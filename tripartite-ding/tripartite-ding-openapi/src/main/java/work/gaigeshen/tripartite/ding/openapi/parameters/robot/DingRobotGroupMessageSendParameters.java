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

    private String msgParam;

    private String msgKey;

    private String openConversationId;

    private String robotCode;

    public String getMsgParam() {
        return msgParam;
    }

    public void setMsgParam(String msgParam) {
        this.msgParam = msgParam;
    }

    public String getMsgKey() {
        return msgKey;
    }

    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey;
    }

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
}
