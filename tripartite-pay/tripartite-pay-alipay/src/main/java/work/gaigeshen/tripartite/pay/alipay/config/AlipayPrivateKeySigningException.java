package work.gaigeshen.tripartite.pay.alipay.config;

/**
 * 支付宝应用私钥在签名的时候发生异常
 *
 * @author gaigeshen
 */
public class AlipayPrivateKeySigningException extends AlipayPrivateKeyException {
  public AlipayPrivateKeySigningException(String message) {
    super(message);
  }
  public AlipayPrivateKeySigningException(String message, Throwable cause) {
    super(message, cause);
  }
}
