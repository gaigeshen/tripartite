package work.gaigeshen.tripartite.qyweixin.openapi.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.ClientCreationException;
import work.gaigeshen.tripartite.core.client.ClientCreator;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.qyweixin.openapi.config.QyWeixinConfig;

/**
 *
 * @author gaigeshen
 */
public class QyWexinClientCreator implements ClientCreator<QyWeixinConfig> {

    private static final Logger log = LoggerFactory.getLogger(QyWexinClientCreator.class);

    private final AccessTokenManager<QyWeixinConfig> accessTokenManager;

    public QyWexinClientCreator(AccessTokenManager<QyWeixinConfig> accessTokenManager) {
        ArgumentValidate.notNull(accessTokenManager, "accessTokenManager cannot be null");
        this.accessTokenManager = accessTokenManager;
    }

    @Override
    public Client<QyWeixinConfig> create(QyWeixinConfig config) throws ClientCreationException {
        log.info("creating qyweixin client: {}", config);
        DefaultQyWeixinClient dingClient = DefaultQyWeixinClient.create(config, accessTokenManager);
        try {
            dingClient.init();
        } catch (Exception e) {
            throw new ClientCreationException(e.getMessage(), e);
        }
        return dingClient;
    }
}
