package work.gaigeshen.triparttite.pay.alipay.config;

import work.gaigeshen.triparttite.pay.alipay.AlipayClientException;

/**
 * 支付宝客户端配置异常
 *
 * @author gaigeshen
 */
public class AlipayConfigException extends AlipayClientException {
  public AlipayConfigException(String message) {
    super(message);
  }
  public AlipayConfigException(String message, Throwable cause) {
    super(message, cause);
  }
}
