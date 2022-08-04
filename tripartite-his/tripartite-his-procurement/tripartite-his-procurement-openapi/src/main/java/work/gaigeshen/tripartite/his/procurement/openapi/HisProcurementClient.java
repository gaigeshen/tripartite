package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.DefaultHisProcurementParameters;
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
  default HisProcurementDirectoryAddResponse addDirectory(HisProcurementDirectoryAddInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9701", inputData), HisProcurementDirectoryAddResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 获取挂网目录
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementDirectoryListResponse listDirectory(HisProcurementDirectoryListInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9700", inputData), HisProcurementDirectoryListResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 获取常用目录
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementDirectoryUsedListResponse listUsedDirectory(HisProcurementDirectoryUsedListInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9702", inputData), HisProcurementDirectoryUsedListResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 创建采购订单
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementPurchaseOrderCreateResponse createPurchaseOrder(HisProcurementPurchaseOrderCreateInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9704", inputData), HisProcurementPurchaseOrderCreateResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 保存或发送采购订单
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementPurchaseOrderSendResponse sendPurchaseOrder(HisProcurementPurchaseOrderSendInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9706", inputData), HisProcurementPurchaseOrderSendResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 撤销采购订单
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementPurchaseOrderCancelResponse cancelPurchaseOrder(HisProcurementPurchaseOrderCancelInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9707", inputData), HisProcurementPurchaseOrderCancelResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 查询采购订单
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementPurchaseOrderListResponse listPurchaseOrder(HisProcurementPurchaseOrderListInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9708", inputData), HisProcurementPurchaseOrderListResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 医疗机构收获
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementTakeDeliveryResponse takeDelivery(HisProcurementTakeDeliveryInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9709", inputData), HisProcurementTakeDeliveryResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 创建提交退货申请
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementReturnApplyResponse applyReturn(HisProcurementReturnApplyInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9710", inputData), HisProcurementReturnApplyResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 获取退货订单信息
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 响应结果不为空
   */
  default HisProcurementReturnListResponse listReturn(HisProcurementReturnListInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9711", inputData), HisProcurementReturnListResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 获取产品配送企业
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementProductDeliveryEnterpriseListResponse listProductDeliveryEnterprise(
          HisProcurementProductDeliveryEnterpriseListInputData inputData) throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9713", inputData),
            HisProcurementProductDeliveryEnterpriseListResponse.class, getHisProcurementConfig().getServiceUri());
  }

  /**
   * 设置产品配送企业
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementProductDeliveryEnterpriseSetResponse setProductDeliveryEnterprise(
          HisProcurementProductDeliveryEnterpriseSetInputData inputData) throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9714", inputData),
            HisProcurementProductDeliveryEnterpriseSetResponse.class, getHisProcurementConfig().getServiceUri());
  }

  /**
   * 创建结算单
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementStatementCreateResponse createStatement(HisProcurementStatementCreateInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9716", inputData), HisProcurementStatementCreateResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 添加结算单明细
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementStatementDetailAddResponse addStatementDetail(HisProcurementStatementDetailAddInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9717", inputData), HisProcurementStatementDetailAddResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 提交结算单
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementStatementSubmitResponse submitStatement(HisProcurementStatementSubmitInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9718", inputData), HisProcurementStatementSubmitResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 审核结算单
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementStatementExamineResponse examineStatement(HisProcurementStatementExamineInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9719", inputData), HisProcurementStatementExamineResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 获取库房信息
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementStorehouseListResponse listStorehouse(HisProcurementStorehouseListInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9604", inputData), HisProcurementStorehouseListResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 保存库房信息
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementStorehouseSaveResponse saveStorehouse(HisProcurementStorehouseSaveInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9605", inputData), HisProcurementStorehouseSaveResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

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
  <R extends HisProcurementResponse> R execute(HisProcurementParameters parameters, Class<R> responseClass, String uri)
          throws HisProcurementClientException;

}
