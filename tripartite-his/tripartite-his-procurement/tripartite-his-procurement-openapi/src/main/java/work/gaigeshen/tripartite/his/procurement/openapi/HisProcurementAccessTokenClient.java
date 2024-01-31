package work.gaigeshen.tripartite.his.procurement.openapi;

import org.springframework.web.client.RestTemplate;
import work.gaigeshen.tripartite.core.RestTemplateUtils;
import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

/**
 * @author gaigeshen
 */
class HisProcurementAccessTokenClient extends HisProcurementAbstractClient {

    HisProcurementAccessTokenClient(HisProcurementConfig config, WebExecutor executor) {
        super(config, executor);
    }

    static HisProcurementAccessTokenClient create(HisProcurementConfig config) {
        RestTemplate restTemplate = new RestTemplate();
        RestTemplateUtils.configureTimeout(restTemplate, config.getConnectTimeout(), config.getReadTimeout());
        RestTemplateWebExecutor executor = RestTemplateWebExecutor.create(restTemplate);
        executor.setInterceptors(new HisProcurementClientRequestResponseInterceptor(config));
        executor.setParametersConverter(new ParametersMetadataParametersConverter(config));
        return new HisProcurementAccessTokenClient(config, executor);
    }

}
