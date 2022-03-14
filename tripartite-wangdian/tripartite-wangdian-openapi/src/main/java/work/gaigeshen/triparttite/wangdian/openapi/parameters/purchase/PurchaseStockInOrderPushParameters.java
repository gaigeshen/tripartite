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
public class PurchaseStockInOrderPushParameters implements WangdianParameters {

  @Parameter(converter = GsonJsonParameterConverter.class)
  public Purchase purchase_info;


  public static class Purchase {

    public String purchase_no;

    public String outer_no;

    public String warehouse_no;

    public String remark;

    public Integer is_check;

    public Collection<Detail> details_list;
  }

  public static class Detail {

    public String spec_no;

    public BigDecimal stockin_num;

    public BigDecimal stockin_price;

    public BigDecimal tax_price;

    public BigDecimal tax;

    public String position_no;

    public String production_date;

    public BigDecimal validity_days;

    public BigDecimal src_price;

    public String remark;
  }
}
