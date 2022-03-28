package work.gaigeshen.tripartite.pay.alipay;

import work.gaigeshen.tripartite.core.parameter.Parameters;
import work.gaigeshen.tripartite.core.response.consumer.ResponseConsumer;
import work.gaigeshen.tripartite.core.response.converter.ResponseConverter;
import work.gaigeshen.tripartite.pay.alipay.config.AlipayConfig;
import work.gaigeshen.tripartite.pay.alipay.parameters.AlipayParameters;
import work.gaigeshen.tripartite.pay.alipay.parameters.DefaultAlipayParameters;
import work.gaigeshen.tripartite.pay.alipay.parameters.content.*;
import work.gaigeshen.tripartite.pay.alipay.response.*;

/**
 * 支付宝客户端
 *
 * @author gaigeshen
 */
public interface AlipayClient {

  /**
   * 返回此支付宝客户端的配置
   *
   * @return 此支付宝客户端的配置
   */
  AlipayConfig getAlipayConfig();

  /**
   * 交易预创建
   *
   * @param parameter 交易预创建请求参数不能为空
   * @return 交易预创建结果不会为空
   * @throws AlipayClientException 执行请求或者执行业务发生异常
   */
  default AlipayTradePreCreateResponse preCreateTrade(AlipayTradePreCreateContentParameter parameter)
          throws AlipayClientException {
    DefaultAlipayParameters parameters = new DefaultAlipayParameters("alipay.trade.precreate", parameter);
    return execute(parameters, AlipayTradePreCreateResponse.class);
  }

  /**
   * 交易支付
   *
   * @param parameter 交易支付请求参数不能为空
   * @return 交易支付结果不会为空
   * @throws AlipayClientException 执行请求或者执行业务发生异常
   */
  default AlipayTradePayResponse payTrade(AlipayTradePayContentParameter parameter)
          throws AlipayClientException {
    DefaultAlipayParameters parameters = new DefaultAlipayParameters("alipay.trade.pay", parameter);
    return execute(parameters, AlipayTradePayResponse.class);
  }

  /**
   * 交易查询
   *
   * @param parameter 交易查询请求参数不能为空
   * @return 交易查询结果不会为空
   * @throws AlipayClientException 执行请求或者执行业务发生异常
   */
  default AlipayTradeQueryResponse queryTrade(AlipayTradeQueryContentParameter parameter)
          throws AlipayClientException {
    DefaultAlipayParameters parameters = new DefaultAlipayParameters("alipay.trade.query", parameter);
    return execute(parameters, AlipayTradeQueryResponse.class);
  }

  /**
   * 交易退款
   *
   * @param parameter 交易退款请求参数不能为空
   * @return 交易退款结果不会为空
   * @throws AlipayClientException 执行请求或者执行业务发生异常
   */
  default AlipayTradeRefundResponse refundTrade(AlipayTradeRefundContentParameter parameter)
          throws AlipayClientException {
    DefaultAlipayParameters parameters = new DefaultAlipayParameters("alipay.trade.refund", parameter);
    return execute(parameters, AlipayTradeRefundResponse.class);
  }

  /**
   * 交易关闭
   *
   * @param parameter 交易关闭请求参数不能为空
   * @return 交易关闭结果不会为空
   * @throws AlipayClientException 执行请求或者执行业务发生异常
   */
  default AlipayTradeCloseResponse closeTrade(AlipayTradeCloseContentParameter parameter)
          throws AlipayClientException {
    DefaultAlipayParameters parameters = new DefaultAlipayParameters("alipay.trade.close", parameter);
    return execute(parameters, AlipayTradeCloseResponse.class);
  }

  /**
   * 交易撤销
   *
   * @param parameter 交易撤销请求参数不能为空
   * @return 交易撤销结果不会为空
   * @throws AlipayClientException 执行请求或者执行业务发生异常
   */
  default AlipayTradeCancelResponse cancelTrade(AlipayTradeCancelContentParameter parameter)
          throws AlipayClientException {
    DefaultAlipayParameters parameters = new DefaultAlipayParameters("alipay.trade.cancel", parameter);
    return execute(parameters, AlipayTradeCancelResponse.class);
  }

  /**
   * 交易退款查询
   *
   * @param parameter 交易退款查询请求参数不能为空
   * @return 交易退款查询结果不会为空
   * @throws AlipayClientException 执行请求或者执行业务发生异常
   */
  default AlipayTradeRefundQueryResponse queryTradeRefund(AlipayTradeRefundQueryContentParameter parameter)
          throws AlipayClientException {
    DefaultAlipayParameters parameters = new DefaultAlipayParameters("alipay.trade.fastpay.refund.query", parameter);
    return execute(parameters, AlipayTradeRefundQueryResponse.class);
  }

  /**
   * 将原始请求参数对象转换为请求参数
   *
   * @param parameters 原始请求参数不能为空
   * @return 请求参数不为空
   * @throws AlipayClientException 无法执行转换操作
   */
  Parameters convertParameters(AlipayParameters parameters) throws AlipayClientException;

  /**
   * 此方法接受任何请求参数，然后执行请求并返回对应的响应结果
   *
   * @param parameters 请求参数不能为空
   * @param responseClass 响应结果类型不能为空
   * @param <R> 表示响应结果类型
   * @return 响应结果不会为空
   * @throws AlipayClientException 执行请求或者执行业务发生异常
   */
  <R extends AlipayResponse> R execute(AlipayParameters parameters, Class<R> responseClass) throws AlipayClientException;

  /**
   * 此方法接受任何请求参数，然后执行请求并返回对应的响应结果，可以指定响应结果数据转换器
   *
   * @param parameters 请求参数不能为空
   * @param converter 响应结果数据转换器不能为空
   * @param <R> 表示响应结果类型
   * @return 响应结果不会为空
   * @throws AlipayClientException 执行请求或者执行业务发生异常
   */
  <R extends AlipayResponse> R execute(AlipayParameters parameters, ResponseConverter<R> converter) throws AlipayClientException;

  /**
   * 此方法接受任何请求参数，然后执行请求并回调响应结果数据消费器
   *
   * @param parameters 请求参数不能为空
   * @param consumer 响应结果数据消费器不能为空
   * @throws AlipayClientException 执行请求或者执行业务发生异常
   */
  void execute(AlipayParameters parameters, ResponseConsumer consumer) throws AlipayClientException;
}
