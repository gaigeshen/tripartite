package work.gaigeshen.tripartite.ding.openapi.response.oapi.process;

import work.gaigeshen.tripartite.ding.openapi.response.DingOapiResponse;

/**
 * @author gaigeshen
 */
public class DingProcessWorkRecordCreateResponse extends DingOapiResponse {

    public Result result;

    public static class Result {

        public String process_instance_id;
    }
}
