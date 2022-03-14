package work.gaigeshen.triparttite.wangdian.openapi.parameters.trade;

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
public class TradeQueryParameters implements WangdianParameters {

  @Parameter
  public Integer status;

  @Parameter
  public String src_tid;

  @Parameter
  public String trade_no;

  @Parameter
  public String shop_no;

  @Parameter
  public String warehouse_no;

  @Parameter
  public String logistics_no;

  @Parameter
  public String start_time;

  @Parameter
  public String end_time;

  @Parameter
  public Integer page_no;

  @Parameter
  public Integer page_size;
}
