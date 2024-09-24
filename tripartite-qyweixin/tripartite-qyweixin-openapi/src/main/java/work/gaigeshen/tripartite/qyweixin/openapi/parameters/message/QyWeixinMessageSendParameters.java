package work.gaigeshen.tripartite.qyweixin.openapi.parameters.message;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.qyweixin.openapi.parameters.QyWeixinParameters;

import java.util.List;

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

    public Markdown markdown;

    public MiniprogramNotice miniprogram_notice;

    public static class Markdown {

        public String content;
    }

    public static class MiniprogramNotice {

        public String appid;

        public String page;

        public String title;

        public String description;

        public Boolean emphasis_first_item;

        public List<MiniprogramNoticeContentItem> content_item;

        public Integer enable_id_trans;

        public Integer enable_duplicate_check;

        public Integer duplicate_check_interval;
    }

    public static class MiniprogramNoticeContentItem {

        public String key;

        public String value;
    }
}
