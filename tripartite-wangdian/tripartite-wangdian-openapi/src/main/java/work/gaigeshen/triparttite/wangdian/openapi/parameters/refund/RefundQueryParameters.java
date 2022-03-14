package work.gaigeshen.triparttite.wangdian.openapi.parameters.refund;

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
public class RefundQueryParameters implements WangdianParameters {

  @Parameter
  public Integer process_status;

  @Parameter
  public Integer time_type;

  @Parameter
  public String refund_no;

  @Parameter
  public String src_refund_no;

  @Parameter
  public String trade_no;

  @Parameter
  public String tid;

  @Parameter
  public String shop_no;

  @Parameter
  public String start_time;

  @Parameter
  public String end_time;

  @Parameter
  public Integer page_no;

  @Parameter
  public Integer page_size;
}
