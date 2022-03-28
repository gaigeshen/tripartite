package work.gaigeshen.tripartite.pay.alipay.parameters.content;

import work.gaigeshen.tripartite.pay.alipay.parameters.AlipayContentParameter;

import java.math.BigDecimal;

/**
 *
 * @author gaigeshen
 */
public class AlipayTradePayContentParameter implements AlipayContentParameter {

  public String out_trade_no;

  public BigDecimal total_amount;

  public String subject;

  public String body;

  public String product_code;

  public String time_expire;

  public String timeout_express;

  public String scene;

  public String auth_code;
}
