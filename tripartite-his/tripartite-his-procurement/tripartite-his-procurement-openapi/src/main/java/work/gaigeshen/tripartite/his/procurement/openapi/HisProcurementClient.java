package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.core.response.consumer.ResponseConsumer;
import work.gaigeshen.tripartite.core.response.converter.ResponseConverter;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementParameters;
import work.gaigeshen.tripartite.his.procurement.openapi.response.HisProcurementResponse;

/**
 *
 * @author gaigeshen
 */
public interface HisProcurementClient {

  HisProcurementConfig getHisProcurementConfig();

  /**
   * 此方法接受任何请求参数，然后执行请求并返回对应的响应结果
   *
   * @param parameters 请求参数不能为空
   * @param responseClass 响应结果类型不能为空
   * @param <R> 表示响应结果类型
   * @return 响应结果不会为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  <R extends HisProcurementResponse> R execute(HisProcurementParameters parameters, Class<R> responseClass) throws HisProcurementClientException;

  /**
   * 此方法接受任何请求参数，然后执行请求并返回对应的响应结果，可以指定响应结果数据转换器
   *
   * @param parameters 请求参数不能为空
   * @param converter 响应结果数据转换器不能为空
   * @param <R> 表示响应结果类型
   * @return 响应结果不会为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  <R extends HisProcurementResponse> R execute(HisProcurementParameters parameters, ResponseConverter<R> converter) throws HisProcurementClientException;

  /**
   * 此方法接受任何请求参数，然后执行请求并回调响应结果数据消费器
   *
   * @param parameters 请求参数不能为空
   * @param consumer 响应结果数据消费器不能为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  void execute(HisProcurementParameters parameters, ResponseConsumer consumer) throws HisProcurementClientException;
}
