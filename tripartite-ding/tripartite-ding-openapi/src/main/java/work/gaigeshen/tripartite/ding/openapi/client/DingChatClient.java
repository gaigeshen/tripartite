package work.gaigeshen.tripartite.ding.openapi.client;

import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.ClientException;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.parameters.chat.DingChatCreateParameters;
import work.gaigeshen.tripartite.ding.openapi.response.chat.DingChatCreateResponse;

/**
 * @author gaigeshen
 */
public interface DingChatClient extends Client<DingConfig> {

    DingChatCreateResponse chatCreate(DingChatCreateParameters parameters) throws ClientException;
}
