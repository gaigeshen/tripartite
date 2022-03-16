package work.gaigeshen.triparttite.wangdian.openapi.parameters.refund;

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
public class StockInRefundPushParameters implements WangdianParameters {

  @Parameter(converter = DefaultJsonParameterConverter.class)
  public StockInRefund stockin_refund_info;


  public static class StockInRefund {

    public String refund_no;

    public String outer_no;

    public String warehouse_no;

    public Integer is_check;

    public String remark;

    public Collection<Detail> detail_list;
  }

  public static class Detail {

    public String spec_no;

    public BigDecimal stockin_num;

    public String remark;
  }
}
