package work.gaigeshen.tripartite.ding.openapi.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.ClientCreationException;
import work.gaigeshen.tripartite.core.client.ClientCreator;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

/**
 *
 * @author gaigeshen
 */
public class DingClientCreator implements ClientCreator<DingConfig> {

    private static final Logger log = LoggerFactory.getLogger(DingClientCreator.class);

    private final AccessTokenManager<DingConfig> accessTokenManager;

    public DingClientCreator(AccessTokenManager<DingConfig> accessTokenManager) {
        ArgumentValidate.notNull(accessTokenManager, "accessTokenManager cannot be null");
        this.accessTokenManager = accessTokenManager;
    }

    @Override
    public Client<DingConfig> create(DingConfig config) throws ClientCreationException {
        log.info("creating ding client: {}", config);
        DefaultDingClient dingClient = DefaultDingClient.create(config, accessTokenManager);
        try {
            dingClient.init();
        } catch (Exception e) {
            throw new ClientCreationException(e.getMessage(), e);
        }
        return dingClient;
    }
}
