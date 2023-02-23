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
public class DingMessageAsyncSendParameters extends DingOapiParameters {

    public Long agent_id;

    public String userid_list;

    public String dept_id_list;

    public Boolean to_all_user;

    public Message msg;

    public static class Message {

        public String msgtype;

        public Markdown markdown;

        public OA oa;
    }

    public static class Markdown {

        public String title;

        public String text;
    }

    public static class OA {

        public String message_url;

        public String pc_message_url;

        public OAHead head;

        public OABody body;
    }

    public static class OAHead {

        public String bgcolor;

        public String text;
    }

    public static class OABody {

        public String title;

        public String content;
    }
}
