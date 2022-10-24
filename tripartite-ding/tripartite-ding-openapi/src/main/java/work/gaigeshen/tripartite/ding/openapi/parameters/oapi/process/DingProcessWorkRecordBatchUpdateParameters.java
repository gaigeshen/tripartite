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
public class DingProcessWorkRecordBatchUpdateParameters extends DingOapiParameters {

    public Request request;

    public static class Request {

        public Collection<Instance> instances;

        public Long agentid;
    }

    public static class Instance {

        public String process_instance_id;

        public String status;

        public String result;
    }
}
