package work.gaigeshen.tripartite.ding.openapi.client.accesstoken;

import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessToken;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenHelper;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingAccessTokenParameters;
import work.gaigeshen.tripartite.ding.openapi.response.DingAccessTokenResponse;

import java.util.Objects;

/**
 * 钉钉访问令牌帮助类
 *
 * @author gaigeshen
 */
public class DingAccessTokenHelper {

    /**
     * 获取有效的访问令牌，如果当前不存在有效的访问令牌则会尝试获取新的访问令牌，此方法可能会在获取新的访问令牌过程中抛出异常，且不会重试获取
     *
     * @param accessTokenManager 访问令牌管理器
     * @param accessTokenClient 此接口客户端将用于获取新的访问令牌
     * @param config 钉钉配置信息
     * @return 返回有效的访问令牌不会为空
     */
    public static AccessToken findValidAccessToken(
            AccessTokenManager<DingConfig> accessTokenManager,
            Client<DingConfig> accessTokenClient,
            DingConfig config) {
        if (Objects.isNull(accessTokenManager) || Objects.isNull(accessTokenClient)) {
            throw new IllegalArgumentException("access token manager and client cannot be null");
        }
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        AccessToken currentAccessToken = accessTokenManager.findAccessToken(config);
        if (isValidAccessToken(currentAccessToken)) {
            return currentAccessToken;
        }
        synchronized (DingAccessTokenHelper.class) {
            currentAccessToken = accessTokenManager.findAccessToken(config);
            if (isValidAccessToken(currentAccessToken)) {
                return currentAccessToken;
            }
            AccessToken newAccessToken = getNewAccessToken(accessTokenClient, config);
            accessTokenManager.addNewAccessToken(config, newAccessToken);
            return newAccessToken;
        }
    }

    /**
     * 获取新的访问令牌，此方法可能会抛出异常且不会进行重试，建议只在当需要获取新的访问令牌的时候才调用
     *
     * @param accessTokenClient 此接口客户端将用于获取新的访问令牌
     * @param config 钉钉配置信息不能为空
     * @return 返回新的访问令牌
     */
    public static AccessToken getNewAccessToken(Client<DingConfig> accessTokenClient, DingConfig config) {
        if (Objects.isNull(accessTokenClient) || Objects.isNull(config)) {
            throw new IllegalArgumentException("access token client and config cannot be null");
        }
        DingAccessTokenParameters parameters = new DingAccessTokenParameters();
        parameters.appKey = config.getAppKey();
        parameters.appSecret = config.getAppSecret();
        DingAccessTokenResponse response;
        try {
            response = accessTokenClient.execute(parameters, DingAccessTokenResponse.class, config.getAccessTokenUri());
        } catch (Exception e) {
            throw new IllegalStateException("could not get new access token: " + config, e);
        }
        String accessToken = response.accessToken;
        Long expireIn = response.expireIn;
        if (Objects.isNull(accessToken) || Objects.isNull(expireIn)) {
            throw new IllegalStateException("acquired access token is invalid: " + config);
        }
        return AccessTokenHelper.createAccessToken(config, accessToken, expireIn);
    }

    /**
     * 判断访问令牌是否有效
     *
     * @param accessToken 访问令牌
     * @return 如果该访问令牌不为空且未过期的情况则认为是有效的
     */
    public static boolean isValidAccessToken(AccessToken accessToken) {
        return Objects.nonNull(accessToken) && !AccessTokenHelper.isExpired(accessToken);
    }
}
