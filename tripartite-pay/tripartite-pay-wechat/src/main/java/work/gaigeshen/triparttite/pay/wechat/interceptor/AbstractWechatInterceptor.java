package work.gaigeshen.triparttite.pay.wechat.interceptor;

import work.gaigeshen.triparttite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.triparttite.core.interceptor.InterceptingException;

/**
 * 微信拦截器添加了对请求内容进行签名的方法
 *
 * @author gaigeshen
 */
public abstract class AbstractWechatInterceptor extends AbstractInterceptor {

  // 此方法包含对请求内容进行签名操作
  // 如需变更可以重写此方法
  @Override
  protected void updateRequest(Request request) throws InterceptingException {
    // 不添加此请求头有可能导致微信拒绝处理
    request.headers().putValue("User-Agent", "PayFramework/all");

    signRequest(request);
  }

  /**
   * 对请求内容进行签名操作
   *
   * @param request 请求内容不为空
   * @throws InterceptingException 在签名的时候发生异常可以抛出此异常或者其他运行时异常
   */
  protected abstract void signRequest(Request request) throws InterceptingException;

}
