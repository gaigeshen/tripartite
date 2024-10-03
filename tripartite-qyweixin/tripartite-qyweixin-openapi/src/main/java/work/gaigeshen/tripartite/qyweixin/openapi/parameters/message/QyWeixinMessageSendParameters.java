package work.gaigeshen.tripartite.qyweixin.openapi.parameters.message;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.qyweixin.openapi.parameters.QyWeixinParameters;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class QyWeixinMessageSendParameters extends QyWeixinParameters {

    public String touser;

    public String toparty;

    public String totag;

    public String msgtype;

    public Integer agentid;

    public Textcard textcard;

    public Markdown markdown;

    public static class Textcard {

        public String title;

        public String description;

        public String url;
    }

    public static class Markdown {

        public String content;
    }

}
