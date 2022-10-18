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
public class DingProcessWorkRecordTaskCreateParameters implements ClientParameters {

    public Request request;

    public static class Request {

        public Long agentid;

        public String process_instance_id;

        public String activity_id;

        public Collection<Task> tasks;
    }

    public static class Task {

        public String userid;

        public String url;
    }
}
