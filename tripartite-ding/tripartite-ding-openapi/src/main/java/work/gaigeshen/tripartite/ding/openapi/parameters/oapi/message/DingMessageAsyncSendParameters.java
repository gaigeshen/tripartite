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
    }

    public static class Markdown {

        public String title;

        public String text;
    }
}
