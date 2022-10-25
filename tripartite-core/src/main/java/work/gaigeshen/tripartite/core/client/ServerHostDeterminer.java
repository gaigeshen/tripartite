package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.config.Config;

import java.util.Collection;

/**
 * 用于服务器地址确认，给定某个配置信息然后从中确认服务器地址
 *
 * @author gaigeshen
 */
public interface ServerHostDeterminer<C extends Config> {

    /**
     * 确认服务器地址
     *
     * @param config 配置信息
     * @return 服务器地址
     * @throws ServerHostException 无法确认服务器地址
     */
    Collection<ServerHost> determine(C config) throws ServerHostException;
}
