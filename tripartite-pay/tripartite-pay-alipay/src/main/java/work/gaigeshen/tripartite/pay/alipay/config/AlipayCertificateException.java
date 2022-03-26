package work.gaigeshen.tripartite.pay.alipay.config;

/**
 * 支付宝客户端证书异常
 *
 * @author gaigeshen
 */
public class AlipayCertificateException extends AlipayConfigException {
  public AlipayCertificateException(String message) {
    super(message);
  }
  public AlipayCertificateException(String message, Throwable cause) {
    super(message, cause);
  }
}
