package work.gaigeshen.tripartite.qyweixin.openapi.parameters.api.user;

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
public class QyWeixinUserIdByMobileGetParameters extends QyWeixinApiParameters {

    public String mobile;
}
