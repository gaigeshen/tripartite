package work.gaigeshen.tripartite.pay.wechat.config;

/**
 * @author gaigeshen
 */
public class WechatSecretKeyDecryptionException extends WechatSecretKeyException {
  public WechatSecretKeyDecryptionException(String message) {
    super(message);
  }
  public WechatSecretKeyDecryptionException(String message, Throwable cause) {
    super(message, cause);
  }
}
