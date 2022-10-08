package work.gaigeshen.tripartite.ding.openapi.accesstoken;

import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 访问令牌存储器默认的实现，采用简单的哈希存储
 *
 * @author gaigeshen
 */
public class DefaultDingAccessTokenStore implements DingAccessTokenStore {

    private final Map<DingConfig, DingAccessToken> internalStore = new ConcurrentHashMap<>();

    @Override
    public boolean save(DingConfig config, DingAccessToken accessToken) throws DingAccessTokenStoreException {
        if (Objects.isNull(accessToken)) {
            throw new IllegalArgumentException("accessToken cannot be null");
        }
        return Objects.isNull(internalStore.put(config, accessToken));
    }

    @Override
    public void delete(DingConfig config) throws DingAccessTokenStoreException {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        internalStore.remove(config);
    }

    @Override
    public DingAccessToken find(DingConfig config) throws DingAccessTokenStoreException {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        return internalStore.get(config);
    }

    @Override
    public Map<DingConfig, DingAccessToken> findAll() throws DingAccessTokenStoreException {
        return new HashMap<>(internalStore);
    }
}
