package work.gaigeshen.tripartite.ding.openapi.response.oapi.process;

import work.gaigeshen.tripartite.ding.openapi.response.DingOapiResponse;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class DingProcessWorkRecordTaskQueryResponse extends DingOapiResponse {

    public Result result;

    public static class Result {

        public Boolean has_more;

        public Collection<Task> list;
    }

    public static class Task {

        public String instance_id;

        public Long task_id;

        public String title;

        public String url;

        public Collection<Form> forms;
    }

    public static class Form {

        public String content;

        public String title;
    }
}
