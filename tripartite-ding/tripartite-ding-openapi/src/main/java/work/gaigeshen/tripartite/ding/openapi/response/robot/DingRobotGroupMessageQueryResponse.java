package work.gaigeshen.tripartite.ding.openapi.response.robot;

import work.gaigeshen.tripartite.core.client.response.ClientResponse;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class DingRobotGroupMessageQueryResponse implements ClientResponse {

    private String sendStatus;

    private Collection<String> readUserIds;

    private String nextToken;

    private Boolean hasMore;

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Collection<String> getReadUserIds() {
        return readUserIds;
    }

    public void setReadUserIds(Collection<String> readUserIds) {
        this.readUserIds = readUserIds;
    }

    public String getNextToken() {
        return nextToken;
    }

    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
