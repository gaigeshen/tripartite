package work.gaigeshen.tripartite.ding.openapi.parameters.robot;

import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;

import java.util.Collection;

/**
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class DingRobotGroupMessageRecallParameters implements ClientParameters {

    public String openConversationId;

    public String robotCode;

    public Collection<String> processQueryKeys;
}
