package work.gaigeshen.tripartite.qyweixin.openapi.client;

import work.gaigeshen.tripartite.core.client.*;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessToken;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenHelper;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManagerException;
import work.gaigeshen.tripartite.core.client.config.ConfigException;
import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.client.response.ClientResponse;
import work.gaigeshen.tripartite.core.ratelimiter.RateLimiterService;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.qyweixin.openapi.config.QyWeixinConfig;
import work.gaigeshen.tripartite.qyweixin.openapi.response.QyWeixinApiResponse;
import work.gaigeshen.tripartite.qyweixin.openapi.response.api.QyWeixinAccessTokenResponse;

import java.util.Collection;
import java.util.Objects;

/**
 * 默认的企业微信接口客户端
 *
 * @author gaigeshen
 */
public class DefaultQyWeixinClient extends AbstractWebExecutorClient<QyWeixinConfig> implements QyWeixinClient {

    private final QyWeixinConfig config;

    private final AccessTokenManager<QyWeixinConfig> accessTokenManager;

    private final RateLimiterService rateLimiterService;

    protected DefaultQyWeixinClient(QyWeixinConfig config, AccessTokenManager<QyWeixinConfig> accessTokenManager) {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(accessTokenManager, "accessTokenManager cannot be null");
        this.config = config;
        this.accessTokenManager = accessTokenManager;
        this.rateLimiterService = RateLimiterService.create(16);
    }

    /**
     * 创建默认的企业微信接口客户端，需要企业微信配置信息和访问令牌管理器
     *
     * @param config 企业微信配置信息
     * @param accessTokenManager 访问令牌管理器
     * @return 企业微信接口客户端
     */
    static DefaultQyWeixinClient create(QyWeixinConfig config, AccessTokenManager<QyWeixinConfig> accessTokenManager) {
        return new DefaultQyWeixinClient(config, accessTokenManager);
    }

    /**
     * 调用此方法将获取新的访问令牌
     *
     * @return 获取到的访问令牌
     * @throws ClientException 无法获取访问令牌
     */
    public AccessToken getNewAccessToken() throws ClientException {
        String path = "/cgi-bin/gettoken?corpid={corpid}&corpsecret={corpsecret}";
        String corpId = config.getCorpId();
        String corpSecret = config.getCorpSecret();
        QyWeixinAccessTokenResponse response = execute(QyWeixinAccessTokenResponse.class, path, corpId, corpSecret);
        String accessToken = response.access_token;
        Long expireIn = response.expires_in;
        if (Objects.isNull(accessToken) || Objects.isNull(expireIn)) {
            throw new ClientException("acquired access token is invalid: " + config);
        }
        return AccessTokenHelper.createAccessToken(config, accessToken, expireIn);
    }

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
        if (preValidated instanceof QyWeixinApiResponse) {
            QyWeixinApiResponse qyWeixinApiResponse = (QyWeixinApiResponse) preValidated;
            if (!Objects.equals(0, qyWeixinApiResponse.errcode)) {
                throw new ClientException("[ " + qyWeixinApiResponse.errcode + " ] - [ " + qyWeixinApiResponse.errmsg + " ]");
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
