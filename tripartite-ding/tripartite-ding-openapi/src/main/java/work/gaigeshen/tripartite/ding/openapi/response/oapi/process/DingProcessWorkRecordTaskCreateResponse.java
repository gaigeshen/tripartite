package work.gaigeshen.tripartite.ding.openapi.response.oapi.process;

import work.gaigeshen.tripartite.ding.openapi.response.DingOapiResponse;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class DingProcessWorkRecordTaskCreateResponse extends DingOapiResponse {

    public Collection<Task> tasks;

    public static class Task {

        public Long task_id;

        public String userid;
    }
}
