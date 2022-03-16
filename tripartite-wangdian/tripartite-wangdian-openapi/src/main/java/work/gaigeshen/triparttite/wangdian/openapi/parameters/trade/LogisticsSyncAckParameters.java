package work.gaigeshen.triparttite.wangdian.openapi.parameters.trade;

import work.gaigeshen.triparttite.core.parameter.converter.Parameters;
import work.gaigeshen.triparttite.core.parameter.typed.Parameter;
import work.gaigeshen.triparttite.core.parameter.typed.converter.DefaultJsonParameterConverter;
import work.gaigeshen.triparttite.wangdian.openapi.parameters.WangdianParameters;
import work.gaigeshen.triparttite.wangdian.openapi.parameters.WangdianParametersCustomizer;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        customizer = WangdianParametersCustomizer.class
)
public class LogisticsSyncAckParameters implements WangdianParameters {

  @Parameter(converter = DefaultJsonParameterConverter.class)
  public Collection<Logistics> logistics_list;


  public static class Logistics {

    public Integer rec_id;

    public Integer status;

    public String message;
  }
}
