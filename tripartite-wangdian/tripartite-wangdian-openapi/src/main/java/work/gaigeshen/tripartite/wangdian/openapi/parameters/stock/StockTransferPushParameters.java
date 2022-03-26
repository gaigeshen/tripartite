package work.gaigeshen.tripartite.wangdian.openapi.parameters.stock;

import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;
import work.gaigeshen.tripartite.core.parameter.typed.converter.DefaultJsonParameterConverter;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParametersCustomizer;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParameters;

import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        customizer = WangdianParametersCustomizer.class
)
public class StockTransferPushParameters implements WangdianParameters {

  @Parameter(converter = DefaultJsonParameterConverter.class)
  public Transfer transfer_info;


  public static class Transfer {

    public String outer_no;

    public String from_warehouse_no;

    public String to_warehouse_no;

    public String address;

    public String contact;

    public String remark;

    public Integer transfer_type;

    public Integer mode;

    public Integer autocheck;

    public Collection<Sku> skus;
  }

  public static class Sku {

    public String spec_no;

    public String from_position_no;

    public String to_position_no;

    public BigDecimal num;

    public String remark;
  }
}
