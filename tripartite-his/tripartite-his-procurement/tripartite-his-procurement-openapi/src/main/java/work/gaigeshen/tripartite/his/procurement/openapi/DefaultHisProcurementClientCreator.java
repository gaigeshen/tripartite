package work.gaigeshen.tripartite.his.procurement.openapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.HisProcurementAccessTokenManager;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

import java.util.Objects;

import static work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig.CONFIG_TYPE_MAT;
import static work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig.CONFIG_TYPE_MED;

/**
 * @author gaigeshen
 */
public class DefaultHisProcurementClientCreator implements HisProcurementClientCreator {

    private static final Logger log = LoggerFactory.getLogger(DefaultHisProcurementClientCreator.class);

    private final HisProcurementAccessTokenManager accessTokenManager;

    public DefaultHisProcurementClientCreator(HisProcurementAccessTokenManager accessTokenManager) {
        ArgumentValidate.notNull(accessTokenManager, "accessTokenManager cannot be null");
        this.accessTokenManager = accessTokenManager;
    }

    @Override
    public HisProcurementBasicClient create(HisProcurementConfig config) throws HisProcurementClientCreationException {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(config.getType(), "config type cannot be null");
        HisProcurementAccessTokenClient accessTokenClient = HisProcurementAccessTokenClient.create(config);
        HisProcurementClientAccessTokenInterceptor interceptor = new HisProcurementClientAccessTokenInterceptor(accessTokenClient, accessTokenManager);
        log.info("creating his procurement client: {}", config);
        if (Objects.equals(CONFIG_TYPE_MAT, config.getType())) {
            return DefaultHisProcurementMatClient.create(config, interceptor);
        }
        else if (Objects.equals(CONFIG_TYPE_MED, config.getType())) {
            return DefaultHisProcurementMedClient.create(config, interceptor);
        }
        throw new HisProcurementClientCreationException("config type [ " + config.getType() + " ] not supported");
    }
}
