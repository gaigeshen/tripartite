package work.gaigeshen.tripartite.pay.alipay.parameters.content;

import work.gaigeshen.tripartite.pay.alipay.parameters.AlipayContentParameter;

import java.math.BigDecimal;

/**
 *
 * @author gaigeshen
 */
public class AlipayTradeRefundContentParameter implements AlipayContentParameter {

  public String out_trade_no;

  public String trade_no;

  public BigDecimal refund_amount;

  public String refund_reason;

  public String out_request_no;
}
