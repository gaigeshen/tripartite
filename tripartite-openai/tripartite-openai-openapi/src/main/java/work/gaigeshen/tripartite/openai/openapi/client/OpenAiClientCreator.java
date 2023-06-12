package work.gaigeshen.tripartite.openai.openapi.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.ClientCreationException;
import work.gaigeshen.tripartite.core.client.ClientCreator;
import work.gaigeshen.tripartite.openai.openapi.config.OpenAiConfig;

/**
 *
 * @author gaigeshen
 */
public class OpenAiClientCreator implements ClientCreator<OpenAiConfig> {

    private static final Logger log = LoggerFactory.getLogger(OpenAiClientCreator.class);

    @Override
    public Client<OpenAiConfig> create(OpenAiConfig config) throws ClientCreationException {
        log.info("creating openai client: {}", config);
        DefaultOpenAiClient openAiClient = new DefaultOpenAiClient(config);
        try {
            openAiClient.init();
        } catch (Exception e) {
            throw new ClientCreationException(e.getMessage(), e);
        }
        return openAiClient;
    }
}
