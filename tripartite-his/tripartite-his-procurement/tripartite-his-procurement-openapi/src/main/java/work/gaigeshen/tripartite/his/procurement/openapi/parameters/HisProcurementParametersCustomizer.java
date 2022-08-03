package work.gaigeshen.tripartite.his.procurement.openapi.parameters;

import work.gaigeshen.tripartite.core.parameter.Parameters;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersCustomizer;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersCustomizingException;

/**
 * @author gaigeshen
 */
public class HisProcurementParametersCustomizer implements ParametersCustomizer {

  @Override
  public void beforeConvert(Object rawParameters, Object config) throws ParametersCustomizingException {

  }

  @Override
  public void customize(Parameters parameters, Object rawParameters, Object config) throws ParametersCustomizingException {

  }

  @Override
  public boolean supports(Object config) {
    return false;
  }
}
