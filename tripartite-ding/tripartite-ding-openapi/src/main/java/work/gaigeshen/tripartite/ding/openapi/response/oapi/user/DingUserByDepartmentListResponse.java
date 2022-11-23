package work.gaigeshen.tripartite.ding.openapi.response.oapi.user;

import work.gaigeshen.tripartite.ding.openapi.response.DingOapiResponse;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class DingUserByDepartmentListResponse extends DingOapiResponse {

    public Result result;

    public static class Result {

        public Boolean has_more;

        public Integer next_cursor;

        public Collection<ListItem> list;
    }

    public static class ListItem {

        public String userid;

        public String name;

        public String avatar;

        public String mobile;

        public String telephone;

        public String job_number;

        public String title;

        public String email;

        public String remark;

        public Boolean active;

        public Boolean admin;

        public Boolean boss;

        public Boolean leader;
    }
}
