package work.gaigeshen.tripartite.ding.openapi.client;

import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.ClientException;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.parameters.user.DingUserByMobileGetParameters;
import work.gaigeshen.tripartite.ding.openapi.response.user.DingUserByMobileGetResponse;

/**
 *
 * @author gaigeshen
 */
public interface DingUserClient extends Client<DingConfig> {

    DingUserByMobileGetResponse userGetByMobile(DingUserByMobileGetParameters parameters) throws ClientException;
}
