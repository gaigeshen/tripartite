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
public class DingProcessWorkRecordCreateParameters extends DingOapiParameters {

    public Request request;

    public static class Request {

        public Long agentid;

        public String process_code;

        public String originator_user_id;

        public Collection<FormComponentValue> form_component_values;

        public String url;

        public String title;
    }

    public static class FormComponentValue {

        public String name;

        public String value;
    }
}
