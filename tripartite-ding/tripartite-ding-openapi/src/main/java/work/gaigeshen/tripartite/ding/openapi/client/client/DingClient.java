package work.gaigeshen.tripartite.ding.openapi.client.client;

import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenSupported;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

/**
 *
 * @author gaigeshen
 */
public interface DingClient extends Client<DingConfig>, AccessTokenSupported<DingConfig> {

}
