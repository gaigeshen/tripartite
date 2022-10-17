package work.gaigeshen.tripartite.ding.openapi.parameters.process;

import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class DingProcessWorkRecordTaskUpdateParameters implements ClientParameters {

    public Request request;

    public static class Request {

        public Integer agentid;

        public String process_instance_id;

        public Collection<Task> tasks;
    }

    public static class Task {

        public Integer task_id;

        public String status;

        public String result;
    }
}
