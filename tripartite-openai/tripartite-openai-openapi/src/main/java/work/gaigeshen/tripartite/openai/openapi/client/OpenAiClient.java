package work.gaigeshen.tripartite.openai.openapi.client;

import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.ClientException;
import work.gaigeshen.tripartite.openai.openapi.config.OpenAiConfig;
import work.gaigeshen.tripartite.openai.openapi.parameters.chat.OpenAiChatCompletionsCreateParameters;
import work.gaigeshen.tripartite.openai.openapi.parameters.embeddings.OpenAiEmbeddingsCreateParameters;
import work.gaigeshen.tripartite.openai.openapi.parameters.file.OpenAiFileUploadParameters;
import work.gaigeshen.tripartite.openai.openapi.response.chat.OpenAiChatCompletionsCreateResponse;
import work.gaigeshen.tripartite.openai.openapi.response.embeddings.OpenAiEmbeddingsCreateResponse;
import work.gaigeshen.tripartite.openai.openapi.response.file.OpenAiFileUploadResponse;

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
    default OpenAiChatCompletionsCreateResponse createChatCompletions(OpenAiChatCompletionsCreateParameters parameters) throws ClientException {
        return execute(parameters, OpenAiChatCompletionsCreateResponse.class, "/v1/chat/completions");
    }

    /**
     * 获取给定输入的矢量表示
     *
     * @param parameters 给定的输入参数
     * @return 矢量表示响应
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://platform.openai.com/docs/api-reference/embeddings/create">接口文档</a>
     */
    default OpenAiEmbeddingsCreateResponse createEmbeddings(OpenAiEmbeddingsCreateParameters parameters) throws ClientException {
        return execute(parameters, OpenAiEmbeddingsCreateResponse.class, "/v1/embeddings");
    }

    /**
     * 文件上传
     *
     * @param parameters 文件上传参数
     * @return 文件上传结果
     * @throws ClientException 执行请求的时候发生异常
     * @see <a href="https://platform.openai.com/docs/api-reference/files/upload">接口文档</a>
     */
    default OpenAiFileUploadResponse uploadFile(OpenAiFileUploadParameters parameters) throws ClientException {
        return execute(parameters, OpenAiFileUploadResponse.class, "/v1/files");
    }
}
