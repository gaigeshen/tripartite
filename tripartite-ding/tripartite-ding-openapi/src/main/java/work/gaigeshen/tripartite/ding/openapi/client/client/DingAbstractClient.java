package work.gaigeshen.tripartite.ding.openapi.client.client;

import work.gaigeshen.tripartite.core.client.AbstractClient;
import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.ClientException;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.ding.openapi.client.accesstoken.DingAccessTokenHelper;
import work.gaigeshen.tripartite.ding.openapi.client.interceptor.DingAccessTokenInterceptor;
import work.gaigeshen.tripartite.ding.openapi.client.interceptor.DingResponseValidationInterceptor;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

/**
 *
 * @author gaigeshen
 */
public abstract class DingAbstractClient extends AbstractClient<DingConfig> implements Client<DingConfig> {

    private final AccessTokenManager<DingConfig> accessTokenManager;

    protected DingAbstractClient(DingConfig config, AccessTokenManager<DingConfig> accessTokenManager) {
        super(config, DingAccessTokenInterceptor.create(config, accessTokenManager), DingResponseValidationInterceptor.INSTANCE);
        this.accessTokenManager = accessTokenManager;
    }

    @Override
    public final void init() throws ClientException {
        DingConfig config = getConfig();
        AbstractClient<DingConfig> accessTokenClient = new AbstractClient<DingConfig>(config) { };
        try {
            DingAccessTokenHelper.findValidAccessToken(accessTokenManager, accessTokenClient, config);
        } catch (Exception e) {
            throw new ClientException("could not initialize client", e);
        }
    }

    @Override
    public final AccessTokenManager<DingConfig> getAccessTokenManager() {
        return accessTokenManager;
    }
}
