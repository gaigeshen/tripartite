package work.gaigeshen.tripartite.pay.alipay;

import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebException;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;
import work.gaigeshen.tripartite.core.response.consumer.ResponseConsumer;
import work.gaigeshen.tripartite.core.response.converter.ResponseConverter;
import work.gaigeshen.tripartite.pay.alipay.response.AbstractAlipayResponse;
import work.gaigeshen.tripartite.pay.alipay.config.AlipayConfig;
import work.gaigeshen.tripartite.pay.alipay.parameters.AlipayParameters;
import work.gaigeshen.tripartite.pay.alipay.response.AlipayResponse;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class DefaultAlipayClient implements AlipayClient {

  private final AlipayConfig config;

  private final WebExecutor executor;

  protected DefaultAlipayClient(AlipayConfig config, WebExecutor executor) {
    if (Objects.isNull(config)) {
      throw new IllegalArgumentException("config cannot be null");
    }
    if (Objects.isNull(executor)) {
      throw new IllegalArgumentException("web executor cannot be null");
    }
    this.config = config;
    this.executor = executor;
  }

  public static DefaultAlipayClient create(AlipayConfig config, AbstractInterceptor... interceptors) {
    if (Objects.isNull(interceptors)) {
      throw new IllegalArgumentException("interceptors cannot be null");
    }
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
  public <R extends AlipayResponse> R execute(AlipayParameters parameters, Class<R> responseClass) throws AlipayClientException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("parameters cannot be null");
    }
    if (Objects.isNull(responseClass)) {
      throw new IllegalArgumentException("response class cannot be null");
    }
    try {
      R response = executor.execute(config.getServerUrl(), parameters, responseClass);
      return validateResponse(response);
    } catch (WebException e) {
      throw new AlipayClientException(e.getMessage(), e);
    }
  }

  @Override
  public <R extends AlipayResponse> R execute(AlipayParameters parameters, ResponseConverter<R> converter) throws AlipayClientException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("parameters cannot be null");
    }
    if (Objects.isNull(converter)) {
      throw new IllegalArgumentException("response converter cannot be null");
    }
    try {
      R response = executor.execute(config.getServerUrl(), parameters, converter);
      return validateResponse(response);
    } catch (WebException e) {
      throw new AlipayClientException(e.getMessage(), e);
    }
  }

  @Override
  public void execute(AlipayParameters parameters, ResponseConsumer consumer) throws AlipayClientException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("parameters cannot be null");
    }
    if (Objects.isNull(consumer)) {
      throw new IllegalArgumentException("response consumer cannot be null");
    }
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
