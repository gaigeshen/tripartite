package work.gaigeshen.tripartite.openai.openapi.client;

import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.ClientException;
import work.gaigeshen.tripartite.openai.openapi.config.OpenAiConfig;
import work.gaigeshen.tripartite.openai.openapi.parameters.chat.OpenAiChatCompletionsParameters;
import work.gaigeshen.tripartite.openai.openapi.response.chat.OpenAiChatCompletionsResponse;

public interface OpenAiClient extends Client<OpenAiConfig> {

    default OpenAiChatCompletionsResponse chatCompletions(OpenAiChatCompletionsParameters parameters) throws ClientException {
        return execute(parameters, OpenAiChatCompletionsResponse.class, "/v1/chat/completions");
    }
}
