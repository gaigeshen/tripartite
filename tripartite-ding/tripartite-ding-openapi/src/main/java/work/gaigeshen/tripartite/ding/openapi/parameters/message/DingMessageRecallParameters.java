package work.gaigeshen.tripartite.ding.openapi.parameters.message;

import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class DingMessageRecallParameters implements ClientParameters {

    public Long agent_id;

    public Long msg_task_id;
}
