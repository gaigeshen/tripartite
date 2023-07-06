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
import work.gaigeshen.tripartite.core.ratelimiter.RateLimiterService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 抽象的接口客户端
 *
 * @author gaigeshen
 */
public abstract class AbstractWebExecutorClient<C extends Config> implements Client<C> {

    private WebExecutor webExecutor;

    /**
     * 初始化请求执行器，将会调用创建请求执行器的方法
     *
     * @throws ClientException 如果没有正确创建请求执行器的情况则会抛出异常
     */
    @Override
    public synchronized void init() throws ClientException {
        if (Objects.isNull(webExecutor)) {
            webExecutor = createWebExecutor();
            if (Objects.isNull(webExecutor)) {
                throw new ClientException("web executor created cannot be null");
            }
            initInternal();
        }
    }

    /**
     * 此方法将会被初始化方法调用，子类可以重写此方法用于额外的初始化操作
     *
     * @throws ClientException 如果方法执行发生异常
     */
    protected void initInternal() throws ClientException { }

    @Override
    public <R extends ClientResponse, P extends ClientParameters> R executeDelete(
            P parameters, Class<R> responseClass, String path, Object... uriVariables
    ) throws ClientException {
        String serverUrl = getServerHost(parameters, responseClass).getServerUrl(path);
        checkRateLimit(serverUrl + "_delete");
        try {
            R response = webExecutor.executeDelete(serverUrl, parameters, responseClass, uriVariables);
            return validateResponse(response);
        } catch (WebException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    @Override
    public final <R extends ClientResponse, P extends ClientParameters> R executePut(
            P parameters, Class<R> responseClass, String path, Object... uriVariables
    ) throws ClientException {
        String serverUrl = getServerHost(parameters, responseClass).getServerUrl(path);
        checkRateLimit(serverUrl + "_put");
        try {
            R response = webExecutor.executePut(serverUrl, parameters, responseClass, uriVariables);
            return validateResponse(response);
        } catch (WebException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    @Override
    public final <R extends ClientResponse, P extends ClientParameters> R execute(
            P parameters, Class<R> responseClass, String path, Object... uriVariables
    ) throws ClientException {
        String serverUrl = getServerHost(parameters, responseClass).getServerUrl(path);
        checkRateLimit(serverUrl + "_post");
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
        checkRateLimit(serverUrl + "_get");
        try {
            R response = webExecutor.execute(serverUrl, responseClass, uriVariables);
            return validateResponse(response);
        } catch (WebException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    /**
     * 此方法用于校验响应对象
     *
     * @param response 响应对象
     * @return 返回响应对象
     * @param <R> 表示响应对象的类型
     * @throws ClientException 校验失败的时候可以抛出此异常
     */
    protected <R extends ClientResponse> R validateResponse(R response) throws ClientException {
        if (Objects.isNull(response)) {
            throw new ClientException("could not validate null response");
        }
        return response;
    }

    /**
     * 此方法将会被初始化方法调用，用于创建请求执行器
     *
     * @return 创建的请求执行器
     * @throws ClientException 如果没有正确创建请求执行器的情况则应该抛出异常
     */
    protected WebExecutor createWebExecutor() throws ClientException {
        RestTemplateWebExecutor restTemplateWebExecutor = RestTemplateWebExecutor.create();
        List<AbstractInterceptor> interceptors = createInterceptors();
        if (!interceptors.isEmpty()) {
            restTemplateWebExecutor.setInterceptors(interceptors.toArray(new AbstractInterceptor[0]));
        }
        restTemplateWebExecutor.setParametersConverter(createParametersConverter());
        return restTemplateWebExecutor;
    }

    /**
     * 此方法将会被创建请求执行器的方法调用，用于创建拦截器集合
     *
     * @return 创建的拦截器集合
     */
    protected List<AbstractInterceptor> createInterceptors() {
        return Collections.emptyList();
    }

    /**
     * 此方法将会被创建请求执行器的方法调用，用于创建请求参数转换器
     *
     * @return 创建的请求参数转换器
     */
    protected ParametersConverter createParametersConverter() {
        return new ParametersMetadataParametersConverter(getConfig());
    }

    /**
     * 检查限流依赖此接口客户端的限流服务对象，如果没有设置则不会有限流，如果当前被限流则调用此方法会抛出异常
     *
     * @param serverUrl 限流针对不同的服务器访问地址
     * @throws ClientException 被限流的情况会抛出此异常
     */
    protected void checkRateLimit(String serverUrl) throws ClientException {
        RateLimiterService rateLimiterService = getRateLimiterService();
        if (Objects.isNull(rateLimiterService)) {
            return;
        }
        if (!rateLimiterService.acquire(serverUrl, (long) 2)) {
            throw new ClientException("could not acquire permits: " + serverUrl);
        }
    }
}
