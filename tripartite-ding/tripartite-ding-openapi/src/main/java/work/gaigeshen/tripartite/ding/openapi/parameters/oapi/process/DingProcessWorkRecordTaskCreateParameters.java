package work.gaigeshen.tripartite.ding.openapi.parameters.oapi.process;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingOapiParameters;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class DingProcessWorkRecordTaskCreateParameters extends DingOapiParameters {

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
