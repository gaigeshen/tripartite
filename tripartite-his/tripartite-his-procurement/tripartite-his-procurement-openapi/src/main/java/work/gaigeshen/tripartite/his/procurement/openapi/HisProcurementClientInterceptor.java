package work.gaigeshen.tripartite.his.procurement.openapi;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.core.util.json.JsonCodec;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.HisProcurementAccessToken;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.HisProcurementAccessTokenManager;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.accesstoken.HisProcurementAccessTokenParameters;
import work.gaigeshen.tripartite.his.procurement.openapi.response.accesstoken.HisProcurementAccessTokenResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementClientInterceptor extends AbstractInterceptor {

  private final HisProcurementClient hisProcurementClient;

  private final HisProcurementAccessTokenManager hisProcurementAccessTokenManager;

  public HisProcurementClientInterceptor(Supplier<HisProcurementClient> hisProcurementClient,
                                         Supplier<HisProcurementAccessTokenManager> hisProcurementAccessTokenManager) {
    this.hisProcurementClient = hisProcurementClient.get();
    this.hisProcurementAccessTokenManager = hisProcurementAccessTokenManager.get();

    if (!ObjectUtils.allNotNull(this.hisProcurementClient, this.hisProcurementAccessTokenManager)) {
      throw new IllegalArgumentException("hisProcurementClient and hisProcurementAccessTokenManager cannot be null");
    }
  }

  @Override
  protected void updateRequest(Request request) throws InterceptingException {
    if (StringUtils.contains(request.url(), "accessToken")) {
      return;
    }
    HisProcurementConfig config = hisProcurementClient.getHisProcurementConfig();
    HisProcurementAccessToken accessToken = hisProcurementAccessTokenManager.findAccessToken(config.getAccount());
    if (Objects.nonNull(accessToken)) {
      request.headers().putValue("Access-Token", accessToken.getAccessToken());
      return;
    }
    HisProcurementAccessTokenParameters parameters = new HisProcurementAccessTokenParameters();
    parameters.appCode = config.getAppCode();
    parameters.authCode = config.getAuthCode();
    HisProcurementAccessTokenResponse response;
    try {
      response = hisProcurementClient.execute(parameters, HisProcurementAccessTokenResponse.class,
              config.getAccessTokenUri());
    } catch (Exception e) {
      throw new InterceptingException("could not get new access token", e);
    }
    HisProcurementAccessToken newAccessToken = HisProcurementAccessToken.builder()
            .setAccessToken(response.accessToken).setAccount(config.getAccount())
            .setExpiresIn(1800).setExpiresTimestamp(System.currentTimeMillis() / 1000 + 1800)
            .setUpdateTime(new Date()).build();
    hisProcurementAccessTokenManager.addNewAccessToken(newAccessToken);
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
