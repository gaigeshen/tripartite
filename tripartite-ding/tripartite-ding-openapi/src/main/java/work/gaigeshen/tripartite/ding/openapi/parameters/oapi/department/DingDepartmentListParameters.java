package work.gaigeshen.tripartite.ding.openapi.parameters.oapi.department;

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
public class DingDepartmentListParameters extends DingOapiParameters {

    public Long dept_id;

    public String language;
}
