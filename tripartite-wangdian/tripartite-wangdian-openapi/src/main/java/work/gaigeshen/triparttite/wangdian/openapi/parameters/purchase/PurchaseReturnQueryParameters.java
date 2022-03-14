package work.gaigeshen.triparttite.wangdian.openapi.parameters.purchase;

import work.gaigeshen.triparttite.core.parameter.converter.Parameters;
import work.gaigeshen.triparttite.core.parameter.typed.Parameter;
import work.gaigeshen.triparttite.wangdian.openapi.parameters.WangdianParameters;
import work.gaigeshen.triparttite.wangdian.openapi.parameters.WangdianParametersCustomizer;

/**
 * @author gaigeshen
 */
@Parameters(
        customizer = WangdianParametersCustomizer.class
)
public class PurchaseReturnQueryParameters implements WangdianParameters {

  @Parameter
  public Integer status;

  @Parameter
  public String warehouse_no;

  @Parameter
  public String return_no;

  @Parameter
  public String api_outer_no;

  @Parameter
  public String start_time;

  @Parameter
  public String end_time;

  @Parameter
  public Integer page_no;

  @Parameter
  public Integer page_size;

}
