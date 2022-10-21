package work.gaigeshen.tripartite.ding.openapi.parameters.api.robot;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingApiParameters;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class DingRobotGroupMessageQueryParameters extends DingApiParameters {

    public String openConversationId;

    public String robotCode;

    public String processQueryKey;

    public String nextToken;

    public Long maxResults;
}
