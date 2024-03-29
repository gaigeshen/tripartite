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
public class DingUserByUserIdGetParameters extends DingOapiParameters {

    public String userid;

    public String language;
}
