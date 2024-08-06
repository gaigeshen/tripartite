package work.gaigeshen.tripartite.core.client.accesstoken;

import work.gaigeshen.tripartite.core.client.config.Config;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 访问令牌存储器默认的实现，采用简单的哈希存储
 *
 * @author gaigeshen
 */
public class DefaultAccessTokenStore<C extends Config> implements AccessTokenStore<C> {

    private final Map<C, AccessToken> internalStore = new ConcurrentHashMap<>();

    @Override
    public boolean save(C config, AccessToken accessToken) throws AccessTokenStoreException {
        ArgumentValidate.notNull(accessToken, "accessToken cannot be null");
        return Objects.isNull(internalStore.put(config, accessToken));
    }

    @Override
    public void delete(C config) throws AccessTokenStoreException {
        ArgumentValidate.notNull(config, "config cannot be null");
        internalStore.remove(config);
    }

    @Override
    public AccessToken find(C config) throws AccessTokenStoreException {
        ArgumentValidate.notNull(config, "config cannot be null");
        return internalStore.get(config);
    }

    @Override
    public Map<C, AccessToken> findAll() throws AccessTokenStoreException {
        return new HashMap<>(internalStore);
    }
}
