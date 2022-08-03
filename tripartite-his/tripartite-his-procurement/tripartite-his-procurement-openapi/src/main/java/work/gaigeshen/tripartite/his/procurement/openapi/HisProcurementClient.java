package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementParameters;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.*;
import work.gaigeshen.tripartite.his.procurement.openapi.response.*;

/**
 * 浙江省医疗保障信息平台客户端
 *
 * @author gaigeshen
 */
public interface HisProcurementClient {

  /**
   * 返回此客户端的配置
   *
   * @return 此客户端的配置
   */
  HisProcurementConfig getHisProcurementConfig();

  /**
   * 勾选本院目录
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  HisProcurementDirectoryAddResponse addDirectory(HisProcurementDirectoryAddInputData inputData) throws HisProcurementClientException;

  /**
   * 获取挂网目录
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  HisProcurementDirectoryListResponse listDirectory(HisProcurementDirectoryListInputData inputData) throws HisProcurementClientException;

  /**
   * 获取常用目录
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  HisProcurementDirectoryUsedListResponse listUsedDirectory(HisProcurementDirectoryUsedListInputData inputData) throws HisProcurementClientException;

  /**
   * 创建采购订单
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  HisProcurementPurchaseOrderCreateResponse createPurchaseOrder(HisProcurementPurchaseOrderCreateInputData inputData) throws HisProcurementClientException;

  /**
   * 保存或发送采购订单
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  HisProcurementPurchaseOrderSendResponse sendPurchaseOrder(HisProcurementPurchaseOrderSendInputData inputData) throws HisProcurementClientException;

  /**
   * 撤销采购订单
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  HisProcurementPurchaseOrderCancelResponse cancelPurchaseOrder(HisProcurementPurchaseOrderCancelInputData inputData) throws HisProcurementClientException;

  /**
   * 此方法接受任何请求参数，然后执行请求并返回对应的响应结果
   *
   * @param parameters 请求参数不能为空
   * @param responseClass 响应结果类型不能为空
   * @param uri 该请求对应的服务器地址不能为空
   * @param <R> 表示响应结果类型
   * @return 响应结果不会为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  <R extends HisProcurementResponse> R execute(HisProcurementParameters parameters, Class<R> responseClass, String uri) throws HisProcurementClientException;

}
