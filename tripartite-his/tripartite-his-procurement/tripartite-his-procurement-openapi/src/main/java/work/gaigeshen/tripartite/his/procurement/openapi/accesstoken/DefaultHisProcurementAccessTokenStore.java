package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 访问令牌存储器默认的实现，采用简单的哈希存储
 *
 * @author gaigeshen
 */
public class DefaultHisProcurementAccessTokenStore implements HisProcurementAccessTokenStore {

    private final Map<HisProcurementConfig, HisProcurementAccessToken> internalStore = new ConcurrentHashMap<>();

    @Override
    public boolean save(HisProcurementConfig config, HisProcurementAccessToken accessToken) throws HisProcurementAccessTokenStoreException {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(accessToken, "accessToken cannot be null");
        return Objects.isNull(internalStore.put(config, accessToken));
    }

    @Override
    public void delete(HisProcurementConfig config) throws HisProcurementAccessTokenStoreException {
        ArgumentValidate.notNull(config, "config cannot be null");
        internalStore.remove(config);
    }

    @Override
    public HisProcurementAccessToken find(HisProcurementConfig config) throws HisProcurementAccessTokenStoreException {
        ArgumentValidate.notNull(config, "config cannot be null");
        return internalStore.get(config);
    }

    @Override
    public Map<HisProcurementConfig, HisProcurementAccessToken> findAll() throws HisProcurementAccessTokenStoreException {
        return new HashMap<>(internalStore);
    }
}
