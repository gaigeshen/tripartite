package work.gaigeshen.tripartite.his.procurement.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.core.util.json.JsonCodec;
import work.gaigeshen.tripartite.his.procurement.openapi.DefaultHisProcurementClient;
import work.gaigeshen.tripartite.his.procurement.openapi.HisProcurementClient;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.*;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.accesstoken.HisProcurementAccessTokenParameters;
import work.gaigeshen.tripartite.his.procurement.openapi.response.accesstoken.HisProcurementAccessTokenResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
@EnableConfigurationProperties({ HisProcurementProperties.class })
@ConditionalOnClass({ HisProcurementClient.class })
@Configuration
public class HisProcurementAutoConfiguration {

  private static final Logger log = LoggerFactory.getLogger(HisProcurementAutoConfiguration.class);

  private final HisProcurementProperties hisProcurementProperties;

  public HisProcurementAutoConfiguration(HisProcurementProperties hisProcurementProperties) {
    this.hisProcurementProperties = hisProcurementProperties;
  }

  @Bean(destroyMethod = "shutdown")
  public HisProcurementAccessTokenManager hisProcurementAccessTokenManager() {
    return new DefaultHisProcurementAccessTokenManager(hisProcurementAccessTokenStore(), hisProcurementAccessTokenRefresher());
  }

  @Bean
  public HisProcurementAccessTokenStore hisProcurementAccessTokenStore() {
    return new DefaultHisProcurementAccessTokenStore();
  }

  @Bean
  public HisProcurementAccessTokenRefresher hisProcurementAccessTokenRefresher() {
    return new HisProcurementAccessTokenRefresherImpl();
  }

  @Bean
  public HisProcurementClient hisProcurementClient() {
    HisProcurementConfig.Builder builder = HisProcurementConfig.builder();
    builder.setServerHost(hisProcurementProperties.getServerHost());
    builder.setAccessTokenUri(hisProcurementProperties.getAccessTokenUri());
    builder.setServiceUri(hisProcurementProperties.getServiceUri());
    builder.setAccount(hisProcurementProperties.getAccount());
    builder.setAppCode(hisProcurementProperties.getAppCode());
    builder.setAuthCode(hisProcurementProperties.getAuthCode());

    HisProcurementConfig procurementConfig = builder.build();

    log.info("creating his procurement client: {}", procurementConfig);

    return DefaultHisProcurementClient.create(procurementConfig, new HisProcurementAccessTokenInterceptorImpl());
  }

  /**
   *
   * @author gaigeshen
   */
  public class HisProcurementAccessTokenRefresherImpl implements HisProcurementAccessTokenRefresher {

    @Override
    public HisProcurementAccessToken refresh(HisProcurementAccessToken oldAccessToken) throws HisProcurementAccessTokenRefreshException {
      HisProcurementClient client = hisProcurementClient();
      HisProcurementConfig config = client.getHisProcurementConfig();

      HisProcurementAccessTokenParameters parameters = new HisProcurementAccessTokenParameters();
      parameters.appCode = config.getAppCode();
      parameters.authCode = config.getAuthCode();

      HisProcurementAccessTokenResponse response;
      try {
        response = client.execute(parameters, HisProcurementAccessTokenResponse.class, config.getAccessTokenUri());
      } catch (Exception e) {
        throw new HisProcurementAccessTokenRefreshException("could not refresh access token", e)
                .setCurrentAccessToken(oldAccessToken).setCanRetry(true);
      }
      return HisProcurementAccessToken.builder()
              .setAccessToken(response.accessToken).setAccount(config.getAccount())
              .setExpiresIn(1800).setExpiresTimestamp(System.currentTimeMillis() / 1000 + 1800)
              .setUpdateTime(new Date())
              .build();
    }
  }

  /**
   *
   * @author gaigeshen
   */
  public class HisProcurementAccessTokenInterceptorImpl extends AbstractInterceptor {

    @Override
    protected void updateRequest(Request request) throws InterceptingException {
      if (request.url().contains("accessToken")) {
        return;
      }
      HisProcurementClient client = hisProcurementClient();
      HisProcurementConfig config = client.getHisProcurementConfig();
      HisProcurementAccessTokenManager accessTokenManager = hisProcurementAccessTokenManager();
      HisProcurementAccessToken accessToken = accessTokenManager.findAccessToken(config.getAccount());
      if (Objects.nonNull(accessToken)) {
        request.headers().putValue("Access-Token", accessToken.getAccessToken());
        return;
      }

      HisProcurementAccessTokenParameters parameters = new HisProcurementAccessTokenParameters();
      parameters.appCode = config.getAppCode();
      parameters.authCode = config.getAuthCode();
      HisProcurementAccessTokenResponse response;
      try {
        response = client.execute(parameters, HisProcurementAccessTokenResponse.class, config.getAccessTokenUri());
      } catch (Exception e) {
        throw new HisProcurementAccessTokenRefreshException("could not refresh access token", e);
      }
      HisProcurementAccessToken newAccessToken = HisProcurementAccessToken.builder()
              .setAccessToken(response.accessToken).setAccount(config.getAccount())
              .setExpiresIn(1800).setExpiresTimestamp(System.currentTimeMillis() / 1000 + 1800)
              .setUpdateTime(new Date())
              .build();
      accessTokenManager.addNewAccessToken(newAccessToken);
      request.headers().putValue("Access-Token", newAccessToken.getAccessToken());
    }

    @Override
    protected void validateResponse(Request request, Response response) throws InterceptingException {
      String rawResponse;
      try {
        rawResponse = response.bodyString(StandardCharsets.UTF_8);
      } catch (IOException e) {
        throw new InterceptingException("could not read raw response", e);
      }
      Map<String, Object> decodedResponse = JsonCodec.instance().decodeObject(rawResponse);
      String infcode = (String) decodedResponse.get("infcode");
      if (!Objects.equals("0", infcode)) {
        throw new InterceptingException(rawResponse);
      }
      Map<?, ?> output = (Map<?, ?>) decodedResponse.get("output");
      if (Objects.isNull(output) || !output.containsKey("data")) {
        throw new InterceptingException("response output or output data not found: " + rawResponse);
      }
      response.buffered(JsonCodec.instance().encode(output.get("data")).getBytes(StandardCharsets.UTF_8));
    }
  }

}
