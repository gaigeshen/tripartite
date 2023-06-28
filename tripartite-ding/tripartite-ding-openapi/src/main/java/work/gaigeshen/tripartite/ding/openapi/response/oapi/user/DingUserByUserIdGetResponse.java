package work.gaigeshen.tripartite.ding.openapi.response.oapi.user;

import work.gaigeshen.tripartite.ding.openapi.response.DingOapiResponse;

/**
 *
 * @author gaigeshen
 */
public class DingUserByUserIdGetResponse extends DingOapiResponse {

    public Result result;

    public static class Result {

        public String userid;

        public String unionid;

        public String name;

        public String avatar;

        public String mobile;

        public String telephone;

        public String title;

        public String email;
    }
}
