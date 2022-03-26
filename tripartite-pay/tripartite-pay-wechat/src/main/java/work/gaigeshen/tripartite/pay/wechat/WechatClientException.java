package work.gaigeshen.tripartite.pay.wechat;

/**
 * 微信支付客户端异常
 *
 * @author gaigeshen
 */
public class WechatClientException extends RuntimeException {
  public WechatClientException(String message) {
    super(message);
  }
  public WechatClientException(String message, Throwable cause) {
    super(message, cause);
  }
}
