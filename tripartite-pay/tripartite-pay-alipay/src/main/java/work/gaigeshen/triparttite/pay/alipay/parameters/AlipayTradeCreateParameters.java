package work.gaigeshen.triparttite.pay.alipay.parameters;

import java.math.BigDecimal;

/**
 *
 * @author gaigeshen
 */
public class AlipayTradeCreateParameters implements AlipayParameters {

  public String out_trade_no;

  public BigDecimal total_amount;

  public String subject;

  public String body;

  public String product_code;

  public String seller_id;

  public String buyer_id;
}
