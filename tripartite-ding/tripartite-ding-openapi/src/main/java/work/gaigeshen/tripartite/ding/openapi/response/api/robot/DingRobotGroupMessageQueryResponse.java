package work.gaigeshen.tripartite.ding.openapi.response.api.robot;

import work.gaigeshen.tripartite.ding.openapi.response.DingApiResponse;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class DingRobotGroupMessageQueryResponse extends DingApiResponse {

    public String sendStatus;

    public Collection<String> readUserIds;

    public String nextToken;

    public Boolean hasMore;
}
