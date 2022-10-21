package work.gaigeshen.tripartite.ding.openapi.parameters.api.robot;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingApiParameters;

import java.util.Collection;

/**
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class DingRobotGroupMessageRecallParameters extends DingApiParameters {

    public String openConversationId;

    public String robotCode;

    public Collection<String> processQueryKeys;
}
