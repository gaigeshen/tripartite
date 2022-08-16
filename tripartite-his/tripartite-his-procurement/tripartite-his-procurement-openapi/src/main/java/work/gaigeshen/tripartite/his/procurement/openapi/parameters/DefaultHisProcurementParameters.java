package work.gaigeshen.tripartite.his.procurement.openapi.parameters;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class,
        customizer = HisProcurementParametersCustomizer.class
)
public class DefaultHisProcurementParameters implements HisProcurementParameters {

  @Parameter(name = "infno")
  private final String interfaceCode;

  @Parameter(name = "input")
  private final InputParameter inputParameter;

  public DefaultHisProcurementParameters(String interfaceCode, HisProcurementInputData inputData) {
    if (Objects.isNull(interfaceCode)) {
      throw new IllegalArgumentException("interfaceCode cannot be null");
    }
    if (Objects.isNull(inputData)) {
      throw new IllegalArgumentException("inputData cannot be null");
    }
    this.interfaceCode = interfaceCode;
    this.inputParameter = new InputParameter(inputData);
  }

  public String getInterfaceCode() {
    return interfaceCode;
  }

  public InputParameter getInputParameter() {
    return inputParameter;
  }

  /**
   *
   * @author gaigeshen
   */
  public static class InputParameter {

    @Parameter(name = "data")
    private final HisProcurementInputData inputData;

    public InputParameter(HisProcurementInputData inputData) {
      this.inputData = inputData;
    }

    public HisProcurementInputData getInputData() {
      return inputData;
    }
  }
}
