package work.gaigeshen.tripartite.ding.openapi.client.accesstoken;

import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessToken;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenHelper;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.parameters.api.DingAccessTokenParameters;
import work.gaigeshen.tripartite.ding.openapi.response.api.DingAccessTokenResponse;

import java.util.Objects;

/**
 * 钉钉访问令牌帮助类
 *
 * @author gaigeshen
 */
public class DingAccessTokenHelper {

    public static AccessToken getNewAccessToken(Client<DingConfig> accessTokenClient) {
        DingConfig config = accessTokenClient.getConfig();
        DingAccessTokenParameters parameters = new DingAccessTokenParameters();
        parameters.appKey = config.getAppKey();
        parameters.appSecret = config.getAppSecret();
        DingAccessTokenResponse response;
        try {
            response = accessTokenClient.execute(parameters, DingAccessTokenResponse.class, "/v1.0/oauth2/accessToken");
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
}
