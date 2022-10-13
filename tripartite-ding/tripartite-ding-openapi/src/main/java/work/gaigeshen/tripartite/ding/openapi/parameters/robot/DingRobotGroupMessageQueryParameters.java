package work.gaigeshen.tripartite.ding.openapi.parameters.robot;

import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class DingRobotGroupMessageQueryParameters implements ClientParameters {

    private String openConversationId;

    private String robotCode;

    private String processQueryKey;

    private String nextToken;

    private Long maxResults;

    public String getOpenConversationId() {
        return openConversationId;
    }

    public void setOpenConversationId(String openConversationId) {
        this.openConversationId = openConversationId;
    }

    public String getRobotCode() {
        return robotCode;
    }

    public void setRobotCode(String robotCode) {
        this.robotCode = robotCode;
    }

    public String getProcessQueryKey() {
        return processQueryKey;
    }

    public void setProcessQueryKey(String processQueryKey) {
        this.processQueryKey = processQueryKey;
    }

    public String getNextToken() {
        return nextToken;
    }

    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }

    public Long getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Long maxResults) {
        this.maxResults = maxResults;
    }
}
