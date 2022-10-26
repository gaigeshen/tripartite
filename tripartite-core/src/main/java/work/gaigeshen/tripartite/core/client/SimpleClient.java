package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.config.Config;
import work.gaigeshen.tripartite.core.client.config.ConfigException;
import work.gaigeshen.tripartite.core.client.config.ConfigResolver;

import java.util.Collection;
import java.util.Objects;

/**
 * 简单的接口客户端，注意此客户端没有访问令牌的支持
 *
 * @author gaigeshen
 */
public class SimpleClient<C extends Config> extends AbstractWebExecutorClient<C> implements Client<C> {

    private final C config;

    private final ServerHostResolver<C> resolver;

    /**
     * 创建简单的接口客户端
     *
     * @param config 配置信息
     * @param resolver 用于服务器地址解析
     */
    public SimpleClient(C config, ServerHostResolver<C> resolver) {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        if (Objects.isNull(resolver)) {
            throw new IllegalArgumentException("server host determiner cannot be null");
        }
        this.config = config;
        this.resolver = resolver;
    }

    @Override
    public C getConfig() throws ConfigException {
        return config;
    }

    @Override
    public Collection<ServerHost> getServerHosts() throws ServerHostException {
        try {
            return resolver.resolve(config);
        } catch (Exception e) {
            throw new ServerHostException(e.getMessage(), e);
        }
    }

    /**
     * 从配置信息中解析出服务器地址集合
     *
     * @author gaigeshen
     * @param <C> 配置信息类型
     */
    interface ServerHostResolver<C extends Config> extends ConfigResolver<C, Collection<ServerHost>> {

        /**
         * 从配置信息中解析出服务器地址集合
         *
         * @param config 配置信息
         * @return 服务器地址集合
         * @throws ConfigException 无法解析服务器地址抛出此异常
         */
        @Override
        Collection<ServerHost> resolve(C config) throws ConfigException;
    }
}
