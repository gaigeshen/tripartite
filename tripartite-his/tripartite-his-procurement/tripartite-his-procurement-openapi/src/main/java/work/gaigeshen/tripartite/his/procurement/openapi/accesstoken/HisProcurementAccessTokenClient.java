package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;
import work.gaigeshen.tripartite.his.procurement.openapi.DefaultHisProcurementClient;
import work.gaigeshen.tripartite.his.procurement.openapi.HisProcurementClientResponseInterceptor;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementAccessTokenClient extends DefaultHisProcurementClient {

  protected HisProcurementAccessTokenClient(HisProcurementConfig config, WebExecutor executor) {
    super(config, executor);
  }

  public static DefaultHisProcurementClient create(HisProcurementConfig config) {
    RestTemplateWebExecutor executor = RestTemplateWebExecutor.create();
    executor.setInterceptors(HisProcurementClientResponseInterceptor.INSTANCE);
    executor.setParametersConverter(new ParametersMetadataParametersConverter(config));
    return new HisProcurementAccessTokenClient(config, executor);
  }

}
