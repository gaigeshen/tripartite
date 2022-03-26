package work.gaigeshen.tripartite.pay.alipay.config;

/**
 * 支付宝应用私钥异常
 *
 * @author gaigeshen
 */
public class AlipayPrivateKeyException extends AlipayConfigException {
  public AlipayPrivateKeyException(String message) {
    super(message);
  }
  public AlipayPrivateKeyException(String message, Throwable cause) {
    super(message, cause);
  }
}
