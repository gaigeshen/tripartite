package work.gaigeshen.tripartite.openai.openapi.client;

import work.gaigeshen.tripartite.core.client.AbstractWebExecutorClient;
import work.gaigeshen.tripartite.core.client.ServerHost;
import work.gaigeshen.tripartite.core.client.ServerHostException;
import work.gaigeshen.tripartite.core.client.ServerHosts;
import work.gaigeshen.tripartite.core.client.config.ConfigException;
import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.client.response.ClientResponse;
import work.gaigeshen.tripartite.core.ratelimiter.RateLimiterService;
import work.gaigeshen.tripartite.openai.openapi.config.OpenAiConfig;

import java.util.Collection;
import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class DefaultOpenAiClient extends AbstractWebExecutorClient<OpenAiConfig> implements OpenAiClient {

    private final OpenAiConfig openAiConfig;

    public DefaultOpenAiClient(OpenAiConfig openAiConfig) {
        if (Objects.isNull(openAiConfig)) {
            throw new IllegalArgumentException("openAiConfig cannot be null");
        }
        this.openAiConfig = openAiConfig;
    }

    @Override
    public OpenAiConfig getConfig() throws ConfigException {
        return openAiConfig;
    }

    @Override
    public RateLimiterService getRateLimiterService() {
        return RateLimiterService.create(1);
    }

    @Override
    public ServerHost getServerHost(ClientParameters parameters, Class<? extends ClientResponse> responseClass) throws ServerHostException {
        return ServerHost.create(openAiConfig.getServerHost());
    }

    @Override
    public Collection<ServerHost> getServerHosts() throws ServerHostException {
        return ServerHosts.create(openAiConfig.getServerHost());
    }
}
