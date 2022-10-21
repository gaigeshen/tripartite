package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebException;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.client.config.Config;
import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.client.response.ClientResponse;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public abstract class AbstractWebExecutorClient<C extends Config> implements Client<C> {

    private WebExecutor webExecutor;

    @Override
    public synchronized void init() throws ClientException {
        webExecutor = createWebExecutor();
        if (Objects.isNull(webExecutor)) {
            throw new ClientException("Web executor created cannot be null");
        }
    }

    @Override
    public final <R extends ClientResponse, P extends ClientParameters> R execute(
            P parameters, Class<R> responseClass, String path, Object... uriVariables
    ) throws ClientException {
        String serverUrl = getServerHost(parameters, responseClass).getServerUrl(path);
        try {
            R response = webExecutor.execute(serverUrl, parameters, responseClass, uriVariables);
            return validateResponse(response);
        } catch (WebException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    @Override
    public final <R extends ClientResponse> R execute(
            Class<R> responseClass, String path, Object... uriVariables
    ) throws ClientException {
        String serverUrl = getServerHost(null, responseClass).getServerUrl(path);
        try {
            R response = webExecutor.execute(serverUrl, responseClass, uriVariables);
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

    protected WebExecutor createWebExecutor() throws ClientException {
        RestTemplateWebExecutor restTemplateWebExecutor = RestTemplateWebExecutor.create();
        List<AbstractInterceptor> interceptors = createInterceptors();
        if (!interceptors.isEmpty()) {
            restTemplateWebExecutor.setInterceptors(interceptors.toArray(new AbstractInterceptor[0]));
        }
        restTemplateWebExecutor.setParametersConverter(createParametersConverter());
        return restTemplateWebExecutor;
    }

    protected List<AbstractInterceptor> createInterceptors() {
        return Collections.emptyList();
    }

    protected ParametersConverter createParametersConverter() {
        return new ParametersMetadataParametersConverter(getConfig());
    }
}
