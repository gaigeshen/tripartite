package work.gaigeshen.triparttite.wangdian.openapi.parameters.purchase;

import work.gaigeshen.triparttite.core.parameter.converter.Parameters;
import work.gaigeshen.triparttite.core.parameter.typed.Parameter;
import work.gaigeshen.triparttite.wangdian.openapi.parameters.WangdianParameters;
import work.gaigeshen.triparttite.wangdian.openapi.parameters.WangdianParametersCustomizer;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        customizer = WangdianParametersCustomizer.class
)
public class PurchaseProviderQueryParameters implements WangdianParameters {

  @Parameter
  public String column;

  @Parameter
  public String provider_no;

  @Parameter
  public String provider_name;

  @Parameter
  public Integer page_no;

  @Parameter
  public Integer page_size;
}
