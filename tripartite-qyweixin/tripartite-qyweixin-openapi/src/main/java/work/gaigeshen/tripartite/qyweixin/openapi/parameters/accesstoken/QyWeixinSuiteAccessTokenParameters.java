package work.gaigeshen.tripartite.qyweixin.openapi.parameters.accesstoken;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.qyweixin.openapi.parameters.QyWeixinParameters;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class QyWeixinSuiteAccessTokenParameters extends QyWeixinParameters {

    public String suite_id;

    public String suite_secret;

    public String suite_ticket;
}
