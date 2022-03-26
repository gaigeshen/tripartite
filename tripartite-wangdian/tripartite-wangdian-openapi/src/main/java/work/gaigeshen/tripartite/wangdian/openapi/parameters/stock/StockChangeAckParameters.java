package work.gaigeshen.tripartite.wangdian.openapi.parameters.stock;

import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;
import work.gaigeshen.tripartite.core.parameter.typed.converter.DefaultJsonParameterConverter;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParameters;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParametersCustomizer;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        customizer = WangdianParametersCustomizer.class
)
public class StockChangeAckParameters implements WangdianParameters {

  @Parameter(converter = DefaultJsonParameterConverter.class)
  public Collection<StockSync> stock_sync_list;


  public static class StockSync {

    public Integer rec_id;

    public Integer sync_stock;

    public Integer stock_change_count;
  }
}
