package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.accesstoken.AccessToken;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.core.client.config.Config;
import work.gaigeshen.tripartite.core.client.config.ConfigException;
import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.client.response.ClientResponse;

import java.util.Objects;

/**
 * 接口客户端
 *
 * @author gaigeshen
 */
public interface Client<C extends Config> extends ServerHosts {

    C getConfig() throws ConfigException;

    <R extends ClientResponse, P extends ClientParameters> R execute(
            P parameters, Class<R> responseClass, String path, Object... uriVariables
    ) throws ClientException;

    <R extends ClientResponse> R execute(
            Class<R> responseClass, String path, Object... uriVariables
    ) throws ClientException;

    /**
     * 此接口客户端被创建之后可以调用此方法来做些初始化的工作
     *
     * @throws ClientException 在初始化的时候发生异常
     */
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
