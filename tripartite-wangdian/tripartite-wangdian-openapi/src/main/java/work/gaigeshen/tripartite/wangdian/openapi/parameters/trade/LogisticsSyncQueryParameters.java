package work.gaigeshen.tripartite.wangdian.openapi.parameters.trade;

import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParameters;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParametersCustomizer;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        customizer = WangdianParametersCustomizer.class
)
public class LogisticsSyncQueryParameters implements WangdianParameters {

  @Parameter
  public Integer limit;

  @Parameter
  public String shop_no;

  @Parameter
  public Integer is_part_sync_able;
}
