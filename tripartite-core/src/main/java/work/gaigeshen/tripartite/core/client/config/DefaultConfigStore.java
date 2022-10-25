package work.gaigeshen.tripartite.core.client.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * 默认的配置信息存储
 *
 * @author gaigeshen
 */
public class DefaultConfigStore<C extends Config, K> implements ConfigStore<C, K> {

    private final Map<K, C> internalStore = new ConcurrentHashMap<>();

    public DefaultConfigStore(Collection<C> configs, Function<C, K> keyMapper) {
        if (Objects.isNull(configs) || Objects.isNull(keyMapper)) {
            throw new IllegalArgumentException("configs and key mapper cannot be null");
        }
        for (C config : configs) {
            internalStore.put(keyMapper.apply(config), config);
        }
    }

    @Override
    public void save(K key, C config) throws ConfigException {
        if (Objects.isNull(key) || Objects.isNull(config)) {
            throw new IllegalArgumentException("key and config cannot be null");
        }
        internalStore.put(key, config);
    }

    @Override
    public C find(K key) throws ConfigException {
        if (Objects.isNull(key)) {
            throw new IllegalArgumentException("key cannot be null");
        }
        return internalStore.get(key);
    }

    @Override
    public Map<K, C> findAll() throws ConfigException {
        return new HashMap<>(internalStore);
    }
}
