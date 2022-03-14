package work.gaigeshen.triparttite.pay.alipay.parameters;

import java.math.BigDecimal;

/**
 *
 * @author gaigeshen
 */
public class AlipayTradePagePayParameters implements AlipayParameters {

  public String out_trade_no;

  public BigDecimal total_amount;

  public String subject;

  public String body;

  public String product_code;

  public String qr_pay_mode;

  public Integer qrcode_width;

  public String integration_type;

  public String request_from_url;

  public String timeout_express;

  public String time_expire;

  public String enable_pay_channels;

  public String disable_pay_channels;
}
