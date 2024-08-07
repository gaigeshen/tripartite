package work.gaigeshen.tripartite.his.procurement.openapi.parameters;

import lombok.Getter;
import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

/**
 * @author gaigeshen
 */
@Parameters(converter = JsonParametersConverter.class)
@Getter
public class HisProcurementAccessTokenParameters implements HisProcurementParameters {

    @Parameter
    public final String appCode;

    @Parameter
    public final String authCode;

    public HisProcurementAccessTokenParameters(String appCode, String authCode) {
        ArgumentValidate.notNull(appCode, "appCode cannot be null");
        ArgumentValidate.notNull(authCode, "authCode cannot be null");
        this.appCode = appCode;
        this.authCode = authCode;
    }

}
