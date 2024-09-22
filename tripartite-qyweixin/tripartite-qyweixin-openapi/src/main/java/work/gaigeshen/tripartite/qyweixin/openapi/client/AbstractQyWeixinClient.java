package work.gaigeshen.tripartite.qyweixin.openapi.client;

import work.gaigeshen.tripartite.core.client.*;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessToken;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManagerException;
import work.gaigeshen.tripartite.core.client.config.ConfigException;
import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.client.response.ClientResponse;
import work.gaigeshen.tripartite.core.ratelimiter.RateLimiterService;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.qyweixin.openapi.config.QyWeixinConfig;
import work.gaigeshen.tripartite.qyweixin.openapi.response.QyWeixinResponse;

import java.util.Collection;
import java.util.Objects;

public abstract class AbstractQyWeixinClient extends AbstractWebExecutorClient<QyWeixinConfig> implements BaseQyWeixinClient {

    private final QyWeixinConfig config;

    private final AccessTokenManager<QyWeixinConfig> accessTokenManager;

    private final RateLimiterService rateLimiterService;

    protected AbstractQyWeixinClient(QyWeixinConfig config, AccessTokenManager<QyWeixinConfig> accessTokenManager) {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(accessTokenManager, "accessTokenManager cannot be null");
        this.config = config;
        this.accessTokenManager = accessTokenManager;
        this.rateLimiterService = RateLimiterService.create(100);
    }

    protected abstract AccessToken getNewAccessToken() throws ClientException;

    protected abstract AccessToken getNewSuiteAccessToken() throws ClientException;

    @Override
    protected void initInternal() throws ClientException {
        try {
            accessTokenManager.addNewAccessToken(config, getNewAccessToken());
        } catch (AccessTokenManagerException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    @Override
    public QyWeixinConfig getConfig() throws ConfigException {
        return config;
    }

    @Override
    public AccessTokenManager<QyWeixinConfig> getAccessTokenManager() {
        return accessTokenManager;
    }

    @Override
    public RateLimiterService getRateLimiterService() {
        return rateLimiterService;
    }

    @Override
    protected <R extends ClientResponse> R validateResponse(R response) throws ClientException {
        R preValidated = super.validateResponse(response);
        if (preValidated instanceof QyWeixinResponse) {
            QyWeixinResponse qyWeixinResponse = (QyWeixinResponse) preValidated;
            if (Objects.nonNull(qyWeixinResponse.errcode) && !Objects.equals(0, qyWeixinResponse.errcode)) {
                throw new ClientException("[ " + qyWeixinResponse.errcode + " ] - [ " + qyWeixinResponse.errmsg + " ]");
            }
        }
        return preValidated;
    }

    @Override
    public ServerHost getServerHost(ClientParameters parameters, Class<? extends ClientResponse> responseClass) throws ServerHostException {
        return ServerHost.create(config.getServerHost());
    }

    @Override
    public Collection<ServerHost> getServerHosts() throws ServerHostException {
        return ServerHosts.create(config.getServerHost());
    }
}
