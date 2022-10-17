package work.gaigeshen.tripartite.ding.openapi.parameters.robot;

import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;

/**
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class DingRobotGroupMessageSendParameters implements ClientParameters {

    public String msgParam;

    public String msgKey;

    public String openConversationId;

    public String robotCode;
}
