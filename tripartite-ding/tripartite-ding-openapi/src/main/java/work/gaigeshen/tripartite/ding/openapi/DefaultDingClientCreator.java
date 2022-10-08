package work.gaigeshen.tripartite.ding.openapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.ding.openapi.accesstoken.DingAccessTokenManager;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.Objects;

/**
 * @author gaigeshen
 */
public class DefaultDingClientCreator implements DingClientCreator {

    private static final Logger log = LoggerFactory.getLogger(DefaultDingClientCreator.class);

    private final DingAccessTokenManager accessTokenManager;

    public DefaultDingClientCreator(DingAccessTokenManager accessTokenManager) {
        if (Objects.isNull(accessTokenManager)) {
            throw new IllegalArgumentException("accessTokenManager cannot be null");
        }
        this.accessTokenManager = accessTokenManager;
    }

    @Override
    public DingClient create(DingConfig config) throws DingClientCreationException {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        DingAccessTokenClient accessTokenClient = DingAccessTokenClient.create(config);
        log.info("creating ding client: {}", config);
        return DefaultDingClient.create(config);
    }
}
