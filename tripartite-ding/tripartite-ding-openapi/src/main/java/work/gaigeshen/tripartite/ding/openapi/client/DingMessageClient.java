package work.gaigeshen.tripartite.ding.openapi.client;

import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.ClientException;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.parameters.message.DingMessageAsyncSendParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.message.DingMessageRecallParameters;
import work.gaigeshen.tripartite.ding.openapi.response.message.DingMessageAsyncSendResponse;
import work.gaigeshen.tripartite.ding.openapi.response.message.DingMessageRecallResponse;

/**
 * @author gaigeshen
 */
public interface DingMessageClient extends Client<DingConfig> {

    DingMessageAsyncSendResponse messageAsyncSend(DingMessageAsyncSendParameters parameters) throws ClientException;

    DingMessageRecallResponse messageRecall(DingMessageRecallParameters parameters) throws ClientException;
}
