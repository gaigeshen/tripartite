package work.gaigeshen.tripartite.wangdian.openapi.parameters.stock;

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
public class StockQueryParameters implements WangdianParameters {

  @Parameter
  public String warehouse_no;

  @Parameter
  public String spec_no;

  @Parameter
  public Integer is_deleted;

  @Parameter
  public String barcode;

  @Parameter
  public String start_time;

  @Parameter
  public String end_time;

  @Parameter
  public Integer page_no;

  @Parameter
  public Integer page_size;
}
