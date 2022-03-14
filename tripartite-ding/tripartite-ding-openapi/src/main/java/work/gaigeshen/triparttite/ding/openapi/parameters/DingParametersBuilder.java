package work.gaigeshen.triparttite.ding.openapi.parameters;

import work.gaigeshen.triparttite.core.parameter.Parameters;
import work.gaigeshen.triparttite.core.parameter.converter.ParametersConverter;
import work.gaigeshen.triparttite.ding.openapi.config.DingConfig;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public abstract class DingParametersBuilder {

  private final DingParameters parameters;

  public DingParametersBuilder(DingParameters parameters) {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("parameters cannot be null");
    }
    this.parameters = parameters;
  }

  public final Parameters build(DingConfig config) {
    if (Objects.isNull(config)) {
      throw new IllegalArgumentException("config cannot be null");
    }
    Parameters newParameters = ParametersConverter.convertJson(parameters);
    overrideParameters(newParameters, config);
    return newParameters;
  }

  protected void overrideParameters(Parameters parameters, DingConfig config) {}
}
