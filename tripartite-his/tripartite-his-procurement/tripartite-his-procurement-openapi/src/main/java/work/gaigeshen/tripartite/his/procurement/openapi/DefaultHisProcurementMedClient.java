package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

import java.util.Objects;

/**
 * @author gaigeshen
 */
public class DefaultHisProcurementMedClient extends HisProcurementAbstractClient implements HisProcurementMedClient {

  protected DefaultHisProcurementMedClient(HisProcurementConfig config, WebExecutor executor) {
    super(config, executor);
  }

  public static DefaultHisProcurementMedClient create(HisProcurementConfig config, AbstractInterceptor... interceptors) {
    if (Objects.isNull(interceptors)) {
      throw new IllegalArgumentException("interceptors cannot be null");
    }
    RestTemplateWebExecutor executor = RestTemplateWebExecutor.create();
    executor.setInterceptors(interceptors);
    executor.setParametersConverter(new ParametersMetadataParametersConverter(config));
    return new DefaultHisProcurementMedClient(config, executor);
  }

}
