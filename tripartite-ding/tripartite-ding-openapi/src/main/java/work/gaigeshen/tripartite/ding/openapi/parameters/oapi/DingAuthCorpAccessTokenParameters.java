package work.gaigeshen.tripartite.ding.openapi.parameters.oapi;

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
public class DingAuthCorpAccessTokenParameters extends DingOapiParameters {

    public String accessKey;

    public String suiteTicket;

    public String signature;

    public String auth_corpid;

    public Long timestamp;
}
