package work.gaigeshen.triparttite.wangdian.openapi.parameters.purchase;

import work.gaigeshen.triparttite.core.parameter.converter.Parameters;
import work.gaigeshen.triparttite.core.parameter.typed.Parameter;
import work.gaigeshen.triparttite.core.parameter.typed.converter.DefaultJsonParameterConverter;
import work.gaigeshen.triparttite.wangdian.openapi.parameters.WangdianParameters;
import work.gaigeshen.triparttite.wangdian.openapi.parameters.WangdianParametersCustomizer;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
@Parameters(
        customizer = WangdianParametersCustomizer.class
)
public class PurchaseReturnOrderPushParameters implements WangdianParameters {

  @Parameter(converter = DefaultJsonParameterConverter.class)
  public PurchaseReturn purchase_return_info;


  public static class PurchaseReturn {

    public String return_no;

    public String outer_no;

    public String logistics_no;

    public BigDecimal post_cost;

    public Integer is_check;

    public String remark;

    public Collection<Detail> detail_list;
  }

  public static class Detail {

    public String spec_no;

    public BigDecimal num;

    public BigDecimal price;

    public String position_no;

    public String batch_no;

    public String remark;
  }
}
