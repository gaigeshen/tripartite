package work.gaigeshen.tripartite.pay.alipay;

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
   * 交易查询
   *
   * @param parameter 交易查询请求参数不能为空
   * @return 交易查询结果不会为空
   * @throws AlipayClientException 执行请求或者执行业务发生异常
   */
  default AlipayTradeQueryResponse queryTrade(AlipayTradeQueryContentParameter parameter) throws AlipayClientException{
    return execute(new DefaultAlipayParameters("alipay.trade.query", parameter), AlipayTradeQueryResponse.class);
  }

  /**
   * 交易退款
   *
   * @param parameter 交易退款请求参数不能为空
   * @return 交易退款结果不会为空
   * @throws AlipayClientException 执行请求或者执行业务发生异常
   */
  default AlipayTradeRefundResponse refundTrade(AlipayTradeRefundContentParameter parameter) throws AlipayClientException {
    return execute(new DefaultAlipayParameters("alipay.trade.refund", parameter), AlipayTradeRefundResponse.class);
  }

  /**
   * 交易关闭
   *
   * @param parameter 交易关闭请求参数不能为空
   * @return 交易关闭结果不会为空
   * @throws AlipayClientException 执行请求或者执行业务发生异常
   */
  default AlipayTradeCloseResponse closeTrade(AlipayTradeCloseContentParameter parameter) throws AlipayClientException {
    return execute(new DefaultAlipayParameters("alipay.trade.close", parameter), AlipayTradeCloseResponse.class);
  }

  /**
   * 交易撤销
   *
   * @param parameter 交易撤销请求参数不能为空
   * @return 交易撤销结果不会为空
   * @throws AlipayClientException 执行请求或者执行业务发生异常
   */
  default AlipayTradeCancelResponse cancelTrade(AlipayTradeCancelContentParameter parameter) throws AlipayClientException {
    return execute(new DefaultAlipayParameters("alipay.trade.cancel", parameter), AlipayTradeCancelResponse.class);
  }

  /**
   * 交易退款查询
   *
   * @param parameter 交易退款查询请求参数不能为空
   * @return 交易退款查询结果不会为空
   * @throws AlipayClientException 执行请求或者执行业务发生异常
   */
  default AlipayTradeRefundQueryResponse queryTradeRefund(AlipayTradeRefundQueryContentParameter parameter) throws AlipayClientException {
    return execute(new DefaultAlipayParameters("alipay.trade.fastpay.refund.query", parameter), AlipayTradeRefundQueryResponse.class);
  }

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
