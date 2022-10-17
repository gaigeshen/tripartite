package work.gaigeshen.tripartite.ding.openapi.response.robot;

import work.gaigeshen.tripartite.core.client.response.ClientResponse;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class DingRobotGroupMessageQueryResponse implements ClientResponse {

    public String sendStatus;

    public Collection<String> readUserIds;

    public String nextToken;

    public Boolean hasMore;
}
