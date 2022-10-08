package work.gaigeshen.tripartite.ding.openapi.accesstoken;

import work.gaigeshen.tripartite.ding.openapi.DingClient;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingAccessTokenParameters;
import work.gaigeshen.tripartite.ding.openapi.response.DingAccessTokenResponse;

import java.util.Objects;

/**
 * @author gaigeshen
 */
public class DefaultDingAccessTokenRefresher implements DingAccessTokenRefresher {

    private final DingClientSelector clientSelector;

    public DefaultDingAccessTokenRefresher(DingClientSelector clientSelector) {
        if (Objects.isNull(clientSelector)) {
            throw new IllegalArgumentException("client selector cannot be null");
        }
        this.clientSelector = clientSelector;
    }

    @Override
    public DingAccessToken refresh(DingConfig config, DingAccessToken oldAccessToken)
            throws DingAccessTokenRefreshException {
        DingClient client;
        try {
            client = clientSelector.select(config, oldAccessToken);
        } catch (Exception e) {
            throw new DingAccessTokenRefreshException("could not find ding client: " + oldAccessToken);
        }
        DingAccessTokenParameters parameters = new DingAccessTokenParameters(config.getAppKey(), config.getAppSecret());
        DingAccessTokenResponse response;
        try {
            response = client.execute(parameters, DingAccessTokenResponse.class, config.getAccessTokenUri());
        } catch (Exception e) {
            throw new DingAccessTokenRefreshException("could not refresh access token", e)
                    .setCurrentAccessToken(oldAccessToken).setCanRetry(true);
        }
        return DingAccessTokenHelper.createAccessToken(config, response.getAccessToken(), response.getExpireIn());
    }

    /**
     * @author gaigeshen
     */
    @FunctionalInterface
    public interface DingClientSelector {
        DingClient select(DingConfig config, DingAccessToken oldAccessToken);
    }
}
