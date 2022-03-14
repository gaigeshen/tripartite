package work.gaigeshen.triparttite.pay.alipay.response;

import java.math.BigDecimal;

/**
 *
 * @author gaigeshen
 */
public class AlipayTradeQueryResponse implements AlipayResponse {

  public String out_trade_no;

  public String trade_no;

  public String buyer_logon_id;

  public String trade_status;

  public BigDecimal total_amount;

  public BigDecimal buyer_pay_amount;

  public BigDecimal point_amount;

  public BigDecimal invoice_amount;

  public String send_pay_date;

  public String buyer_user_id;

  public String buyer_user_type;

  public String credit_pay_mode;

  public String credit_biz_order_id;
}
