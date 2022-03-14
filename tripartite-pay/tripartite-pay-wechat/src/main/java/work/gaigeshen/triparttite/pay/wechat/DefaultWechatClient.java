package work.gaigeshen.triparttite.pay.wechat;

import work.gaigeshen.triparttite.core.RestTemplateWebExecutor;
import work.gaigeshen.triparttite.core.WebException;
import work.gaigeshen.triparttite.core.WebExecutor;
import work.gaigeshen.triparttite.core.parameter.Parameter;
import work.gaigeshen.triparttite.core.parameter.Parameters;
import work.gaigeshen.triparttite.core.response.consumer.ResponseConsumer;
import work.gaigeshen.triparttite.core.response.converter.ResponseConverter;
import work.gaigeshen.triparttite.pay.wechat.config.WechatConfig;
import work.gaigeshen.triparttite.pay.wechat.interceptor.AbstractWechatInterceptor;
import work.gaigeshen.triparttite.pay.wechat.parameters.WechatParametersBuilder;
import work.gaigeshen.triparttite.pay.wechat.parameters.basic.WechatAppOrderParameters;
import work.gaigeshen.triparttite.pay.wechat.parameters.basic.WechatH5OrderParameters;
import work.gaigeshen.triparttite.pay.wechat.parameters.basic.WechatNativeOrderParameters;
import work.gaigeshen.triparttite.pay.wechat.parameters.basic.WechatOrderRefundParameters;
import work.gaigeshen.triparttite.pay.wechat.response.WechatResponse;
import work.gaigeshen.triparttite.pay.wechat.response.basic.*;

import java.util.Objects;

/**
 * 默认的微信支付客户端
 *
 * @author gaigeshen
 */
public class DefaultWechatClient implements WechatClient {

  private static final String APP_ORDER_URI = "/v3/pay/transactions/app";

  private static final String H5_ORDER_URI = "/v3/pay/transactions/h5";

  private static final String NATIVE_ORDER_URI = "/v3/pay/transactions/native";

  private static final String ORDER_QUERY_URI = "/v3/pay/transactions/out-trade-no/{out_trade_no}?mchid={merchant_id}";

  private static final String ORDER_REFUND_URI = "/v3/refund/domestic/refunds";

  private static final String ORDER_REFUND_QUERY_URI = "/v3/refund/domestic/refunds/{out_refund_no}";

  private final WechatConfig config;

  private final WebExecutor executor;

  public DefaultWechatClient(WechatConfig config, WebExecutor executor) {
    if (Objects.isNull(config)) {
      throw new IllegalArgumentException("wechat config cannot be null");
    }
    if (Objects.isNull(executor)) {
      throw new IllegalArgumentException("wechat web executor cannot be null");
    }
    this.config = config;
    this.executor = executor;
  }

  public static DefaultWechatClient create(WechatConfig config, WebExecutor executor) {
    return new DefaultWechatClient(config, executor);
  }

  public static DefaultWechatClient create(WechatConfig config, AbstractWechatInterceptor... interceptors) {
    if (Objects.isNull(interceptors)) {
      throw new IllegalArgumentException("wechat interceptors cannot be null");
    }
    RestTemplateWebExecutor executor = RestTemplateWebExecutor.create();
    executor.setInterceptors(interceptors);
    return new DefaultWechatClient(config, executor);
  }

  @Override
  public WechatConfig getWechatConfig() {
    return config;
  }

  @Override
  public WechatAppOrderResponse appOrder(WechatAppOrderParameters parameters) throws WechatClientException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("wechat app order parameters cannot be null");
    }
    return execute(new WechatParametersBuilder(parameters) {
      @Override
      protected void overrideParameters(Parameters parameters, WechatConfig config) {
        Parameter<?>[] otherParameters = new Parameter[] {
                Parameter.string("appid", config.getAppId()),
                Parameter.string("mchid", config.getMerchantId()),
                Parameter.string("notify_url", config.getNotifyUrl())
        };
        parameters.put(otherParameters);
      }
    }, WechatAppOrderResponse.class, APP_ORDER_URI);
  }

  @Override
  public WechatH5OrderResponse h5Order(WechatH5OrderParameters parameters) throws WechatClientException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("wechat h5 order parameters cannot be null");
    }
    return execute(new WechatParametersBuilder(parameters) {
      @Override
      protected void overrideParameters(Parameters parameters, WechatConfig config) {
        Parameter<?>[] otherParameters = new Parameter[] {
                Parameter.string("appid", config.getAppId()),
                Parameter.string("mchid", config.getMerchantId()),
                Parameter.string("notify_url", config.getNotifyUrl())
        };
        parameters.put(otherParameters);
      }
    }, WechatH5OrderResponse.class, H5_ORDER_URI);
  }

  @Override
  public WechatNativeOrderResponse nativeOrder(WechatNativeOrderParameters parameters) throws WechatClientException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("wechat native order parameters cannot be null");
    }
    return execute(new WechatParametersBuilder(parameters) {
      @Override
      protected void overrideParameters(Parameters parameters, WechatConfig config) {
        Parameter<?>[] otherParameters = new Parameter[] {
                Parameter.string("appid", config.getAppId()),
                Parameter.string("mchid", config.getMerchantId()),
                Parameter.string("notify_url", config.getNotifyUrl())
        };
        parameters.put(otherParameters);
      }
    }, WechatNativeOrderResponse.class, NATIVE_ORDER_URI);
  }

  @Override
  public WechatOrderQueryResponse orderQuery(String outTradeNo) throws WechatClientException {
    if (Objects.isNull(outTradeNo)) {
      throw new IllegalArgumentException("wechat out trade number cannot be null");
    }
    return execute(WechatOrderQueryResponse.class, ORDER_QUERY_URI, outTradeNo, config.getMerchantId());
  }

  @Override
  public WechatOrderRefundResponse orderRefund(WechatOrderRefundParameters parameters) throws WechatClientException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("wechat order refund parameters cannot be null");
    }
    return execute(new WechatParametersBuilder(parameters) {
      @Override
      protected void overrideParameters(Parameters parameters, WechatConfig config) {
        Parameter<?>[] otherParameters = new Parameter[] {
                Parameter.string("notify_url", config.getNotifyUrl())
        };
        parameters.put(otherParameters);
      }
    }, WechatOrderRefundResponse.class, ORDER_REFUND_URI);
  }

  @Override
  public WechatOrderRefundQueryResponse orderRefundQuery(String outRefundNo) throws WechatClientException {
    if (Objects.isNull(outRefundNo)) {
      throw new IllegalArgumentException("wechat out refund number cannot be null");
    }
    return execute(WechatOrderRefundQueryResponse.class, ORDER_REFUND_QUERY_URI, outRefundNo);
  }

  @Override
  public <R extends WechatResponse> R execute(Class<R> responseClass, String uri, Object... uriVariables) throws WechatClientException {
    if (Objects.isNull(responseClass)) {
      throw new IllegalArgumentException("response class cannot be null");
    }
    if (Objects.isNull(uri)) {
      throw new IllegalArgumentException("uri cannot be null");
    }
    try {
      return executor.execute(config.getServerHost() + "/" + uri, responseClass, uriVariables);
    } catch (WebException e) {
      throw new WechatClientException(e.getMessage(), e);
    }
  }

  @Override
  public <R extends WechatResponse> R execute(WechatParametersBuilder builder, Class<R> responseClass, String uri, Object... uriVariables) throws WechatClientException {
    if (Objects.isNull(builder)) {
      throw new IllegalArgumentException("wechat parameters builder cannot be null");
    }
    if (Objects.isNull(responseClass)) {
      throw new IllegalArgumentException("response class cannot be null");
    }
    if (Objects.isNull(uri)) {
      throw new IllegalArgumentException("uri cannot be null");
    }
    try {
      return executor.execute(config.getServerHost() + "/" + uri, builder.build(config), responseClass, uriVariables);
    } catch (WebException e) {
      throw new WechatClientException(e.getMessage(), e);
    }
  }

  @Override
  public <R> R execute(ResponseConverter<R> converter, String uri, Object... uriVariables) throws WechatClientException {
    if (Objects.isNull(converter)) {
      throw new IllegalArgumentException("response converter cannot be null");
    }
    if (Objects.isNull(uri)) {
      throw new IllegalArgumentException("uri cannot be null");
    }
    try {
      return executor.execute(config.getServerHost() + "/" + uri, converter, uriVariables);
    } catch (WebException e) {
      throw new WechatClientException(e.getMessage(), e);
    }
  }

  @Override
  public void execute(ResponseConsumer consumer, String uri, Object... uriVariables) throws WechatClientException {
    if (Objects.isNull(consumer)) {
      throw new IllegalArgumentException("response consumer cannot be null");
    }
    if (Objects.isNull(uri)) {
      throw new IllegalArgumentException("uri cannot be null");
    }
    try {
      executor.execute(config.getServerHost() + "/" + uri, consumer, uriVariables);
    } catch (WebException e) {
      throw new WechatClientException(e.getMessage(), e);
    }
  }

  @Override
  public <R> R execute(WechatParametersBuilder builder, ResponseConverter<R> converter, String uri, Object... uriVariables) throws WechatClientException {
    if (Objects.isNull(builder)) {
      throw new IllegalArgumentException("wechat parameters builder cannot be null");
    }
    if (Objects.isNull(converter)) {
      throw new IllegalArgumentException("response converter cannot be null");
    }
    if (Objects.isNull(uri)) {
      throw new IllegalArgumentException("uri cannot be null");
    }
    try {
      return executor.execute(config.getServerHost() + "/" + uri, builder.build(config), converter, uriVariables);
    } catch (WebException e) {
      throw new WechatClientException(e.getMessage(), e);
    }
  }

  @Override
  public void execute(WechatParametersBuilder builder, ResponseConsumer consumer, String uri, Object... uriVariables) throws WechatClientException {
    if (Objects.isNull(builder)) {
      throw new IllegalArgumentException("wechat parameters builder cannot be null");
    }
    if (Objects.isNull(consumer)) {
      throw new IllegalArgumentException("response consumer cannot be null");
    }
    if (Objects.isNull(uri)) {
      throw new IllegalArgumentException("uri cannot be null");
    }
    try {
      executor.execute(config.getServerHost() + "/" + uri, builder.build(config), consumer, uriVariables);
    } catch (WebException e) {
      throw new WechatClientException(e.getMessage(), e);
    }
  }
}
