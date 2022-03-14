package work.gaigeshen.triparttite.pay.alipay.parameters;

import java.math.BigDecimal;

/**
 * 支付宝下单业务参数
 *
 * @author gaigeshen
 */
public class AlipayTradeAppPayParameters implements AlipayParameters {

  public String out_trade_no;

  public BigDecimal total_amount;

  public String subject;

  public String body;

  public String product_code;

  public String timeout_express;

  public String time_expire;

  public String goods_type;

  public String enable_pay_channels;

  public String disable_pay_channels;
}
