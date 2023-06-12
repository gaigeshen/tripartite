package work.gaigeshen.tripartite.openai.openapi.response.file;

import work.gaigeshen.tripartite.openai.openapi.response.OpenAiResponse;

/**
 *
 * @author gaigeshen
 */
public class OpenAiFileUploadResponse extends OpenAiResponse {

    public String id;

    public String object;

    public Long bytes;

    public Long created_at;

    public String filename;

    public String purpose;
}
