package work.gaigeshen.tripartite.his.procurement.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.gaigeshen.tripartite.his.procurement.openapi.DefaultHisProcurementClient;
import work.gaigeshen.tripartite.his.procurement.openapi.HisProcurementClient;
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
  public HisProcurementAccessTokenManager hisProcurementAccessTokenManager(
          HisProcurementAccessTokenStore accessTokenStore,
          HisProcurementAccessTokenRefresher accessTokenRefresher) {
    log.info("creating default his procurement access token manager " +
            "by using the token store {} and the token refresher {}", accessTokenStore, accessTokenRefresher);
    return new DefaultHisProcurementAccessTokenManager(accessTokenStore, accessTokenRefresher);
  }

  @Bean
  public HisProcurementAccessTokenStore hisProcurementAccessTokenStore() {
    log.info("creating default his procurement access token store");
    return new DefaultHisProcurementAccessTokenStore();
  }

  @Bean
  public HisProcurementAccessTokenRefresher hisProcurementAccessTokenRefresher(HisProcurementClient hisProcurementClient) {
    log.info("creating default his procurement access token refresher " +
            "by using the client: {}", hisProcurementClient);
    return new DefaultHisProcurementAccessTokenRefresher(hisProcurementClient);
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

    return DefaultHisProcurementClient.create(procurementConfig);
  }
}
