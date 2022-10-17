package work.gaigeshen.tripartite.ding.openapi.client.accesstoken;

import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.ClientSelector;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessToken;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenRefreshException;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenRefresher;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.Objects;

/**
 * @author gaigeshen
 */
public class DingAccessTokenRefresher implements AccessTokenRefresher<DingConfig> {

    private final ClientSelector<DingConfig> clientSelector;

    public DingAccessTokenRefresher(ClientSelector<DingConfig> clientSelector) {
        if (Objects.isNull(clientSelector)) {
            throw new IllegalArgumentException("client selector cannot be null");
        }
        this.clientSelector = clientSelector;
    }

    @Override
    public AccessToken refresh(DingConfig config, AccessToken oldAccessToken) throws AccessTokenRefreshException {
        Client<DingConfig> dingClient;
        try {
            dingClient = clientSelector.select(config);
        } catch (Exception e) {
            throw new AccessTokenRefreshException("could not find ding client: " + oldAccessToken)
                    .setCurrentAccessToken(oldAccessToken)
                    .setCanRetry(false);
        }
        try {
            return DingAccessTokenHelper.getNewAccessToken(dingClient, config);
        } catch (Exception e) {
            throw new AccessTokenRefreshException("could not refresh access token", e)
                    .setCurrentAccessToken(oldAccessToken)
                    .setCanRetry(true);
        }
    }
}
