package work.gaigeshen.tripartite.core.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.client.SimpleClient.ServerHostResolver;
import work.gaigeshen.tripartite.core.client.config.Config;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 创建简单的接口客户端
 *
 * @author gaigeshen
 */
public class SimpleClientCreator<C extends Config> implements ClientCreator<C> {

    private static final Logger log = LoggerFactory.getLogger(SimpleClientCreator.class);

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

    @Override
    public Client<C> create(C config) throws ClientCreationException {
        log.info("creating simple client: {}", config);

        List<AbstractInterceptor> interceptors = getInterceptors(config);

        SimpleClient<C> simpleClient = new SimpleClient<C>(config, resolver) {
            @Override
            protected List<AbstractInterceptor> createInterceptors() { return interceptors; }
        };
        try {
            simpleClient.init();
        } catch (Exception e) {
            throw new ClientCreationException(e.getMessage(), e);
        }
        return simpleClient;
    }

    protected List<AbstractInterceptor> getInterceptors(C config) {
        return Collections.emptyList();
    }
}
