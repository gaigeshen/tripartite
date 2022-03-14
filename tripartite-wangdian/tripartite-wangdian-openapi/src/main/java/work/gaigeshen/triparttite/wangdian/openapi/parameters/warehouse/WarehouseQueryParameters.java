package work.gaigeshen.triparttite.wangdian.openapi.parameters.warehouse;

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
public class WarehouseQueryParameters implements WangdianParameters {

  @Parameter
  public String warehouse_no;

  @Parameter
  public Integer page_no;

  @Parameter
  public Integer page_size;

}
