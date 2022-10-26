package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.SimpleClient.ServerHostResolver;
import work.gaigeshen.tripartite.core.client.config.Config;

import java.util.Objects;

/**
 * 创建简单的接口客户端
 *
 * @author gaigeshen
 */
public class SimpleClientCreator<C extends Config> implements ClientCreator<C> {

    private final ServerHostResolver<C> resolver;

    /**
     * 实例化此创建器
     *
     * @param resolver 简单的接口客户端需要此解析器用于获取服务器地址集合
     */
    public SimpleClientCreator(ServerHostResolver<C> resolver) {
        if (Objects.isNull(resolver)) {
            throw new IllegalArgumentException("server host resolver cannot be null");
        }
        this.resolver = resolver;
    }

    /**
     * 直接调用此方法来创建简单的接口客户端
     *
     * @param config 配置信息
     * @param resolver 服务器地址集合解析器
     * @return 简单的接口客户端
     * @param <C> 配置信息类型
     */
    public static <C extends Config> SimpleClient<C> create(C config, ServerHostResolver<C> resolver) {
        return (SimpleClient<C>) new SimpleClientCreator<>(resolver).create(config);
    }

    @Override
    public Client<C> create(C config) throws ClientCreationException {
        return new SimpleClient<>(config, resolver);
    }
}
