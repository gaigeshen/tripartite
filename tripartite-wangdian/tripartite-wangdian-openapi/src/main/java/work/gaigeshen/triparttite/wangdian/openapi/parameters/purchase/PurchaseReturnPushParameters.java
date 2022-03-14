package work.gaigeshen.triparttite.wangdian.openapi.parameters.purchase;

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
public class PurchaseReturnPushParameters implements WangdianParameters {

  @Parameter(converter = GsonJsonParameterConverter.class)
  public Return return_info;


  public static class Return {

    public String provider_no;

    public String outer_no;

    public String warehouse_no;

    public Integer is_check;

    public String purchaser_no;

    public String receiver_province;

    public String receiver_city;

    public String receiver_district;

    public String address;

    public String contact;

    public BigDecimal post_fee;

    public String remark;

    public String purchase_no;

    public Collection<Detail> detail_list;
  }

  public static class Detail {

    public String spec_no;

    public BigDecimal num;

    public BigDecimal price;

    public BigDecimal tax;

    public BigDecimal discount;

    public String detail_remark;
  }
}
