package work.gaigeshen.tripartite.qyweixin.openapi.client;

import work.gaigeshen.tripartite.core.client.ClientException;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessToken;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenHelper;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.qyweixin.openapi.config.QyWeixinConfig;
import work.gaigeshen.tripartite.qyweixin.openapi.response.accesstoken.QyWeixinAccessTokenResponse;

import java.util.Objects;

/**
 * 默认的企业微信接口客户端
 *
 * @author gaigeshen
 */
public class DefaultQyWeixinClient extends AbstractQyWeixinClient implements QyWeixinClient {

    protected DefaultQyWeixinClient(QyWeixinConfig config, AccessTokenManager<QyWeixinConfig> accessTokenManager) {
        super(config, accessTokenManager);
    }

    public AccessToken getNewAccessToken() throws ClientException {
        QyWeixinConfig config = getConfig();
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
    protected AccessToken getNewSuiteAccessToken() throws ClientException {
        return null;
    }
}
