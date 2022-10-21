package work.gaigeshen.tripartite.ding.openapi.parameters.api;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingApiParameters;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class DingAccessTokenParameters extends DingApiParameters {

    public String appKey;

    public String appSecret;
}
