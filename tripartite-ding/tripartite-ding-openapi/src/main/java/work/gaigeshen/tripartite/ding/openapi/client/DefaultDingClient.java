package work.gaigeshen.tripartite.ding.openapi.client;

import work.gaigeshen.tripartite.core.client.AbstractClient;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.ding.openapi.client.interceptor.DingAccessTokenInterceptor;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class DefaultDingClient extends AbstractClient<DingConfig> implements DingClient {

    protected DefaultDingClient(DingConfig config, AbstractInterceptor... interceptors) {
        super(config, interceptors);
    }

    public static DefaultDingClient create(DingConfig config, AccessTokenManager<DingConfig> accessTokenManager) {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        if (Objects.isNull(accessTokenManager)) {
            throw new IllegalArgumentException("access token manager cannot be null");
        }
        return new DefaultDingClient(config, DingAccessTokenInterceptor.create(config, accessTokenManager));
    }
}
