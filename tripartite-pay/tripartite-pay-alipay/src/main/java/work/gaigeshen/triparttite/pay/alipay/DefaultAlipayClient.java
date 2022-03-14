package work.gaigeshen.triparttite.pay.alipay;

import work.gaigeshen.triparttite.core.RestTemplateWebExecutor;
import work.gaigeshen.triparttite.core.WebException;
import work.gaigeshen.triparttite.core.WebExecutor;
import work.gaigeshen.triparttite.core.interceptor.Interceptor;
import work.gaigeshen.triparttite.core.parameter.Parameters;
import work.gaigeshen.triparttite.pay.alipay.config.AlipayConfig;
import work.gaigeshen.triparttite.pay.alipay.parameters.AlipayParameters;
import work.gaigeshen.triparttite.pay.alipay.parameters.AlipayParametersBuilder;
import work.gaigeshen.triparttite.pay.alipay.response.AlipayResponse;

import java.util.Objects;

/**
 * 默认的支付宝客户端
 *
 * @author gaigeshen
 */
public class DefaultAlipayClient implements AlipayClient {

  private final AlipayConfig config;

  private final WebExecutor executor;

  private DefaultAlipayClient(AlipayConfig config, WebExecutor executor) {
    if (Objects.isNull(config)) {
      throw new IllegalArgumentException("alipay config cannot be null");
    }
    if (Objects.isNull(executor)) {
      throw new IllegalArgumentException("alipay web executor cannot be null");
    }
    this.config = config;
    this.executor = executor;
  }

  public static DefaultAlipayClient create(AlipayConfig config, WebExecutor executor) {
    return new DefaultAlipayClient(config, executor);
  }

  public static DefaultAlipayClient create(AlipayConfig config, Interceptor... interceptors) {
    if (Objects.isNull(interceptors)) {
      throw new IllegalArgumentException("alipay interceptors cannot be null");
    }
    RestTemplateWebExecutor executor = RestTemplateWebExecutor.create();
    executor.setInterceptors(interceptors);
    return new DefaultAlipayClient(config, executor);
  }

  @Override
  public AlipayConfig getAlipayConfig() {
    return config;
  }

  @Override
  public Parameters convertParameters(AlipayParametersBuilder builder) {
    if (Objects.isNull(builder)) {
      throw new IllegalArgumentException("alipay parameters builder cannot be null");
    }
    return builder.build(config);
  }

  @Override
  public <R extends AlipayResponse> R execute(String apiMethod, AlipayParameters parameters, Class<R> responseClass)
          throws AlipayClientException {
    if (Objects.isNull(apiMethod)) {
      throw new IllegalArgumentException("api method cannot be null");
    }
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("alipay parameters cannot be null");
    }
    if (Objects.isNull(responseClass)) {
      throw new IllegalArgumentException("alipay response class cannot be null");
    }
    Parameters convertedParameters = convertParameters(apiMethod, parameters);
    try {
      return executor.execute(config.getServerUrl(), convertedParameters, responseClass);
    } catch (WebException e) {
      throw new AlipayClientException(e.getMessage(), e);
    }
  }
}
