package work.gaigeshen.tripartite.ding.openapi.response.user;

import work.gaigeshen.tripartite.core.client.response.ClientResponse;

/**
 *
 * @author gaigeshen
 */
public class DingUserByMobileGetResponse implements ClientResponse {

    public Result result;

    public static class Result {

        public String userid;
    }
}
