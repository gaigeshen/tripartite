package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.accesstoken.AccessToken;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.core.client.config.Config;
import work.gaigeshen.tripartite.core.client.config.ConfigException;
import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.client.response.ClientResponse;

import java.util.Objects;

/**
 * 客户端
 *
 * @author gaigeshen
 */
public interface Client<C extends Config> {

    C getConfig() throws ConfigException;

    <R extends ClientResponse, P extends ClientParameters> R execute(
            P parameters, Class<R> responseClass, String uri, Object... uriVariables
    ) throws ClientException;

    <R extends ClientResponse> R execute(
            Class<R> responseClass, String uri, Object... uriVariables
    ) throws ClientException;

    default void init() throws ClientException { }

    default String getAccessTokenValue() {
        AccessToken accessToken = getAccessToken();
        return Objects.isNull(accessToken) ? "" : accessToken.getAccessToken();
    }

    default AccessToken getAccessToken() {
        AccessTokenManager<C> accessTokenManager = getAccessTokenManager();
        if (Objects.isNull(accessTokenManager)) {
            return null;
        }
        return accessTokenManager.findAccessToken(getConfig());
    }

    default AccessTokenManager<C> getAccessTokenManager() {
        return null;
    }
}
