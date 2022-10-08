package work.gaigeshen.tripartite.ding.openapi;

import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

/**
 *
 * @author gaigeshen
 */
public class DingAccessTokenClient extends DingAbstractClient {

    protected DingAccessTokenClient(DingConfig config, WebExecutor executor) {
        super(config, executor);
    }

    public static DingAccessTokenClient create(DingConfig config) {
        RestTemplateWebExecutor executor = RestTemplateWebExecutor.create();
        executor.setParametersConverter(new ParametersMetadataParametersConverter(config));
        return new DingAccessTokenClient(config, executor);
    }
}
