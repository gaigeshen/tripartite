package work.gaigeshen.triparttite.pay.alipay;

import work.gaigeshen.triparttite.core.parameter.Parameters;
import work.gaigeshen.triparttite.pay.alipay.config.AlipayConfig;
import work.gaigeshen.triparttite.pay.alipay.parameters.*;
import work.gaigeshen.triparttite.pay.alipay.response.*;

import java.util.Objects;

/**
 * 支付宝客户端
 *
 * @author gaigeshen
 */
public interface AlipayClient {
  /**
   * 获取支付宝客户端的配置
   *
   * @return 支付宝客户端的配置不为空
   */
  AlipayConfig getAlipayConfig();

  /**
   * 转换支付宝业务参数为请求参数
   *
   * @param builder 支付宝请求参数构建器不能为空
   * @return 请求参数不为空
   */
  Parameters convertParameters(AlipayParametersBuilder builder);

  /**
   * 转换支付宝业务参数为请求参数
   *
   * @param apiMethod 支付宝业务参数所对应的接口名称不能为空
   * @param parameters 支付宝业务参数不能为空
   * @return 请求参数不为空
   */
  default Parameters convertParameters(String apiMethod, AlipayParameters parameters) {
    if (Objects.isNull(apiMethod)) {
      throw new IllegalArgumentException("api method cannot be null");
    }
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("alipay parameters cannot be null");
    }
    return convertParameters(new DefaultAlipayParametersBuilder(parameters, apiMethod));
  }

  /**
   * 执行请求然后返回支付宝业务响应结果
   *
   * @param apiMethod 支付宝业务参数所对应的接口名称不能为空
   * @param parameters 支付宝业务参数不能为空
   * @param responseClass 支付宝业务响应结果类型不能为空
   * @param <R> 支付宝业务响应结果类型
   * @return 支付宝业务响应结果不为空
   * @throws AlipayClientException 执行请求的时候发生异常
   */
  <R extends AlipayResponse> R execute(String apiMethod, AlipayParameters parameters, Class<R> responseClass) throws AlipayClientException;

  /**
   * 执行支付宝订单撤销请求
   *
   * @param parameters 支付宝订单撤销业务参数不能为空
   * @return 支付宝订单撤销业务响应结果不为空
   * @throws AlipayClientException 执行请求的时候发生异常
   */
  default AlipayTradeCancelResponse execute(AlipayTradeCancelParameters parameters) throws AlipayClientException {
    return execute(AlipayParameters.API_METHOD_TRADE_CANCEL, parameters, AlipayTradeCancelResponse.class);
  }

  /**
   * 执行支付宝订单关闭请求
   *
   * @param parameters 支付宝订单关闭业务参数不能为空
   * @return 支付宝订单关闭业务响应结果不为空
   * @throws AlipayClientException 执行请求的时候发生异常
   */
  default AlipayTradeCloseResponse execute(AlipayTradeCloseParameters parameters) throws AlipayClientException {
    return execute(AlipayParameters.API_METHOD_TRADE_CLOSE, parameters, AlipayTradeCloseResponse.class);
  }
  /**
   * 执行支付宝订单创建请求
   *
   * @param parameters 支付宝订单创建业务参数不能为空
   * @return 支付宝订单创建业务响应结果不为空
   * @throws AlipayClientException 执行请求的时候发生异常
   */
  default AlipayTradeCreateResponse execute(AlipayTradeCreateParameters parameters) throws AlipayClientException {
    return execute(AlipayParameters.API_METHOD_TRADE_CREATE, parameters, AlipayTradeCreateResponse.class);
  }

  /**
   * 执行支付宝订单支付请求
   *
   * @param parameters 支付宝订单支付业务参数不能为空
   * @return 支付宝订单支付业务响应结果不为空
   * @throws AlipayClientException 执行请求的时候发生异常
   */
  default AlipayTradePayResponse execute(AlipayTradePayParameters parameters) throws AlipayClientException {
    return execute(AlipayParameters.API_METHOD_TRADE_PAY, parameters, AlipayTradePayResponse.class);
  }

  /**
   * 执行支付宝订单查询请求
   *
   * @param parameters 支付宝订单查询业务参数不能为空
   * @return 支付宝订单查询业务响应结果不为空
   * @throws AlipayClientException 执行请求的时候发生异常
   */
  default AlipayTradeQueryResponse execute(AlipayTradeQueryParameters parameters) throws AlipayClientException {
    return execute(AlipayParameters.API_METHOD_TRADE_QUERY, parameters, AlipayTradeQueryResponse.class);
  }

  /**
   * 执行支付宝订单退款请求
   *
   * @param parameters 支付宝订单退款业务参数不能为空
   * @return 支付宝订单退款业务响应结果不为空
   * @throws AlipayClientException 执行请求的时候发生异常
   */
  default AlipayTradeRefundResponse execute(AlipayTradeRefundParameters parameters) throws AlipayClientException {
    return execute(AlipayParameters.API_METHOD_TRADE_REFUND, parameters, AlipayTradeRefundResponse.class);
  }

  /**
   * 执行支付宝订单退款查询请求
   *
   * @param parameters 支付宝订单退款查询业务参数不能为空
   * @return 支付宝订单退款查询业务响应结果不为空
   * @throws AlipayClientException 执行请求的时候发生异常
   */
  default AlipayTradeRefundQueryResponse execute(AlipayTradeRefundQueryParameters parameters) throws AlipayClientException {
    return execute(AlipayParameters.API_METHOD_TRADE_REFUND_QUERY, parameters, AlipayTradeRefundQueryResponse.class);
  }
}
