package work.gaigeshen.tripartite.ding.openapi.client.client;

import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

/**
 *
 * @author gaigeshen
 */
public class DefaultDingNewEditionClient extends DingAbstractClient implements DingNewEditionClient {

    protected DefaultDingNewEditionClient(DingConfig config, AccessTokenManager<DingConfig> accessTokenManager) {
        super(config, accessTokenManager);
    }
}
