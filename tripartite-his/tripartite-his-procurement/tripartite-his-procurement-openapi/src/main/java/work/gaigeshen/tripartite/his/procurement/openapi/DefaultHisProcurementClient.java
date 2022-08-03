package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebException;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;
import work.gaigeshen.tripartite.core.response.consumer.ResponseConsumer;
import work.gaigeshen.tripartite.core.response.converter.ResponseConverter;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementParameters;
import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;
import work.gaigeshen.tripartite.his.procurement.openapi.response.HisProcurementResponse;

import java.util.Objects;

/**
 * @author gaigeshen
 */
public class DefaultHisProcurementClient implements HisProcurementClient {

  private final HisProcurementConfig config;

  private final WebExecutor executor;

  protected DefaultHisProcurementClient(HisProcurementConfig config, WebExecutor executor) {
    if (Objects.isNull(config)) {
      throw new IllegalArgumentException("config cannot be null");
    }
    if (Objects.isNull(executor)) {
      throw new IllegalArgumentException("web executor cannot be null");
    }
    this.config = config;
    this.executor = executor;
  }

  public static DefaultHisProcurementClient create(HisProcurementConfig config, AbstractInterceptor... interceptors) {
    if (Objects.isNull(interceptors)) {
      throw new IllegalArgumentException("interceptors cannot be null");
    }
    RestTemplateWebExecutor executor = RestTemplateWebExecutor.create();
    executor.setInterceptors(interceptors);
    executor.setParametersConverter(new ParametersMetadataParametersConverter(config));
    return new DefaultHisProcurementClient(config, executor);
  }

  public static DefaultHisProcurementClient create(HisProcurementConfig config, WebExecutor executor) {
    return new DefaultHisProcurementClient(config, executor);
  }

  @Override
  public HisProcurementConfig getHisProcurementConfig() {
    return config;
  }

  @Override
  public <R extends HisProcurementResponse> R execute(HisProcurementParameters parameters, Class<R> responseClass) throws HisProcurementClientException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("parameters cannot be null");
    }
    if (Objects.isNull(responseClass)) {
      throw new IllegalArgumentException("response class cannot be null");
    }
    try {
      R response = executor.execute(config.getServerUrl(), parameters, responseClass);
      return validateResponse(response);
    } catch (WebException e) {
      throw new HisProcurementClientException(e.getMessage(), e);
    }
  }

  @Override
  public <R extends HisProcurementResponse> R execute(HisProcurementParameters parameters, ResponseConverter<R> converter) throws HisProcurementClientException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("parameters cannot be null");
    }
    if (Objects.isNull(converter)) {
      throw new IllegalArgumentException("response converter cannot be null");
    }
    try {
      R response = executor.execute(config.getServerUrl(), parameters, converter);
      return validateResponse(response);
    } catch (WebException e) {
      throw new HisProcurementClientException(e.getMessage(), e);
    }
  }

  @Override
  public void execute(HisProcurementParameters parameters, ResponseConsumer consumer) throws HisProcurementClientException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("parameters cannot be null");
    }
    if (Objects.isNull(consumer)) {
      throw new IllegalArgumentException("response consumer cannot be null");
    }
    try {
      executor.execute(config.getServerUrl(), parameters, consumer);
    } catch (WebException e) {
      throw new HisProcurementClientException(e.getMessage(), e);
    }
  }

  protected <R extends HisProcurementResponse> R validateResponse(R response) throws HisProcurementClientException {
    if (Objects.isNull(response)) {
      throw new HisProcurementClientException("could not validate null response");
    }
    if (response instanceof AbstractHisProcurementResponse) {
      AbstractHisProcurementResponse abstractResponse = (AbstractHisProcurementResponse) response;
      if (!Objects.equals(abstractResponse.returnCode, 0)) {
        throw new HisProcurementClientException("[ " + abstractResponse.returnCode + " ] " + abstractResponse.returnMsg);
      }
    }
    return response;
  }

}
