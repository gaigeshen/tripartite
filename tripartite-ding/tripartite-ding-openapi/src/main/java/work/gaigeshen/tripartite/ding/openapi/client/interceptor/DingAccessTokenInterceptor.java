package work.gaigeshen.tripartite.ding.openapi.client.interceptor;

import work.gaigeshen.tripartite.core.client.AbstractClient;
import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessToken;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.ding.openapi.client.accesstoken.DingAccessTokenHelper;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.Objects;

/**
 * 钉钉访问令牌拦截器，会在请求的头部放入访问令牌
 *
 * @author gaigeshen
 */
public class DingAccessTokenInterceptor extends AbstractInterceptor {

    private static final String ACCESS_TOKEN_HEADER = "x-acs-dingtalk-access-token";

    private final Client<DingConfig> accessTokenClient;

    private final AccessTokenManager<DingConfig> accessTokenManager;

    /**
     * 调用此方法可以使用指定的客户端来获取新的访问令牌
     *
     * @param accessTokenClient 此客户端用来获取新的访问令牌
     * @param accessTokenManager 需要访问令牌管理器，获取到的访问令牌将交给此管理器维护
     */
    public DingAccessTokenInterceptor(Client<DingConfig> accessTokenClient, AccessTokenManager<DingConfig> accessTokenManager) {
        if (Objects.isNull(accessTokenClient)) {
            throw new IllegalArgumentException("access token client cannot be null");
        }
        if (Objects.isNull(accessTokenManager)) {
            throw new IllegalArgumentException("access token manager cannot be null");
        }
        this.accessTokenClient = accessTokenClient;
        this.accessTokenManager = accessTokenManager;
    }

    /**
     * 调用此方法将会使用单独的客户端来获取新的访问令牌
     *
     * @param config 钉钉配置信息
     * @param accessTokenManager 需要访问令牌管理器，获取到的访问令牌将交给此管理器维护
     * @return 返回被创建的拦截器对象
     */
    public static DingAccessTokenInterceptor create(DingConfig config, AccessTokenManager<DingConfig> accessTokenManager) {
        return new DingAccessTokenInterceptor(new AbstractClient<DingConfig>(config) {}, accessTokenManager);
    }

    @Override
    protected void updateRequest(Request request) throws InterceptingException {
        DingConfig config = accessTokenClient.getConfig();
        AccessToken accessToken;
        try {
            accessToken = DingAccessTokenHelper.findValidAccessToken(accessTokenManager, accessTokenClient, config);
        } catch (Exception e) {
            throw new InterceptingException("could not find valid access token: " + config, e);
        }
        request.headers().putValue(ACCESS_TOKEN_HEADER, accessToken.getAccessToken());
    }

    @Override
    protected void validateResponse(Request request, Response response) throws InterceptingException { }

}
