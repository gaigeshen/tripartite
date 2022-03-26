package work.gaigeshen.tripartite.pay.wechat;

import work.gaigeshen.tripartite.core.response.consumer.ResponseConsumer;
import work.gaigeshen.tripartite.core.response.converter.ResponseConverter;
import work.gaigeshen.tripartite.pay.wechat.config.WechatConfig;
import work.gaigeshen.tripartite.pay.wechat.parameters.WechatParametersBuilder;
import work.gaigeshen.tripartite.pay.wechat.parameters.basic.WechatAppOrderParameters;
import work.gaigeshen.tripartite.pay.wechat.parameters.basic.WechatH5OrderParameters;
import work.gaigeshen.tripartite.pay.wechat.parameters.basic.WechatNativeOrderParameters;
import work.gaigeshen.tripartite.pay.wechat.parameters.basic.WechatOrderRefundParameters;
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
   * @throws WechatClientException 执行请求的时候发生异常
   */
  WechatAppOrderResponse appOrder(WechatAppOrderParameters parameters) throws WechatClientException;

  /**
   * 执行网页端微信支付下单请求
   *
   * @param parameters 微信支付下单业务参数不能为空
   * @return 下单响应结果不为空
   * @throws WechatClientException 执行请求的时候发生异常
   */
  WechatH5OrderResponse h5Order(WechatH5OrderParameters parameters) throws WechatClientException;

  /**
   * 执行二维码微信支付下单请求
   *
   * @param parameters 微信支付下单业务参数不能为空
   * @return 下单响应结果不为空
   * @throws WechatClientException 执行请求的时候发生异常
   */
  WechatNativeOrderResponse nativeOrder(WechatNativeOrderParameters parameters) throws WechatClientException;

  /**
   * 执行微信支付订单查询请求
   *
   * @param outTradeNo 商户订单号不能为空
   * @return 订单查询响应结果不为空
   * @throws WechatClientException 执行请求的时候发生异常
   */
  WechatOrderQueryResponse orderQuery(String outTradeNo) throws WechatClientException;

  /**
   * 执行微信支付订单退款请求
   *
   * @param parameters 微信支付订单退款业务参数不能为空
   * @return 订单退款响应结果不为空
   * @throws WechatClientException 执行请求的时候发生异常
   */
  WechatOrderRefundResponse orderRefund(WechatOrderRefundParameters parameters) throws WechatClientException;

  /**
   * 执行微信支付订单退款查询请求
   *
   * @param outRefundNo 商户退款单号不能为空
   * @return 订单退款查询响应结果不为空
   * @throws WechatClientException 执行请求的时候发生异常
   */
  WechatOrderRefundQueryResponse orderRefundQuery(String outRefundNo) throws WechatClientException;

  /**
   * 执行微信支付不带参数的请求
   *
   * @param responseClass 响应结果类型不能为空
   * @param uri 请求链接地址不能为空，该地址不带主机名和协议
   * @param uriVariables 请求链接地址占位符参数
   * @param <R> 响应结果类型
   * @return 响应结果不能为空
   * @throws WechatClientException 执行请求的时候发生异常
   */
  <R extends WechatResponse> R execute(Class<R> responseClass, String uri, Object... uriVariables) throws WechatClientException;

  /**
   * 执行微信支付带参数的请求
   *
   * @param builder 用于构建请求参数，不能为空
   * @param responseClass 响应结果类型不能为空
   * @param uri 请求链接地址不能为空，该地址不带主机名和协议
   * @param uriVariables 请求链接地址占位符参数
   * @param <R> 响应结果类型
   * @return 响应结果不能为空
   * @throws WechatClientException 执行请求的时候发生异常
   */
  <R extends WechatResponse> R execute(WechatParametersBuilder builder, Class<R> responseClass, String uri, Object... uriVariables) throws WechatClientException;

  /**
   * 执行微信支付不带参数的请求
   *
   * @param converter 响应结果数据转换器不能为空
   * @param uri 请求链接地址不能为空，该地址不带主机名和协议
   * @param uriVariables 请求链接地址占位符参数
   * @param <R> 数据转换目标对象类型
   * @return 响应结果数据转换目标对象不能为空
   * @throws WechatClientException 执行请求的时候发生异常
   */
  <R> R execute(ResponseConverter<R> converter, String uri, Object... uriVariables) throws WechatClientException;

  /**
   * 执行微信支付不带参数的请求
   *
   * @param consumer 响应结果数据消费器不能为空
   * @param uri 请求链接地址不能为空，该地址不带主机名和协议
   * @param uriVariables 请求链接地址占位符参数
   * @throws WechatClientException 执行请求的时候发生异常
   */
  void execute(ResponseConsumer consumer, String uri, Object... uriVariables) throws WechatClientException;

  /**
   * 执行微信支付带参数的请求
   *
   * @param builder 用于构建请求参数，不能为空
   * @param converter 响应结果数据转换器不能为空
   * @param uri 请求链接地址不能为空，该地址不带主机名和协议
   * @param uriVariables 请求链接地址占位符参数
   * @param <R> 数据转换目标对象类型
   * @return 响应结果数据转换目标对象不能为空
   * @throws WechatClientException 执行请求的时候发生异常
   */
  <R> R execute(WechatParametersBuilder builder, ResponseConverter<R> converter, String uri, Object... uriVariables) throws WechatClientException;

  /**
   * 执行微信支付带参数的请求
   *
   * @param builder 用于构建请求参数，不能为空
   * @param consumer 响应结果数据消费器不能为空
   * @param uri 请求链接地址不能为空，该地址不带主机名和协议
   * @param uriVariables 请求链接地址占位符参数
   * @throws WechatClientException 执行请求的时候发生异常
   */
  void execute(WechatParametersBuilder builder, ResponseConsumer consumer, String uri, Object... uriVariables) throws WechatClientException;

}
