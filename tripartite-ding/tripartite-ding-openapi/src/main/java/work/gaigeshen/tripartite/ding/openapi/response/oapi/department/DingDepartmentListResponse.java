package work.gaigeshen.tripartite.ding.openapi.response.oapi.department;

import work.gaigeshen.tripartite.ding.openapi.response.DingOapiResponse;

/**
 *
 * @author gaigeshen
 */
public class DingDepartmentListResponse extends DingOapiResponse {

    public Result result;

    public static class Result {

        public Long dept_id;

        public Long parent_id;

        public String name;
    }
}
