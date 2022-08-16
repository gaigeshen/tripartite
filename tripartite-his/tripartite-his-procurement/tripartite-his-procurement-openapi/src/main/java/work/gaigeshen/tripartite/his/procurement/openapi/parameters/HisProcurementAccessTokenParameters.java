package work.gaigeshen.tripartite.his.procurement.openapi.parameters;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
@Parameters(converter = JsonParametersConverter.class)
public class HisProcurementAccessTokenParameters implements HisProcurementParameters {

  @Parameter
  public final String appCode;

  @Parameter
  public final String authCode;

  public HisProcurementAccessTokenParameters(String appCode, String authCode) {
    if (Objects.isNull(appCode)) {
      throw new IllegalArgumentException("appCode cannot be null");
    }
    if (Objects.isNull(authCode)) {
      throw new IllegalArgumentException("authCode cannot be null");
    }
    this.appCode = appCode;
    this.authCode = authCode;
  }

  public String getAppCode() {
    return appCode;
  }

  public String getAuthCode() {
    return authCode;
  }
}
