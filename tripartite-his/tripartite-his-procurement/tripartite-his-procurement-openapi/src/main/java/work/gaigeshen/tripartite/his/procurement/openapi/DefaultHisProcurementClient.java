package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebException;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.DefaultHisProcurementParameters;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementParameters;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.*;
import work.gaigeshen.tripartite.his.procurement.openapi.response.*;

import java.util.Objects;

/**
 *
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
  public HisProcurementDirectoryAddResponse addDirectory(HisProcurementDirectoryAddInputData inputData)
          throws HisProcurementClientException {
    DefaultHisProcurementParameters parameters = new DefaultHisProcurementParameters("ZJ9701", inputData);
    return execute(parameters, HisProcurementDirectoryAddResponse.class, config.getServiceUri());
  }

  @Override
  public HisProcurementDirectoryListResponse listDirectory(HisProcurementDirectoryListInputData inputData)
          throws HisProcurementClientException {
    DefaultHisProcurementParameters parameters = new DefaultHisProcurementParameters("ZJ9700", inputData);
    return execute(parameters, HisProcurementDirectoryListResponse.class, config.getServiceUri());
  }

  @Override
  public HisProcurementDirectoryUsedListResponse listUsedDirectory(HisProcurementDirectoryUsedListInputData inputData)
          throws HisProcurementClientException {
    DefaultHisProcurementParameters parameters = new DefaultHisProcurementParameters("ZJ9702", inputData);
    return execute(parameters, HisProcurementDirectoryUsedListResponse.class, config.getServiceUri());
  }

  @Override
  public HisProcurementPurchaseOrderCreateResponse createPurchaseOrder(HisProcurementPurchaseOrderCreateInputData inputData)
          throws HisProcurementClientException {
    DefaultHisProcurementParameters parameters = new DefaultHisProcurementParameters("ZJ9704", inputData);
    return execute(parameters, HisProcurementPurchaseOrderCreateResponse.class, config.getServiceUri());
  }

  @Override
  public HisProcurementPurchaseOrderSendResponse sendPurchaseOrder(HisProcurementPurchaseOrderSendInputData inputData)
          throws HisProcurementClientException {
    DefaultHisProcurementParameters parameters = new DefaultHisProcurementParameters("ZJ9706", inputData);
    return execute(parameters, HisProcurementPurchaseOrderSendResponse.class, config.getServiceUri());
  }

  @Override
  public HisProcurementPurchaseOrderCancelResponse cancelPurchaseOrder(HisProcurementPurchaseOrderCancelInputData inputData)
          throws HisProcurementClientException {
    DefaultHisProcurementParameters parameters = new DefaultHisProcurementParameters("ZJ9707", inputData);
    return execute(parameters, HisProcurementPurchaseOrderCancelResponse.class, config.getServiceUri());
  }

  @Override
  public <R extends HisProcurementResponse> R execute(HisProcurementParameters parameters, Class<R> responseClass, String uri)
          throws HisProcurementClientException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("parameters cannot be null");
    }
    if (Objects.isNull(responseClass)) {
      throw new IllegalArgumentException("response class cannot be null");
    }
    try {
      R response = executor.execute(config.getServerHost() + uri, parameters, responseClass);
      return validateResponse(response);
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
