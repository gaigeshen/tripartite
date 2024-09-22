package work.gaigeshen.tripartite.qyweixin.openapi.client;

import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessToken;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.qyweixin.openapi.config.QyWeixinConfig;
import work.gaigeshen.tripartite.qyweixin.openapi.suite.QyWeixinSuiteTicket;

import java.util.Objects;

public interface BaseQyWeixinClient extends Client<QyWeixinConfig> {

    default String getSuiteAccessTokenValue() {
        AccessToken accessToken = getSuiteAccessToken();
        return Objects.isNull(accessToken) ? "" : accessToken.getAccessToken();
    }

    default AccessToken getSuiteAccessToken() {
        AccessTokenManager<QyWeixinConfig> accessTokenManager = getSuiteAccessTokenManager();
        if (Objects.isNull(accessTokenManager)) {
            return null;
        }
        return accessTokenManager.findAccessToken(getConfig());
    }

    default AccessTokenManager<QyWeixinConfig> getSuiteAccessTokenManager() {
        return null;
    }

    default void setNewSuiteTicket(QyWeixinSuiteTicket suiteTicket) { }
}
