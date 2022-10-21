package work.gaigeshen.tripartite.ding.openapi.parameters.api.robot;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingApiParameters;

/**
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class DingRobotGroupMessageSendParameters extends DingApiParameters {

    public String msgParam;

    public String msgKey;

    public String openConversationId;

    public String robotCode;
}
