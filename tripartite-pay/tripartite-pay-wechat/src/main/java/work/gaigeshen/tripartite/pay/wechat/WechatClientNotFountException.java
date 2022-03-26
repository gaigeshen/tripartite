package work.gaigeshen.tripartite.pay.wechat;

/**
 * 没有找到指定的微信支付客户端的异常
 *
 * @author gaigeshen
 */
public class WechatClientNotFountException extends WechatClientException {
  public WechatClientNotFountException(String message) {
    super(message);
  }
  public WechatClientNotFountException(String message, Throwable cause) {
    super(message, cause);
  }
}
