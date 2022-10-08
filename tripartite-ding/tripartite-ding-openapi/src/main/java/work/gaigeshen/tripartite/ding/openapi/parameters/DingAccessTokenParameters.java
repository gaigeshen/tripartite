package work.gaigeshen.tripartite.ding.openapi.parameters;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class
)
public class DingAccessTokenParameters implements DingParameters {

    @Parameter
    public final String appKey;

    @Parameter
    public final String appSecret;

    public DingAccessTokenParameters(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }
}
