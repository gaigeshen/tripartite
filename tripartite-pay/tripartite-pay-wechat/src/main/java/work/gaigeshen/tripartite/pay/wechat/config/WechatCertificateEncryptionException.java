package work.gaigeshen.tripartite.pay.wechat.config;

/**
 * @author gaigeshen
 */
public class WechatCertificateEncryptionException extends WechatCertificateException {
  public WechatCertificateEncryptionException(String message) {
    super(message);
  }
  public WechatCertificateEncryptionException(String message, Throwable cause) {
    super(message, cause);
  }
}
