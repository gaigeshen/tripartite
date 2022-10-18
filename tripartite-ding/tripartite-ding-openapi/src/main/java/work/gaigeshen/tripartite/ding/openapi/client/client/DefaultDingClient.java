package work.gaigeshen.tripartite.ding.openapi.client.client;

import work.gaigeshen.tripartite.core.client.AbstractClient;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessToken;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenException;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManagerException;
import work.gaigeshen.tripartite.ding.openapi.client.accesstoken.DingAccessTokenHelper;
import work.gaigeshen.tripartite.ding.openapi.client.interceptor.DingAccessTokenInterceptor;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class DefaultDingClient extends AbstractClient<DingConfig> implements DingClient {

    private final AccessTokenManager<DingConfig> accessTokenManager;

    private DefaultDingClient(DingConfig config, AccessTokenManager<DingConfig> accessTokenManager) {
        super(config, DingAccessTokenInterceptor.create(config, accessTokenManager));
        this.accessTokenManager = accessTokenManager;
    }

    public static DefaultDingClient create(DingConfig config, AccessTokenManager<DingConfig> accessTokenManager) {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        if (Objects.isNull(accessTokenManager)) {
            throw new IllegalArgumentException("access token manager cannot be null");
        }
        return new DefaultDingClient(config, accessTokenManager);
    }

    @Override
    public AccessToken getAccessToken() throws AccessTokenException {
        DingConfig config = getConfig();
        AbstractClient<DingConfig> accessTokenClient = new AbstractClient<DingConfig>(config) {};
        try {
            return DingAccessTokenHelper.findValidAccessToken(accessTokenManager, accessTokenClient, config);
        } catch (Exception e) {
            throw new AccessTokenException("could not find valid access token", e);
        }
    }

    @Override
    public AccessTokenManager<DingConfig> getAccessTokenManager() throws AccessTokenManagerException {
        return accessTokenManager;
    }
}
