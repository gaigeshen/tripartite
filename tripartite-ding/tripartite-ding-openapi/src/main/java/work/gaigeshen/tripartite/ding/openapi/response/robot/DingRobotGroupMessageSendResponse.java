package work.gaigeshen.tripartite.ding.openapi.response.robot;

import work.gaigeshen.tripartite.core.client.response.ClientResponse;

/**
 *
 * @author gaigeshen
 */
public class DingRobotGroupMessageSendResponse implements ClientResponse {

    private String processQueryKey;

    public String getProcessQueryKey() {
        return processQueryKey;
    }

    public void setProcessQueryKey(String processQueryKey) {
        this.processQueryKey = processQueryKey;
    }
}
