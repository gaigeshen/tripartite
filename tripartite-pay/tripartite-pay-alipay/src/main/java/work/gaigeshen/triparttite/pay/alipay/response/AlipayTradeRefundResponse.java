package work.gaigeshen.triparttite.pay.alipay.response;

import java.math.BigDecimal;

/**
 *
 * @author gaigeshen
 */
public class AlipayTradeRefundResponse implements AlipayResponse {

  public String out_trade_no;

  public String trade_no;

  public String buyer_logon_id;

  public String buyer_user_id;

  public String fund_change;

  public BigDecimal refund_fee;
}
