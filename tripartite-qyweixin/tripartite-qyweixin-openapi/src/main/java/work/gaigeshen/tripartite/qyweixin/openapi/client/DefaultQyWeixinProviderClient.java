package work.gaigeshen.tripartite.qyweixin.openapi.client;

import work.gaigeshen.tripartite.core.client.ClientException;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessToken;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenHelper;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.qyweixin.openapi.config.QyWeixinConfig;
import work.gaigeshen.tripartite.qyweixin.openapi.parameters.accesstoken.QyWeixinProviderAccessTokenParameters;
import work.gaigeshen.tripartite.qyweixin.openapi.parameters.accesstoken.QyWeixinSuiteAccessTokenParameters;
import work.gaigeshen.tripartite.qyweixin.openapi.response.accesstoken.QyWeixinProviderAccessTokenResponse;
import work.gaigeshen.tripartite.qyweixin.openapi.response.accesstoken.QyWeixinSuiteAccessTokenResponse;
import work.gaigeshen.tripartite.qyweixin.openapi.suite.QyWeixinSuiteTicket;
import work.gaigeshen.tripartite.qyweixin.openapi.suite.QyWeixinSuiteTicketStore;

import java.util.Objects;

/**
 * 企业微信服务商接口客户端的实现
 *
 * @author gaigeshen
 */
public class DefaultQyWeixinProviderClient extends AbstractQyWeixinClient implements QyWeixinProviderClient {

    private final AccessTokenManager<QyWeixinConfig> suiteAccessTokenManager;

    private final QyWeixinSuiteTicketStore<QyWeixinConfig> suiteTicketStore;

    protected DefaultQyWeixinProviderClient(QyWeixinConfig config, AccessTokenManager<QyWeixinConfig> accessTokenManager,
                                            AccessTokenManager<QyWeixinConfig> suiteAccessTokenManager,
                                            QyWeixinSuiteTicketStore<QyWeixinConfig> suiteTicketStore) {
        super(config, accessTokenManager);
        ArgumentValidate.notNull(suiteAccessTokenManager, "suiteAccessTokenManager cannot be null");
        ArgumentValidate.notNull(suiteTicketStore, "suiteTicketStore cannot be null");
        this.suiteAccessTokenManager = suiteAccessTokenManager;
        this.suiteTicketStore = suiteTicketStore;
    }

    public AccessToken getNewAccessToken() throws ClientException {
        QyWeixinConfig config = getConfig();
        String path = "/cgi-bin/service/get_provider_token";
        QyWeixinProviderAccessTokenParameters parameters = new QyWeixinProviderAccessTokenParameters();
        parameters.corpId = config.getCorpId();
        parameters.providerSecret = config.getCorpSecret();
        QyWeixinProviderAccessTokenResponse response = execute(parameters, QyWeixinProviderAccessTokenResponse.class, path);
        String accessToken = response.provider_access_token;
        Long expireIn = response.expires_in;
        if (Objects.isNull(accessToken) || Objects.isNull(expireIn)) {
            throw new ClientException("acquired provider access token is invalid: " + config);
        }
        return AccessTokenHelper.createAccessToken(config, accessToken, expireIn);
    }

    public AccessToken getNewSuiteAccessToken() throws ClientException {
        QyWeixinConfig config = getConfig();
        QyWeixinSuiteTicket suiteTicket = suiteTicketStore.find(config);
        if (Objects.isNull(suiteTicket)) {
            throw new ClientException("suite ticket not found: " + config);
        }
        String path = "/cgi-bin/service/get_suite_token";
        QyWeixinSuiteAccessTokenParameters parameters = new QyWeixinSuiteAccessTokenParameters();
        parameters.suite_id = config.getSuiteId();
        parameters.suite_secret = config.getSuiteSecret();
        parameters.suite_ticket = config.getSuiteSecret();
        QyWeixinSuiteAccessTokenResponse response = execute(parameters, QyWeixinSuiteAccessTokenResponse.class, path);
        String accessToken = response.suite_access_token;
        Long expireIn = response.expires_in;
        if (Objects.isNull(accessToken) || Objects.isNull(expireIn)) {
            throw new ClientException("acquired suite access token is invalid: " + config);
        }
        return AccessTokenHelper.createAccessToken(config, accessToken, expireIn);
    }

    @Override
    public AccessTokenManager<QyWeixinConfig> getSuiteAccessTokenManager() {
        return suiteAccessTokenManager;
    }

    @Override
    public void setNewSuiteTicket(QyWeixinSuiteTicket suiteTicket) {
        QyWeixinConfig config = getConfig();
        if (suiteTicketStore.save(config, suiteTicket)) {
            AccessToken newSuiteAccessToken = getNewSuiteAccessToken();
            suiteAccessTokenManager.addNewAccessToken(config, newSuiteAccessToken);
        }
    }
}
