package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.config.Config;

/**
 * 用于创建客户端
 *
 * @author gaigeshen
 */
public interface ClientCreator<C extends Config> {

    /**
     * 创建客户端
     *
     * @param config 配置信息
     * @return 新创建的客户端
     * @throws ClientCreationException 无法创建客户端
     */
    Client<C> create(C config) throws ClientCreationException;

}
