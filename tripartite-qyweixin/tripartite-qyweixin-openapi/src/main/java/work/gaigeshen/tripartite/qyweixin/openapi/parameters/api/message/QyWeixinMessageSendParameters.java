package work.gaigeshen.tripartite.qyweixin.openapi.parameters.api.message;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.qyweixin.openapi.parameters.QyWeixinApiParameters;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class QyWeixinMessageSendParameters extends QyWeixinApiParameters {

    public String touser;

    public String toparty;

    public String totag;

    public String msgtype;

    public Integer agentid;

    public Markdown markdown;

    public static class Markdown {

        public String content;
    }
}
