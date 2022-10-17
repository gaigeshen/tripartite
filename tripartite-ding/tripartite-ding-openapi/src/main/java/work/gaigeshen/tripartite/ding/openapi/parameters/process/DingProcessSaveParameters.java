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
public class DingProcessSaveParameters implements ClientParameters {

    public SaveProcessRequest saveProcessRequest;

    public static class SaveProcessRequest {

        public Integer agentid;

        public String process_code;

        public String name;

        public String description;

        public Collection<FormComponent> form_component_list;

        public Boolean fake_mode;
    }

    public static class FormComponent {

        public String component_name;

        public Collection<FormComponentProperty> props;
    }

    public static class FormComponentProperty {

        public String id;

        public String label;

        public Boolean required;
    }
}