package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.config.Config;
import work.gaigeshen.tripartite.core.client.config.ConfigException;

import java.util.Collection;
import java.util.Objects;

/**
 * 默认的接口客户端，注意此客户端没有访问令牌的支持
 *
 * @author gaigeshen
 */
public class DefaultClient<C extends Config> extends AbstractWebExecutorClient<C> implements Client<C> {

    private final C config;

    private final ServerHostDeterminer<C> determiner;

    /**
     * 创建默认的接口客户端
     *
     * @param config 配置信息
     * @param determiner 用于服务器地址确认
     */
    public DefaultClient(C config, ServerHostDeterminer<C> determiner) {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        if (Objects.isNull(determiner)) {
            throw new IllegalArgumentException("server host determiner cannot be null");
        }
        this.config = config;
        this.determiner = determiner;
    }

    @Override
    public C getConfig() throws ConfigException {
        return config;
    }

    @Override
    public Collection<ServerHost> getServerHosts() throws ServerHostException {
        return determiner.determine(config);
    }
}
