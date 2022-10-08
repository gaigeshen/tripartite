package work.gaigeshen.tripartite.ding.openapi;

import work.gaigeshen.tripartite.core.WebException;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingParameters;
import work.gaigeshen.tripartite.ding.openapi.response.DingResponse;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public abstract class DingAbstractClient implements DingClient {

    private final DingConfig config;

    private final WebExecutor executor;

    protected DingAbstractClient(DingConfig config, WebExecutor executor) {
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
    public DingConfig getDingConfig() {
        return config;
    }

    @Override
    public <R extends DingResponse> R execute(DingParameters parameters, Class<R> responseClass, String uri)
            throws DingClientException {
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
            throw new DingClientException(e.getMessage(), e);
        }
    }

    protected <R extends DingResponse> R validateResponse(R response) throws DingClientException {
        if (Objects.isNull(response)) {
            throw new DingClientException("could not validate null response");
        }
        return response;
    }
}
