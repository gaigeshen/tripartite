package work.gaigeshen.tripartite.openai.openapi.parameters.embeddings;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.openai.openapi.parameters.OpenAiParameters;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class OpenAiEmbeddingsCreateParameters extends OpenAiParameters {

    public String model;

    public String input;
}
