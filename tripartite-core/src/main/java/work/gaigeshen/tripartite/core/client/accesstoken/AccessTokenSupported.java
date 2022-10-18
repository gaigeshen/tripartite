package work.gaigeshen.tripartite.core.client.accesstoken;

import work.gaigeshen.tripartite.core.client.config.Config;

/**
 * @author gaigeshen
 */
public interface AccessTokenSupported<C extends Config> {

    AccessToken getAccessToken() throws AccessTokenException;

    AccessTokenManager<C> getAccessTokenManager() throws AccessTokenManagerException;
}
