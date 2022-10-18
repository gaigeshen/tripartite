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
public class DingMessageAsyncSendParameters implements ClientParameters {

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
