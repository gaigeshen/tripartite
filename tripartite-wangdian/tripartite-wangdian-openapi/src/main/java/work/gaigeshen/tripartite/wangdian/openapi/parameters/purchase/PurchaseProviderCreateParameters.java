package work.gaigeshen.tripartite.wangdian.openapi.parameters.purchase;

import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParameters;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParametersCustomizer;

import java.math.BigDecimal;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        customizer = WangdianParametersCustomizer.class
)
public class PurchaseProviderCreateParameters implements WangdianParameters {

  @Parameter
  public String provider_no;

  @Parameter
  public String provider_name;

  @Parameter
  public BigDecimal min_purchase_num;

  @Parameter
  public Integer purchase_cycle_days;

  @Parameter
  public Integer arrive_cycle_days;

  @Parameter
  public String contact;

  @Parameter
  public String mobile;

  @Parameter
  public String address;

  @Parameter
  public Integer is_disabled;
}
