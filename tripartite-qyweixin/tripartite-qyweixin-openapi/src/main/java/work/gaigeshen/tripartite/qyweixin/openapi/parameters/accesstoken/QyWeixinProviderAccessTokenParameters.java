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
public class QyWeixinProviderAccessTokenParameters extends QyWeixinParameters {

    public String corpId;

    public String providerSecret;
}
