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

    private String openConversationId;

    private String robotCode;

    private Collection<String> processQueryKeys;

    public String getOpenConversationId() {
        return openConversationId;
    }

    public void setOpenConversationId(String openConversationId) {
        this.openConversationId = openConversationId;
    }

    public String getRobotCode() {
        return robotCode;
    }

    public void setRobotCode(String robotCode) {
        this.robotCode = robotCode;
    }

    public Collection<String> getProcessQueryKeys() {
        return processQueryKeys;
    }

    public void setProcessQueryKeys(Collection<String> processQueryKeys) {
        this.processQueryKeys = processQueryKeys;
    }
}
