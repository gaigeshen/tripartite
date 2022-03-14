package work.gaigeshen.triparttite.wangdian.openapi.parameters.refund;

import work.gaigeshen.triparttite.core.parameter.converter.Parameters;
import work.gaigeshen.triparttite.core.parameter.typed.Parameter;
import work.gaigeshen.triparttite.core.parameter.typed.converter.GsonJsonParameterConverter;
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
public class SalesRefundPushParameters implements WangdianParameters {

  @Parameter(converter = GsonJsonParameterConverter.class)
  public Collection<Refund> api_refund_list;


  public static class Refund {

    public Integer platform_id;

    public String shop_no;

    public String tid;

    public String refund_no;

    public Integer type;

    public String status;

    public BigDecimal refund_fee;

    public String buyer_nick;

    public String refund_time;

    public String reason;

    public String desc;

    public String logistics_no;

    public String logistics_name;

    public Collection<Order> order_list;
  }

  public static class Order {

    public String oid;

    public BigDecimal num;
  }
}
