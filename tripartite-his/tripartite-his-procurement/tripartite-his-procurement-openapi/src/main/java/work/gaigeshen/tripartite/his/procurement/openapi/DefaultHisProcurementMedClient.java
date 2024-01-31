package work.gaigeshen.tripartite.his.procurement.openapi;

import org.springframework.web.client.RestTemplate;
import work.gaigeshen.tripartite.core.RestTemplateUtils;
import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

/**
 * @author gaigeshen
 */
class DefaultHisProcurementMedClient extends HisProcurementAbstractClient implements HisProcurementMedClient {

    DefaultHisProcurementMedClient(HisProcurementConfig config, WebExecutor executor) {
        super(config, executor);
    }

    static DefaultHisProcurementMedClient create(HisProcurementConfig config, AbstractInterceptor... interceptors) {
        RestTemplate restTemplate = new RestTemplate();
        RestTemplateUtils.configureTimeout(restTemplate, config.getConnectTimeout(), config.getReadTimeout());
        RestTemplateWebExecutor executor = RestTemplateWebExecutor.create(restTemplate);
        executor.setInterceptors(interceptors);
        executor.setParametersConverter(new ParametersMetadataParametersConverter(config));
        return new DefaultHisProcurementMedClient(config, executor);
    }

}
