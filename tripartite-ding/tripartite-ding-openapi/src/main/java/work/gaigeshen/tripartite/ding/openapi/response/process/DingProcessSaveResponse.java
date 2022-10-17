package work.gaigeshen.tripartite.ding.openapi.response.process;

import work.gaigeshen.tripartite.core.client.response.ClientResponse;

/**
 * @author gaigeshen
 */
public class DingProcessSaveResponse implements ClientResponse {

    public Result result;

    public static class Result {

        public String process_code;
    }
}
