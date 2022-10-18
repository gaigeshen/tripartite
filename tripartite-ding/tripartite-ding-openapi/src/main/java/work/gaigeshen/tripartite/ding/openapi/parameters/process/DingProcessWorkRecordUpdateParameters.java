package work.gaigeshen.tripartite.ding.openapi.parameters.process;

import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class DingProcessWorkRecordUpdateParameters implements ClientParameters {

    public Request request;

    public static class Request {

        public Long agentid;

        public String process_instance_id;

        public String status;

        public String result;

        public Boolean cancel_running_task;
    }
}
