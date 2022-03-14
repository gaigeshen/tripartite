package work.gaigeshen.triparttite.pay.wechat.config;

/**
 *
 * @author gaigeshen
 */
public class WechatPrivateKeySigningException extends WechatPrivateKeyException {
  public WechatPrivateKeySigningException(String message) {
    super(message);
  }
  public WechatPrivateKeySigningException(String message, Throwable cause) {
    super(message, cause);
  }
}
