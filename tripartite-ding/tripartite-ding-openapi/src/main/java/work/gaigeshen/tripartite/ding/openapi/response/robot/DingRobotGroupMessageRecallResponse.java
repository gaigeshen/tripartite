package work.gaigeshen.tripartite.ding.openapi.response.robot;

import work.gaigeshen.tripartite.core.client.response.ClientResponse;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class DingRobotGroupMessageRecallResponse implements ClientResponse {

    private Collection<String> successResult;

    public Collection<String> getSuccessResult() {
        return successResult;
    }

    public void setSuccessResult(Collection<String> successResult) {
        this.successResult = successResult;
    }
}
