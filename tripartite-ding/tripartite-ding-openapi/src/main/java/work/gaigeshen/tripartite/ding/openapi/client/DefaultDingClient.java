package work.gaigeshen.tripartite.ding.openapi.client;

import work.gaigeshen.tripartite.core.client.AbstractClient;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

/**
 *
 * @author gaigeshen
 */
public class DefaultDingClient extends AbstractClient<DingConfig> implements DingClient {

    protected DefaultDingClient(DingConfig config, AbstractInterceptor... interceptors) {
        super(config, interceptors);
    }

    public static DefaultDingClient create(DingConfig config, AbstractInterceptor... interceptors) {
        return new DefaultDingClient(config, interceptors);
    }
}
