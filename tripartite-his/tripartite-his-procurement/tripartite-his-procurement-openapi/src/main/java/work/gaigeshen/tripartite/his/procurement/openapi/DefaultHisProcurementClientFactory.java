package work.gaigeshen.tripartite.his.procurement.openapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.HisProcurementAccessTokenClient;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.HisProcurementAccessTokenManager;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class DefaultHisProcurementClientFactory implements HisProcurementClientFactory {

  private static final Logger log = LoggerFactory.getLogger(DefaultHisProcurementClientFactory.class);

  private final HisProcurementAccessTokenManager accessTokenManager;

  public DefaultHisProcurementClientFactory(HisProcurementAccessTokenManager accessTokenManager) {
    if (Objects.isNull(accessTokenManager)) {
      throw new IllegalArgumentException("accessTokenManager cannot be null");
    }
    this.accessTokenManager = accessTokenManager;
  }

  @Override
  public HisProcurementClient create(HisProcurementConfig config) {
    if (Objects.isNull(config)) {
      throw new IllegalArgumentException("config cannot be null");
    }
    log.info("creating his procurement client: {}", config);
    HisProcurementAccessTokenClient accessTokenClient = HisProcurementAccessTokenClient.create(config);

    HisProcurementClientAccessTokenInterceptor interceptor = new HisProcurementClientAccessTokenInterceptor(
            accessTokenClient, accessTokenManager);

    return DefaultHisProcurementClient.create(config, interceptor);
  }
}
