package work.gaigeshen.tripartite.ding.openapi.response.oapi.user;

import work.gaigeshen.tripartite.ding.openapi.response.DingOapiResponse;

/**
 *
 * @author gaigeshen
 */
public class DingUserIdGetResponse extends DingOapiResponse {

    public Result result;

    public static class Result {

        public String userid;
    }
}
