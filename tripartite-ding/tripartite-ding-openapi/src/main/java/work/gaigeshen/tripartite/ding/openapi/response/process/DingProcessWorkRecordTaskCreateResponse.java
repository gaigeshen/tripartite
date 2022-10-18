package work.gaigeshen.tripartite.ding.openapi.response.process;

import work.gaigeshen.tripartite.core.client.response.ClientResponse;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class DingProcessWorkRecordTaskCreateResponse implements ClientResponse {

    public Collection<Task> tasks;

    public static class Task {

        public Long task_id;

        public String userid;
    }
}
