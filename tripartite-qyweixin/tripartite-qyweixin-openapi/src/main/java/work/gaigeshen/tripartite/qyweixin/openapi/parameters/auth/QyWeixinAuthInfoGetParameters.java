package work.gaigeshen.tripartite.qyweixin.openapi.parameters.auth;

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
public class QyWeixinAuthInfoGetParameters extends QyWeixinParameters {

    public String auth_corpid;

    public String permanent_code;
}
