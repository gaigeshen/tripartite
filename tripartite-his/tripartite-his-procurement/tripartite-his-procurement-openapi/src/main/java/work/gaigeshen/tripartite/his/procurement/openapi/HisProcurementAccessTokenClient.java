package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

/**
 * @author gaigeshen
 */
public class HisProcurementAccessTokenClient extends HisProcurementAbstractClient {

    protected HisProcurementAccessTokenClient(HisProcurementConfig config, WebExecutor executor) {
        super(config, executor);
    }

    public static HisProcurementAccessTokenClient create(HisProcurementConfig config) {
        RestTemplateWebExecutor executor = RestTemplateWebExecutor.create();
        executor.setInterceptors(new HisProcurementClientRequestResponseInterceptor(config));
        executor.setParametersConverter(new ParametersMetadataParametersConverter(config));
        return new HisProcurementAccessTokenClient(config, executor);
    }

}
