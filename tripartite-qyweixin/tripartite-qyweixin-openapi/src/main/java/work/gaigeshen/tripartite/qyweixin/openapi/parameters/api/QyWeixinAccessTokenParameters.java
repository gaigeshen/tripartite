package work.gaigeshen.tripartite.qyweixin.openapi.parameters.api;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.qyweixin.openapi.parameters.QyWeixinApiParameters;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class QyWeixinAccessTokenParameters extends QyWeixinApiParameters {

    private String corpId;

    private String corpSecret;
}
