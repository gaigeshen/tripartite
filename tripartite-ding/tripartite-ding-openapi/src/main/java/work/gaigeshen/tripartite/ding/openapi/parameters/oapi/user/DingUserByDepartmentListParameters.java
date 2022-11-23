package work.gaigeshen.tripartite.ding.openapi.parameters.oapi.user;

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
public class DingUserByDepartmentListParameters extends DingOapiParameters {

    public Long dept_id;

    public Integer cursor;

    public Integer size;
}
