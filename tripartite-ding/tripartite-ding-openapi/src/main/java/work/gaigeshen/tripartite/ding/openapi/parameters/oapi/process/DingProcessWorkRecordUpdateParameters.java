package work.gaigeshen.tripartite.ding.openapi.parameters.oapi.process;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingOapiParameters;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class DingProcessWorkRecordUpdateParameters extends DingOapiParameters {

    public Request request;

    public static class Request {

        public Long agentid;

        public String process_instance_id;

        public String status;

        public String result;

        public Boolean cancel_running_task;
    }
}
