package work.gaigeshen.tripartite.pay.wechat;

import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebException;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;
import work.gaigeshen.tripartite.core.response.consumer.ResponseConsumer;
import work.gaigeshen.tripartite.core.response.converter.ResponseConverter;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.pay.wechat.config.WechatConfig;
import work.gaigeshen.tripartite.pay.wechat.parameters.WechatParameters;
import work.gaigeshen.tripartite.pay.wechat.parameters.basic.*;
import work.gaigeshen.tripartite.pay.wechat.response.WechatResponse;
import work.gaigeshen.tripartite.pay.wechat.response.basic.*;

import java.util.Objects;

/**
 * 默认的微信支付客户端
 *
 * @author gaigeshen
 */
public class DefaultWechatClient implements WechatClient {

    private static final String APP_ORDER_URI = "/v3/pay/transactions/app";

    private static final String H5_ORDER_URI = "/v3/pay/transactions/h5";

    private static final String JSAPI_ORDER_URI = "/v3/pay/transactions/jsapi";

    private static final String NATIVE_ORDER_URI = "/v3/pay/transactions/native";

    private static final String REFUND_ORDER_URI = "/v3/refund/domestic/refunds";

    private static final String QUERY_ORDER_URI = "/v3/pay/transactions/out-trade-no/{out_trade_no}?mchid={merchant_id}";

    private static final String QUERY_ORDER_REFUND_URI = "/v3/refund/domestic/refunds/{out_refund_no}";

    private final WechatConfig config;

    private final WebExecutor executor;

    protected DefaultWechatClient(WechatConfig config, WebExecutor executor) {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(executor, "executor cannot be null");
        this.config = config;
        this.executor = executor;
    }

    public static DefaultWechatClient create(WechatConfig config, AbstractInterceptor... interceptors) {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(interceptors, "interceptors cannot be null");
        RestTemplateWebExecutor executor = RestTemplateWebExecutor.create();
        executor.setInterceptors(interceptors);
        executor.setParametersConverter(new ParametersMetadataParametersConverter(config));
        return new DefaultWechatClient(config, executor);
    }

    public static DefaultWechatClient create(WechatConfig config, WebExecutor executor) {
        return new DefaultWechatClient(config, executor);
    }

    @Override
    public WechatConfig getWechatConfig() {
        return config;
    }

    @Override
    public WechatAppOrderResponse appOrder(WechatAppOrderParameters parameters) throws WechatClientException {
        return execute(parameters, WechatAppOrderResponse.class, APP_ORDER_URI);
    }

    @Override
    public WechatH5OrderResponse h5Order(WechatH5OrderParameters parameters) throws WechatClientException {
        return execute(parameters, WechatH5OrderResponse.class, H5_ORDER_URI);
    }

    @Override
    public WechatJsapiOrderResponse jsapiOrder(WechatJsapiOrderParameters parameters) throws WechatClientException {
        return execute(parameters, WechatJsapiOrderResponse.class, JSAPI_ORDER_URI);
    }

    @Override
    public WechatNativeOrderResponse nativeOrder(WechatNativeOrderParameters parameters) throws WechatClientException {
        return execute(parameters, WechatNativeOrderResponse.class, NATIVE_ORDER_URI);
    }

    @Override
    public WechatRefundOrderResponse refundOrder(WechatRefundOrderParameters parameters) throws WechatClientException {
        return execute(parameters, WechatRefundOrderResponse.class, REFUND_ORDER_URI);
    }

    @Override
    public WechatQueryOrderResponse queryOrder(String outTradeNo) throws WechatClientException {
        return execute(WechatQueryOrderResponse.class, QUERY_ORDER_URI, outTradeNo, config.getMerchantId());
    }

    @Override
    public WechatQueryOrderRefundResponse queryOrderRefund(String outRefundNo) throws WechatClientException {
        return execute(WechatQueryOrderRefundResponse.class, QUERY_ORDER_REFUND_URI, outRefundNo);
    }

    @Override
    public <R extends WechatResponse> R execute(Class<R> responseClass, String uri, Object... uriVariables) throws WechatClientException {
        ArgumentValidate.notNull(responseClass, "responseClass cannot be null");
        ArgumentValidate.notNull(uri, "uri cannot be null");
        try {
            R response = executor.execute(config.getServerHost() + uri, responseClass, uriVariables);
            return validateResponse(response);
        } catch (WebException e) {
            throw new WechatClientException(e.getMessage(), e);
        }
    }

    @Override
    public <R extends WechatResponse> R execute(WechatParameters parameters, Class<R> responseClass, String uri, Object... uriVariables) throws WechatClientException {
        ArgumentValidate.notNull(parameters, "parameters cannot be null");
        ArgumentValidate.notNull(responseClass, "responseClass cannot be null");
        ArgumentValidate.notNull(uri, "uri cannot be null");
        try {
            R response = executor.execute(config.getServerHost() + uri, parameters, responseClass, uriVariables);
            return validateResponse(response);
        } catch (WebException e) {
            throw new WechatClientException(e.getMessage(), e);
        }
    }

    @Override
    public <R extends WechatResponse> R execute(WechatParameters parameters, ResponseConverter<R> converter, String uri, Object... uriVariables) throws WechatClientException {
        ArgumentValidate.notNull(parameters, "parameters cannot be null");
        ArgumentValidate.notNull(converter, "converter cannot be null");
        ArgumentValidate.notNull(uri, "uri cannot be null");
        try {
            R response = executor.execute(config.getServerHost() + uri, parameters, converter, uriVariables);
            return validateResponse(response);
        } catch (WebException e) {
            throw new WechatClientException(e.getMessage(), e);
        }
    }

    @Override
    public void execute(WechatParameters parameters, ResponseConsumer consumer, String uri, Object... uriVariables) throws WechatClientException {
        ArgumentValidate.notNull(parameters, "parameters cannot be null");
        ArgumentValidate.notNull(consumer, "consumer cannot be null");
        ArgumentValidate.notNull(uri, "uri cannot be null");
        try {
            executor.execute(config.getServerHost() + uri, parameters, consumer, uriVariables);
        } catch (WebException e) {
            throw new WechatClientException(e.getMessage(), e);
        }
    }

    protected <R extends WechatResponse> R validateResponse(R response) throws WechatClientException {
        if (Objects.isNull(response)) {
            throw new WechatClientException("could not validate null response");
        }
        return response;
    }
}
