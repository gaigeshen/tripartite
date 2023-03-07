package work.gaigeshen.tripartite.pay.alipay;

/**
 * 支付宝客户端异常
 *
 * @author gaigeshen
 */
public class AlipayClientException extends RuntimeException {
    public AlipayClientException(String message) {
        super(message);
    }

    public AlipayClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
