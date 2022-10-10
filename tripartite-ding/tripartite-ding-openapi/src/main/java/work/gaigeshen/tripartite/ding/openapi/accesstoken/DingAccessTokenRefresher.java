package work.gaigeshen.tripartite.ding.openapi.accesstoken;

import work.gaigeshen.tripartite.core.client.ClientSelector;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessToken;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenRefreshException;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenRefresher;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.Objects;

/**
 *
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
        return null;
    }
}
