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
public class DingAuthCorpAccessTokenParameters extends DingApiParameters {

    public String suiteKey;

    public String suiteSecret;

    public String authCorpId;

    public String suiteTicket;
}
