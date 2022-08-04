package work.gaigeshen.tripartite.his.procurement.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.gaigeshen.tripartite.his.procurement.openapi.DefaultHisProcurementClient;
import work.gaigeshen.tripartite.his.procurement.openapi.HisProcurementClient;
import work.gaigeshen.tripartite.his.procurement.openapi.HisProcurementClientInterceptor;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.*;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

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
    return new DefaultHisProcurementAccessTokenRefresher(oldAccessToken -> hisProcurementClient());
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

    HisProcurementClientInterceptor interceptor = new HisProcurementClientInterceptor(
            () -> DefaultHisProcurementClient.create(procurementConfig), this::hisProcurementAccessTokenManager);
    return DefaultHisProcurementClient.create(procurementConfig, interceptor);
  }

}
