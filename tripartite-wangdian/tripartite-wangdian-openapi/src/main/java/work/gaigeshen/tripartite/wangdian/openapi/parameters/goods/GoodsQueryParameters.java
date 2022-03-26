package work.gaigeshen.tripartite.wangdian.openapi.parameters.goods;

import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParametersCustomizer;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParameters;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        customizer = WangdianParametersCustomizer.class
)
public class GoodsQueryParameters implements WangdianParameters {

  @Parameter
  public String spec_no;

  @Parameter
  public String goods_no;

  @Parameter
  public String start_time;

  @Parameter
  public String end_time;

  @Parameter
  public Integer deleted;

  @Parameter
  public Integer page_no;

  @Parameter
  public Integer page_size;
}
