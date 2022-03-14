package work.gaigeshen.triparttite.pay.alipay.parameters;

import java.math.BigDecimal;

/**
 *
 * @author gaigeshen
 */
public class AlipayTradePayParameters implements AlipayParameters {

  public String out_trade_no;

  public BigDecimal total_amount;

  public String subject;

  public String body;

  public String product_code;

  public String scene;

  public String auth_code;
}
