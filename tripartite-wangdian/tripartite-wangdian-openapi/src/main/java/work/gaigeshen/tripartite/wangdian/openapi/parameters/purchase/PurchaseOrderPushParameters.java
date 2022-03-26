package work.gaigeshen.tripartite.wangdian.openapi.parameters.purchase;

import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;
import work.gaigeshen.tripartite.core.parameter.typed.converter.DefaultJsonParameterConverter;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParameters;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParametersCustomizer;

import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        customizer = WangdianParametersCustomizer.class
)
public class PurchaseOrderPushParameters implements WangdianParameters {

  @Parameter(converter = DefaultJsonParameterConverter.class)
  public Purchase purchase_info;


  public static class Purchase {

    public String provider_no;

    public String warehouse_no;

    public String outer_no;

    public Integer is_use_outer_no;

    public Integer is_check;

    public String contact;

    public String purchase_name;

    public String receive_address;

    public Integer logistics_type;

    public String expect_arrive_time;

    public String remark;

    public Collection<Detail> details_list;
  }

  public static class Detail {

    public String spec_no;

    public BigDecimal num;

    public BigDecimal price;

    public BigDecimal discount;

    public BigDecimal tax;

    public BigDecimal tax_price;

    public String remark;
  }
}
