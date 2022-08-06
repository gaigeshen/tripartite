package work.gaigeshen.tripartite.his.procurement.openapi.parameters;

import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;

/**
 *
 * @author gaigeshen
 */
@Parameters
public class HisProcurementAccessTokenParameters implements HisProcurementParameters {

  @Parameter
  public String appCode;

  @Parameter
  public String authCode;
}
