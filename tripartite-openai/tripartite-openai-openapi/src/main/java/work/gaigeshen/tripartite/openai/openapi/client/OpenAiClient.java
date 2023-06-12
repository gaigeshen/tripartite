package work.gaigeshen.tripartite.openai.openapi.client;

import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.ClientException;
import work.gaigeshen.tripartite.openai.openapi.config.OpenAiConfig;
import work.gaigeshen.tripartite.openai.openapi.parameters.chat.OpenAiChatCompletionsParameters;
import work.gaigeshen.tripartite.openai.openapi.response.chat.OpenAiChatCompletionsResponse;

/**
 *
 * @author gaigeshen
 */
public interface OpenAiClient extends Client<OpenAiConfig> {

    /**
     * 为给定的聊天对话创建模型响应
     *
     * @param parameters 聊天对话参数
     * @return 模型响应
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://platform.openai.com/docs/api-reference/chat/create">接口文档</a>
     */
    default OpenAiChatCompletionsResponse chatCompletions(OpenAiChatCompletionsParameters parameters) throws ClientException {
        return execute(parameters, OpenAiChatCompletionsResponse.class, "/v1/chat/completions");
    }
}
