package work.gaigeshen.tripartite.pay.alipay;

import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebException;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.parameter.Parameters;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;
import work.gaigeshen.tripartite.core.response.consumer.ResponseConsumer;
import work.gaigeshen.tripartite.core.response.converter.ResponseConverter;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.pay.alipay.response.AbstractAlipayResponse;
import work.gaigeshen.tripartite.pay.alipay.config.AlipayConfig;
import work.gaigeshen.tripartite.pay.alipay.parameters.AlipayParameters;
import work.gaigeshen.tripartite.pay.alipay.response.AlipayResponse;

import java.util.Objects;

/**
 * @author gaigeshen
 */
public class DefaultAlipayClient implements AlipayClient {

    private final AlipayConfig config;

    private final WebExecutor executor;

    protected DefaultAlipayClient(AlipayConfig config, WebExecutor executor) {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(executor, "executor cannot be null");
        this.config = config;
        this.executor = executor;
    }

    public static DefaultAlipayClient create(AlipayConfig config, AbstractInterceptor... interceptors) {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(interceptors, "interceptors cannot be null");
        RestTemplateWebExecutor executor = RestTemplateWebExecutor.create();
        executor.setInterceptors(interceptors);
        executor.setParametersConverter(new ParametersMetadataParametersConverter(config));
        return new DefaultAlipayClient(config, executor);
    }

    public static DefaultAlipayClient create(AlipayConfig config, WebExecutor executor) {
        return new DefaultAlipayClient(config, executor);
    }

    @Override
    public AlipayConfig getAlipayConfig() {
        return config;
    }

    @Override
    public Parameters convertParameters(AlipayParameters parameters) throws AlipayClientException {
        ArgumentValidate.notNull(parameters, "parameters cannot be null");
        ParametersConverter parametersConverter = executor.getParametersConverter();
        if (Objects.isNull(parametersConverter)) {
            throw new AlipayClientException("could not convert parameters because missing converter: " + parameters);
        }
        return parametersConverter.convert(parameters);
    }

    @Override
    public <R extends AlipayResponse> R execute(AlipayParameters parameters, Class<R> responseClass) throws AlipayClientException {
        ArgumentValidate.notNull(parameters, "parameters cannot be null");
        ArgumentValidate.notNull(responseClass, "responseClass cannot be null");
        try {
            R response = executor.execute(config.getServerUrl(), parameters, responseClass);
            return validateResponse(response);
        } catch (WebException e) {
            throw new AlipayClientException(e.getMessage(), e);
        }
    }

    @Override
    public <R extends AlipayResponse> R execute(AlipayParameters parameters, ResponseConverter<R> converter) throws AlipayClientException {
        ArgumentValidate.notNull(parameters, "parameters cannot be null");
        ArgumentValidate.notNull(converter, "converter cannot be null");
        try {
            R response = executor.execute(config.getServerUrl(), parameters, converter);
            return validateResponse(response);
        } catch (WebException e) {
            throw new AlipayClientException(e.getMessage(), e);
        }
    }

    @Override
    public void execute(AlipayParameters parameters, ResponseConsumer consumer) throws AlipayClientException {
        ArgumentValidate.notNull(parameters, "parameters cannot be null");
        ArgumentValidate.notNull(consumer, "consumer cannot be null");
        try {
            executor.execute(config.getServerUrl(), parameters, consumer);
        } catch (WebException e) {
            throw new AlipayClientException(e.getMessage(), e);
        }
    }

    protected <R extends AlipayResponse> R validateResponse(R response) throws AlipayClientException {
        if (Objects.isNull(response)) {
            throw new AlipayClientException("could not validate null response");
        }
        if (response instanceof AbstractAlipayResponse) {
            AbstractAlipayResponse abstractResponse = (AbstractAlipayResponse) response;
            if (!Objects.equals(abstractResponse.code, "10000")) {
                if (Objects.nonNull(abstractResponse.sub_code)) {
                    throw new AlipayClientException("[ " + abstractResponse.sub_code + " ] - [ " + abstractResponse.sub_msg + " ]");
                }
            }
        }
        return response;
    }
}
