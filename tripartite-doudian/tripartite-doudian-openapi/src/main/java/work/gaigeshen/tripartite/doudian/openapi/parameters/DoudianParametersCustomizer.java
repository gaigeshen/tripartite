package work.gaigeshen.tripartite.doudian.openapi.parameters;

import work.gaigeshen.tripartite.core.parameter.Parameters;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersCustomizer;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersCustomizingException;
import work.gaigeshen.tripartite.doudian.openapi.config.DoudianConfig;

/**
 * @author gaigeshen
 */
public class DoudianParametersCustomizer implements ParametersCustomizer {

  @Override
  public void beforeConvert(Object rawParameters, Object config) throws ParametersCustomizingException {

  }

  @Override
  public void customize(Parameters parameters, Object rawParameters, Object config) throws ParametersCustomizingException {

  }

  @Override
  public boolean supports(Object config) {
    return config instanceof DoudianConfig;
  }
}
