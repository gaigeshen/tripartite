package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.WebException;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.client.config.Config;
import work.gaigeshen.tripartite.core.client.config.ConfigException;
import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.client.response.ClientResponse;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public abstract class AbstractClient<C extends Config> implements Client<C> {

    private final C config;

    private final WebExecutor executor;

    protected AbstractClient(C config, WebExecutor executor) {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        if (Objects.isNull(executor)) {
            throw new IllegalArgumentException("executor cannot be null");
        }
        this.config = config;
        this.executor = executor;
    }

    @Override
    public final C getConfig() throws ConfigException {
        return config;
    }

    @Override
    public final <R extends ClientResponse> R execute(ClientParameters parameters, Class<R> responseClass, String uri) throws ClientException {
        if (Objects.isNull(parameters)) {
            throw new IllegalArgumentException("parameters cannot be null");
        }
        if (Objects.isNull(responseClass)) {
            throw new IllegalArgumentException("response class cannot be null");
        }
        try {
            R response = executor.execute(config.getServerHost() + uri, parameters, responseClass);
            return validateResponse(response);
        } catch (WebException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    protected <R extends ClientResponse> R validateResponse(R response) throws ClientException {
        if (Objects.isNull(response)) {
            throw new ClientException("could not validate null response");
        }
        return response;
    }
}
