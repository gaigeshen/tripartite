package work.gaigeshen.triparttite.pay.alipay.parameters;

import java.math.BigDecimal;

/**
 *
 * @author gaigeshen
 */
public class AlipayTradeRefundParameters implements AlipayParameters {

  public String out_trade_no;

  public String trade_no;

  public BigDecimal refund_amount;

  public String refund_reason;

  public String out_request_no;
}
