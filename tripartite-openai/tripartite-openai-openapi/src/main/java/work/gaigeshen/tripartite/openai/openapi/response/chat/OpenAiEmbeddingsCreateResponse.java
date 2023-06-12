package work.gaigeshen.tripartite.openai.openapi.response.chat;

import work.gaigeshen.tripartite.openai.openapi.response.OpenAiResponse;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author gaigeshen
 */
public class OpenAiEmbeddingsCreateResponse extends OpenAiResponse {

    public String object;

    public List<Data> data;

    public String model;

    public Usage usage;

    public static class Data {

        public String object;

        public List<BigDecimal> embedding;

        public Integer index;
    }

    public static class Usage {

        public Long prompt_tokens;

        public Long total_tokens;
    }
}
