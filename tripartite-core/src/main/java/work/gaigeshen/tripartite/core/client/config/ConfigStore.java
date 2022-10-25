package work.gaigeshen.tripartite.core.client.config;

import java.util.Map;

/**
 * 配置信息存储
 *
 * @author gaigeshen
 */
public interface ConfigStore<C extends Config, K> {
    /**
     * 保存配置信息
     *
     * @param key 配置信息的标识符
     * @param config 配置信息
     * @throws ConfigException 无法保存配置信息
     */
    void save(K key, C config) throws ConfigException;

    /**
     * 获取配置信息
     *
     * @param key 配置信息的标识符
     * @return 配置信息
     * @throws ConfigException 无法获取配置信息
     */
    C find(K key) throws ConfigException;

    /**
     * 获取所有的配置信息
     *
     * @return 所有的配置信息
     * @throws ConfigException 无法获取配置信息
     */
    Map<K, C> findAll() throws ConfigException;
}
