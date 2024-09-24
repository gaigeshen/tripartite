package work.gaigeshen.tripartite.qyweixin.openapi.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.ClientCreationException;
import work.gaigeshen.tripartite.core.client.ClientCreator;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.qyweixin.openapi.config.QyWeixinConfig;

/**
 *
 * @author gaigeshen
 */
@RequiredArgsConstructor
@Slf4j
public class QyWeixinClientCreator implements ClientCreator<QyWeixinConfig> {

    private final AccessTokenManager<QyWeixinConfig> accessTokenManager;

    @Override
    public Client<QyWeixinConfig> create(QyWeixinConfig config) throws ClientCreationException {
        log.info("creating qyweixin client: {}", config);
        Client<QyWeixinConfig> qyWeixinClient;
        try {
            qyWeixinClient = new DefaultQyWeixinClient(config, accessTokenManager);
            qyWeixinClient.init();
        }
        catch (Exception e) {
            throw new ClientCreationException(e.getMessage(), e);
        }
        return qyWeixinClient;
    }
}
