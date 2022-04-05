package work.gaigeshen.tripartite.pay.wechat;

import work.gaigeshen.tripartite.core.response.consumer.ResponseConsumer;
import work.gaigeshen.tripartite.core.response.converter.ResponseConverter;
import work.gaigeshen.tripartite.pay.wechat.config.WechatConfig;
import work.gaigeshen.tripartite.pay.wechat.parameters.WechatParameters;
import work.gaigeshen.tripartite.pay.wechat.parameters.basic.*;
import work.gaigeshen.tripartite.pay.wechat.response.WechatResponse;
import work.gaigeshen.tripartite.pay.wechat.response.basic.*;

/**
 * 微信支付客户端
 *
 * @author gaigeshen
 */
public interface WechatClient {

  /**
   * 获取微信支付客户端的配置
   *
   * @return 微信支付客户端的配置不为空
   */
  WechatConfig getWechatConfig();

  /**
   * 执行手机端微信支付下单请求
   *
   * @param parameters 微信支付下单业务参数不能为空
   * @return 下单响应结果不为空
   * @throws WechatClientException 执行请求或者执行业务发生异常
   */
  WechatAppOrderResponse appOrder(WechatAppOrderParameters parameters) throws WechatClientException;

  /**
   * 执行网页端微信支付下单请求
   *
   * @param parameters 微信支付下单业务参数不能为空
   * @return 下单响应结果不为空
   * @throws WechatClientException 执行请求或者执行业务发生异常
   */
  WechatH5OrderResponse h5Order(WechatH5OrderParameters parameters) throws WechatClientException;

  /**
   * 执行小程序端微信支付下单请求
   *
   * @param parameters 微信支付下单业务参数不能为空
   * @return 下单响应结果不为空
   * @throws WechatClientException 执行请求或者执行业务发生异常
   */
  WechatJsapiOrderResponse jsapiOrder(WechatJsapiOrderParameters parameters) throws WechatClientException;

  /**
   * 执行二维码微信支付下单请求
   *
   * @param parameters 微信支付下单业务参数不能为空
   * @return 下单响应结果不为空
   * @throws WechatClientException 执行请求或者执行业务发生异常
   */
  WechatNativeOrderResponse nativeOrder(WechatNativeOrderParameters parameters) throws WechatClientException;

  /**
   * 执行微信支付订单退款请求
   *
   * @param parameters 微信支付订单退款业务参数不能为空
   * @return 订单退款响应结果不为空
   * @throws WechatClientException 执行请求或者执行业务发生异常
   */
  WechatRefundOrderResponse refundOrder(WechatRefundOrderParameters parameters) throws WechatClientException;

  /**
   * 执行微信支付订单查询请求
   *
   * @param outTradeNo 商户订单号不能为空
   * @return 订单查询响应结果不为空
   * @throws WechatClientException 执行请求或者执行业务发生异常
   */
  WechatQueryOrderResponse queryOrder(String outTradeNo) throws WechatClientException;

  /**
   * 执行微信支付订单退款查询请求
   *
   * @param outRefundNo 商户退款单号不能为空
   * @return 订单退款查询响应结果不为空
   * @throws WechatClientException 执行请求或者执行业务发生异常
   */
  WechatQueryOrderRefundResponse queryOrderRefund(String outRefundNo) throws WechatClientException;

  /**
   * 此方法不接受任何请求参数，然后执行请求并返回对应的响应结果
   *
   * @param <R> 表示响应结果类型
   * @param responseClass 响应结果类型不能为空
   * @param uri 该请求对应的服务器地址不能为空
   * @param uriVariables 按顺序指定服务器地址中的可选参数
   * @return 响应结果不会为空
   * @throws WechatClientException 执行请求或者执行业务发生异常
   */
  <R extends WechatResponse> R execute(Class<R> responseClass, String uri, Object... uriVariables) throws WechatClientException;

  /**
   * 此方法接受任何请求参数，然后执行请求并返回对应的响应结果
   *
   * @param parameters 请求参数不能为空
   * @param responseClass 响应结果类型不能为空
   * @param uri 该请求对应的服务器地址不能为空
   * @param uriVariables 按顺序指定服务器地址中的可选参数
   * @param <R> 表示响应结果类型
   * @return 响应结果不会为空
   * @throws WechatClientException 执行请求或者执行业务发生异常
   */
  <R extends WechatResponse> R execute(WechatParameters parameters, Class<R> responseClass, String uri, Object... uriVariables) throws WechatClientException;

  /**
   * 此方法接受任何请求参数，然后执行请求并返回对应的响应结果，可以指定响应结果数据转换器
   *
   * @param parameters 请求参数不能为空
   * @param converter 响应结果数据转换器不能为空
   * @param uri 该请求对应的服务器地址不能为空
   * @param uriVariables 按顺序指定服务器地址中的可选参数
   * @param <R> 表示响应结果类型
   * @return 响应结果不会为空
   * @throws WechatClientException 执行请求或者执行业务发生异常
   */
  <R extends WechatResponse> R execute(WechatParameters parameters, ResponseConverter<R> converter, String uri, Object... uriVariables) throws WechatClientException;

  /**
   * 此方法接受任何请求参数，然后执行请求并回调响应结果数据消费器
   *
   * @param parameters 请求参数不能为空
   * @param consumer 响应结果数据消费器不能为空
   * @param uri 该请求对应的服务器地址不能为空
   * @param uriVariables 按顺序指定服务器地址中的可选参数
   * @throws WechatClientException 执行请求或者执行业务发生异常
   */
  void execute(WechatParameters parameters, ResponseConsumer consumer, String uri, Object... uriVariables) throws WechatClientException;

}
