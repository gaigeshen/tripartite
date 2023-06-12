package work.gaigeshen.tripartite.openai.openapi.parameters.file;

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
public class OpenAiFileUploadParameters extends OpenAiParameters {

    public String file;

    public String purpose;
}
