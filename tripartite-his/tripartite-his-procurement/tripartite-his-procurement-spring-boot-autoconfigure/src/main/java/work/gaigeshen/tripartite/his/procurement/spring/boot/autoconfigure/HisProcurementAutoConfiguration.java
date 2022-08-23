package work.gaigeshen.tripartite.his.procurement.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.gaigeshen.tripartite.his.procurement.openapi.DefaultHisProcurementClientCreator;
import work.gaigeshen.tripartite.his.procurement.openapi.DefaultHisProcurementClients;
import work.gaigeshen.tripartite.his.procurement.openapi.HisProcurementBasicClient;
import work.gaigeshen.tripartite.his.procurement.openapi.HisProcurementClientCreator;
import work.gaigeshen.tripartite.his.procurement.openapi.HisProcurementClients;
import work.gaigeshen.tripartite.his.procurement.openapi.HisProcurementMatClient;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.DefaultHisProcurementAccessTokenManager;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.DefaultHisProcurementAccessTokenRefresher;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.DefaultHisProcurementAccessTokenStore;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.HisProcurementAccessTokenManager;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.HisProcurementAccessTokenRefresher;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.HisProcurementAccessTokenStore;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;
import work.gaigeshen.tripartite.his.procurement.spring.boot.autoconfigure.HisProcurementProperties.Client;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
@EnableConfigurationProperties({ HisProcurementProperties.class })
@ConditionalOnClass({ HisProcurementMatClient.class })
@Configuration
public class HisProcurementAutoConfiguration {

  private static final Logger log = LoggerFactory.getLogger(HisProcurementAutoConfiguration.class);

  private final HisProcurementProperties hisProcurementProperties;

  public HisProcurementAutoConfiguration(HisProcurementProperties hisProcurementProperties) {
    this.hisProcurementProperties = hisProcurementProperties;
  }

  @Bean
  public HisProcurementClients hisProcurementClients() {
    HisProcurementAccessTokenManager accessTokenManager = hisProcurementAccessTokenManager();
    HisProcurementClientCreator hisProcurementClientCreator = new DefaultHisProcurementClientCreator(accessTokenManager);
    Collection<HisProcurementBasicClient> hisProcurementClients = new ArrayList<>();
    for (Client client : hisProcurementProperties.getClients()) {
      HisProcurementConfig config = HisProcurementConfig.builder()
              .setServerHost(client.getServerHost())
              .setAccessTokenUri(client.getAccessTokenUri()).setServiceUri(client.getServiceUri())
              .setAccount(client.getAccount()).setType(client.getType())
              .setAppCode(client.getAppCode()).setAuthCode(client.getAuthCode()).setSecret(client.getSecret())
              .build();
      HisProcurementBasicClient procurementClient = hisProcurementClientCreator.create(config);
      hisProcurementClients.add(procurementClient);
      log.info("loaded his procurement client: {}", config);
    }
    return new DefaultHisProcurementClients(hisProcurementClients, hisProcurementClientCreator);
  }

  @Bean(destroyMethod = "shutdown")
  public HisProcurementAccessTokenManager hisProcurementAccessTokenManager() {
    return new DefaultHisProcurementAccessTokenManager(
            hisProcurementAccessTokenStore(), hisProcurementAccessTokenRefresher());
  }

  @Bean
  public HisProcurementAccessTokenRefresher hisProcurementAccessTokenRefresher() {
    return new DefaultHisProcurementAccessTokenRefresher(
            (cfg, oat) -> hisProcurementClients().getClientOrCreate(cfg));
  }

  @Bean
  public HisProcurementAccessTokenStore hisProcurementAccessTokenStore() {
    return new DefaultHisProcurementAccessTokenStore();
  }
}
