package work.gaigeshen.tripartite.core.client.config;

import java.util.Collection;
import java.util.Collections;

/**
 * 配置信息资源库
 *
 * @param <C> 具体的配置信息类型
 */
public interface ConfigRepository<C extends Config> {

    /**
     * 获取所有的配置信息
     *
     * @return 所有的配置信息
     * @throws ConfigException 无法获取配置信息
     */
    default Collection<C> findAll() throws ConfigException {
        return Collections.emptyList();
    }
}
