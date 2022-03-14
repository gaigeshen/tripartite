package work.gaigeshen.triparttite.pay.alipay;

/**
 * 没有找到指定的支付宝客户端异常
 *
 * @author gaigeshen
 */
public class AlipayClientNotFoundException extends AlipayClientException {
  public AlipayClientNotFoundException(String message) {
    super(message);
  }
  public AlipayClientNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
