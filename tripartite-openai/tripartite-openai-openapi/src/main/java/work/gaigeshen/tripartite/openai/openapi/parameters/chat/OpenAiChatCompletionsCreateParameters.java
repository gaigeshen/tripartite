package work.gaigeshen.tripartite.openai.openapi.parameters.chat;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.openai.openapi.parameters.OpenAiParameters;

import java.util.List;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class OpenAiChatCompletionsCreateParameters extends OpenAiParameters {

    public String model;

    public List<Message> messages;

    public static class Message {

        public String role;

        public String content;
    }
}
