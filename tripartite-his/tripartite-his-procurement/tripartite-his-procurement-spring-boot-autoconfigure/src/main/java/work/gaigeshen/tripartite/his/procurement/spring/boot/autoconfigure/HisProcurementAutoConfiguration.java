package work.gaigeshen.tripartite.his.procurement.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.gaigeshen.tripartite.his.procurement.openapi.*;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.*;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

import java.util.ArrayList;
import java.util.Collection;

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
    return new DefaultHisProcurementAccessTokenManager(hisProcurementAccessTokenStore(),
            hisProcurementAccessTokenRefresher());
  }

  @Bean
  public HisProcurementAccessTokenStore hisProcurementAccessTokenStore() {
    return new DefaultHisProcurementAccessTokenStore();
  }

  @Bean
  public HisProcurementAccessTokenRefresher hisProcurementAccessTokenRefresher() {
    return new DefaultHisProcurementAccessTokenRefresher(oat -> {
      HisProcurementClients hisProcurementClients = hisProcurementClients();
      HisProcurementConfig procurementConfig = hisProcurementClients.getConfig(oat.getAccount());
      return HisProcurementAccessTokenClient.create(procurementConfig);
    });
  }

  @Bean
  public HisProcurementClients hisProcurementClients() {

    HisProcurementAccessTokenManager accessTokenManager = hisProcurementAccessTokenManager();

    Collection<HisProcurementClient> hisProcurementClients = new ArrayList<>();

    for (HisProcurementProperties.Client client : hisProcurementProperties.getClients()) {
      HisProcurementConfig.Builder builder = HisProcurementConfig.builder();
      builder.setServerHost(client.getServerHost());
      builder.setAccessTokenUri(client.getAccessTokenUri());
      builder.setServiceUri(client.getServiceUri());
      builder.setAccount(client.getAccount());
      builder.setAppCode(client.getAppCode());
      builder.setAuthCode(client.getAuthCode());

      HisProcurementConfig config = builder.build();

      DefaultHisProcurementClient accessTokenClient = HisProcurementAccessTokenClient.create(config);
      HisProcurementClientAccessTokenInterceptor interceptor = new HisProcurementClientAccessTokenInterceptor(
              accessTokenClient, accessTokenManager);

      hisProcurementClients.add(DefaultHisProcurementClient.create(config, interceptor));
      log.info("loaded his procurement client: {}", config);
    }
    return new DefaultHisProcurementClients(hisProcurementClients);
  }

}
