package work.gaigeshen.tripartite.ding.openapi.client;

import work.gaigeshen.tripartite.core.client.*;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessToken;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenHelper;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManagerException;
import work.gaigeshen.tripartite.core.client.config.ConfigException;
import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.client.response.ClientResponse;
import work.gaigeshen.tripartite.core.header.Headers;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.core.ratelimiter.RateLimiterService;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingApiParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingOapiParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.api.DingAccessTokenParameters;
import work.gaigeshen.tripartite.ding.openapi.response.DingApiResponse;
import work.gaigeshen.tripartite.ding.openapi.response.DingOapiResponse;
import work.gaigeshen.tripartite.ding.openapi.response.api.DingAccessTokenResponse;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 默认的钉钉接口客户端
 *
 * @author gaigeshen
 */
public class DefaultDingClient extends AbstractWebExecutorClient<DingConfig> implements DingClient<DingConfig> {

    private final DingConfig config;

    private final AccessTokenManager<DingConfig> accessTokenManager;

    protected DefaultDingClient(DingConfig config, AccessTokenManager<DingConfig> accessTokenManager) {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        if (Objects.isNull(accessTokenManager)) {
            throw new IllegalArgumentException("access token manager cannot be null");
        }
        this.config = config;
        this.accessTokenManager = accessTokenManager;
    }

    /**
     * 创建默认的钉钉接口客户端，需要钉钉配置信息和访问令牌管理器
     *
     * @param config 钉钉配置信息
     * @param accessTokenManager 访问令牌管理器
     * @return 钉钉接口客户端
     */
    static DefaultDingClient create(DingConfig config, AccessTokenManager<DingConfig> accessTokenManager) {
        return new DefaultDingClient(config, accessTokenManager);
    }

    /**
     * 调用此方法将获取新的访问令牌，在访问令牌过期前，重复获取返回的访问令牌都是相同的
     *
     * @return 获取到的访问令牌
     * @throws ClientException 无法获取访问令牌
     */
    public AccessToken getNewAccessToken() throws ClientException {
        DingAccessTokenParameters parameters = new DingAccessTokenParameters();
        parameters.appKey = config.getAppKey();
        parameters.appSecret = config.getAppSecret();
        DingAccessTokenResponse response = execute(parameters, DingAccessTokenResponse.class,
                "/v1.0/oauth2/accessToken");
        String accessToken = response.accessToken;
        Long expireIn = response.expireIn;
        if (Objects.isNull(accessToken) || Objects.isNull(expireIn)) {
            throw new ClientException("acquired access token is invalid: " + config);
        }
        return AccessTokenHelper.createAccessToken(config, accessToken, expireIn);
    }

    /**
     * 在钉钉接口客户端被创建之后，调用此方法来获取钉钉访问令牌并添加到访问令牌管理器
     *
     * @throws ClientException 无法获取钉钉访问令牌
     */
    @Override
    public synchronized void init() throws ClientException {
        super.init();
        try {
            accessTokenManager.addNewAccessToken(config, getNewAccessToken());
        } catch (AccessTokenManagerException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    @Override
    protected List<AbstractInterceptor> createInterceptors() {
        return Collections.singletonList(new AbstractInterceptor() {
            @Override
            protected void updateRequest(Request request) throws InterceptingException {
                // 获取新的访问令牌无需加入请求头，只会使用新版接口去获取访问令牌
                if (request.url().contains("/v1.0/oauth2/accessToken")) {
                    return;
                }
                // 其他任何情况都加入访问令牌请求头
                // 旧版本接口加入此请求头应该无任何副作用
                String accessTokenValue = getAccessTokenValue();
                if (Objects.isNull(accessTokenValue)) {
                    throw new InterceptingException("access token not found: " + getConfig());
                }
                Headers headers = request.headers();
                headers.putValue("x-acs-dingtalk-access-token", accessTokenValue);
            }
            @Override
            protected void validateResponse(Request request, Response response) throws InterceptingException {
            }
        });
    }

    @Override
    public DingConfig getConfig() throws ConfigException {
        return config;
    }

    @Override
    public AccessTokenManager<DingConfig> getAccessTokenManager() {
        return accessTokenManager;
    }

    @Override
    public RateLimiterService getRateLimiterService() {
        return RateLimiterService.create(16);
    }

    @Override
    protected <R extends ClientResponse> R validateResponse(R response) throws ClientException {
        R preValidated = super.validateResponse(response);
        if (preValidated instanceof DingOapiResponse) {
            DingOapiResponse dingOapiResponse = (DingOapiResponse) preValidated;
            if (!Objects.equals(0, dingOapiResponse.errcode)) {
                throw new ClientException("[ " + dingOapiResponse.errcode + " ] - [ " + dingOapiResponse.errmsg + " ]");
            }
        }
        return preValidated;
    }

    @Override
    public ServerHost getServerHost(ClientParameters parameters, Class<? extends ClientResponse> responseClass) throws ServerHostException {
        if (parameters instanceof DingApiParameters || DingApiResponse.class.isAssignableFrom(responseClass)) {
            return ServerHost.create(config.getApiServerHost());
        }
        else if (parameters instanceof DingOapiParameters || DingOapiResponse.class.isAssignableFrom(responseClass)) {
            return ServerHost.create(config.getOapiServerHost());
        }
        throw new ServerHostException("could not determine server host: " + parameters + ", " + responseClass);
    }

    @Override
    public Collection<ServerHost> getServerHosts() throws ServerHostException {
        return ServerHosts.create(config.getApiServerHost(), config.getOapiServerHost());
    }
}
