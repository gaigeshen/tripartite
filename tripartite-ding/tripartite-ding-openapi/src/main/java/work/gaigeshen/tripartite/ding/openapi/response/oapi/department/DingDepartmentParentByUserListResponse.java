package work.gaigeshen.tripartite.ding.openapi.response.oapi.department;

import work.gaigeshen.tripartite.ding.openapi.response.DingOapiResponse;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class DingDepartmentParentByUserListResponse extends DingOapiResponse {

    public Result result;

    public static class Result {

        public Collection<Parent> parent_list;
    }

    public static class Parent {

        public Collection<Long> parent_dept_id_list;
    }
}
