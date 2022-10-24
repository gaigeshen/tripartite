package work.gaigeshen.tripartite.ding.openapi.parameters.oapi.message;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingOapiParameters;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class DingMessageRecallParameters extends DingOapiParameters {

    public Long agent_id;

    public Long msg_task_id;
}
