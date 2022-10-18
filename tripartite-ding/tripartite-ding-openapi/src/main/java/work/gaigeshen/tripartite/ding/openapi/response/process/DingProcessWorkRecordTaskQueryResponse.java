package work.gaigeshen.tripartite.ding.openapi.response.process;

import work.gaigeshen.tripartite.core.client.response.ClientResponse;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class DingProcessWorkRecordTaskQueryResponse implements ClientResponse {

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
