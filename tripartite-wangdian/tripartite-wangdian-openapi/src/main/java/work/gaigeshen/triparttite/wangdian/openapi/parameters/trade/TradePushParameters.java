package work.gaigeshen.triparttite.wangdian.openapi.parameters.trade;

import work.gaigeshen.triparttite.core.parameter.converter.Parameters;
import work.gaigeshen.triparttite.core.parameter.typed.Parameter;
import work.gaigeshen.triparttite.core.parameter.typed.converter.DefaultJsonParameterConverter;
import work.gaigeshen.triparttite.wangdian.openapi.parameters.WangdianParameters;
import work.gaigeshen.triparttite.wangdian.openapi.parameters.WangdianParametersCustomizer;

import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        customizer = WangdianParametersCustomizer.class
)
public class TradePushParameters implements WangdianParameters {

  @Parameter
  public String shop_no;

  @Parameter(converter = DefaultJsonParameterConverter.class)
  public Collection<Trade> trade_list;


  public static class Trade {

    public String tid;

    public Integer trade_status;

    public Integer pay_status;

    public Integer delivery_term;

    public String trade_time;

    public String pay_time;

    public String buyer_nick;

    public String receiver_name;

    public String receiver_province;

    public String receiver_city;

    public String receiver_district;

    public String receiver_address;

    public String receiver_mobile;

    public Integer logistics_type;

    public Integer invoice_kind;

    public String invoice_title;

    public String invoice_content;

    public String buyer_message;

    public String seller_memo;

    public Integer seller_flag;

    public BigDecimal post_amount;

    public BigDecimal cod_amount;

    public BigDecimal ext_cod_fee;

    public BigDecimal other_amount;

    public BigDecimal paid;

    public String warehouse_no;

    public Collection<Order> order_list;
  }

  public static class Order {

    public String oid;

    public BigDecimal num;

    public BigDecimal price;

    public Integer status;

    public Integer refund_status;

    public String goods_id;

    public String spec_id;

    public String goods_no;

    public String spec_no;

    public String goods_name;

    public String spec_name;

    public BigDecimal adjust_amount;

    public BigDecimal discount;

    public BigDecimal share_discount;

    public String remark;
  }
}
