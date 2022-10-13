package work.gaigeshen.tripartite.ding.openapi.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.client.AbstractClient;
import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.ClientCreationException;
import work.gaigeshen.tripartite.core.client.ClientCreator;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.ding.openapi.client.accesstoken.DingAccessTokenInterceptor;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class DingClientCreator implements ClientCreator<DingConfig> {

    private static final Logger log = LoggerFactory.getLogger(DingClientCreator.class);

    private final AccessTokenManager<DingConfig> accessTokenManager;

    public DingClientCreator(AccessTokenManager<DingConfig> accessTokenManager) {
        if (Objects.isNull(accessTokenManager)) {
            throw new IllegalArgumentException("access token manager cannot be null");
        }
        this.accessTokenManager = accessTokenManager;
    }

    @Override
    public Client<DingConfig> create(DingConfig config) throws ClientCreationException {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        log.info("creating ding client: {}", config);
        AbstractClient<DingConfig> accessTokenClient = new AbstractClient<DingConfig>(config) {};
        return DefaultDingClient.create(config, new DingAccessTokenInterceptor(accessTokenClient, accessTokenManager));
    }
}
