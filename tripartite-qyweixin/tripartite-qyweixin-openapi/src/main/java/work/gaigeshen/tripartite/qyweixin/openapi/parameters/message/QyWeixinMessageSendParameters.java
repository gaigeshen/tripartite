package work.gaigeshen.tripartite.qyweixin.openapi.parameters.message;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.qyweixin.openapi.parameters.QyWeixinParameters;

import java.util.Collection;

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

    public MiniprogramNotice miniprogram_notice;

    public static class Textcard {

        public String title;

        public String description;

        public String url;
    }

    public static class Markdown {

        public String content;
    }

    public static class MiniprogramNotice {

        public String appid;

        public String page;

        public String title;

        public String description;

        public Boolean emphasis_first_item;

        public Collection<MiniprogramNoticeItem> content_item;
    }

    public static class MiniprogramNoticeItem {

        public String key;

        public String value;
    }
}
