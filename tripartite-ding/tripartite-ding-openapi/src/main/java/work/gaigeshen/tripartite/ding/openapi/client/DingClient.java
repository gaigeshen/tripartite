package work.gaigeshen.tripartite.ding.openapi.client;

import work.gaigeshen.tripartite.core.client.ClientException;
import work.gaigeshen.tripartite.ding.openapi.config.DingAuthCorpContexts;
import work.gaigeshen.tripartite.ding.openapi.config.DingAuthCorpContexts.Context;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.Objects;

/**
 * 钉钉接口客户端
 *
 * @author gaigeshen
 */
public interface DingClient extends DingApiClient, DingOapiClient {

    default String getAuthCorpId() {
        Context authCorpContext = getAuthCorpContext();
        if (Objects.isNull(authCorpContext)) {
            return null;
        }
        return authCorpContext.getAuthCorpId();
    }

    default boolean hasAuthCorpContext() {
        return Objects.nonNull(getAuthCorpContext());
    }

    default Context getAuthCorpContext() {
        return DingAuthCorpContexts.getContextLocal();
    }

    default String getSuiteTicket() throws ClientException {
        DingConfig config = getConfig();
        if (Objects.isNull(config.getSuiteId())) {
            throw new ClientException("suite id not found: " + config);
        }
        DingSuiteTicketStore suiteTicketStore = getSuiteTicketStore();
        if (Objects.isNull(suiteTicketStore)) {
            throw new ClientException("suite ticket store not found: " + config);
        }
        return suiteTicketStore.getTicket(config.getSuiteId());
    }

    default boolean hasSuiteTicketStore() {
        return Objects.nonNull(getSuiteTicketStore());
    }

    default DingSuiteTicketStore getSuiteTicketStore() throws ClientException {
        return null;
    }
}
