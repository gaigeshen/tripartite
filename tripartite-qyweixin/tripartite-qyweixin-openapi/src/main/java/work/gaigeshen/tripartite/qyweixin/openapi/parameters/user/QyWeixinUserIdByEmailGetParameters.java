package work.gaigeshen.tripartite.qyweixin.openapi.parameters.user;

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
public class QyWeixinUserIdByEmailGetParameters extends QyWeixinParameters {

    public String email;

    public Integer email_type;
}
