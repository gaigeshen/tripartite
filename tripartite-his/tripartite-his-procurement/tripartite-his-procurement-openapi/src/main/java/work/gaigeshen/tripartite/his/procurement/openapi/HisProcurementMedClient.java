package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.DefaultHisProcurementParameters;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.med.*;
import work.gaigeshen.tripartite.his.procurement.openapi.response.med.*;

/**
 * 浙江省医保信息平台客户端，此客户端只针对药品类型
 *
 * @author gaigeshen
 */
public interface HisProcurementMedClient extends HisProcurementBasicClient {

  /**
   * 获取常用目录
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementDirectoryUsedListResponse listUsedDirectories(HisProcurementDirectoryUsedListInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9652", inputData), HisProcurementDirectoryUsedListResponse.class,
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
    return execute(new DefaultHisProcurementParameters("ZJ9653", inputData), HisProcurementPurchaseOrderCreateResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 添加采购订单明细
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementPurchaseOrderDetailAddResponse addPurchaseOrderDetails(HisProcurementPurchaseOrderDetailAddInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9654", inputData), HisProcurementPurchaseOrderDetailAddResponse.class,
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
    return execute(new DefaultHisProcurementParameters("ZJ9655", inputData), HisProcurementPurchaseOrderCancelResponse.class,
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
    return execute(new DefaultHisProcurementParameters("ZJ9656", inputData), HisProcurementPurchaseOrderSendResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 获取采购订单明细
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementPurchaseOrderListResponse listPurchaseOrders(HisProcurementPurchaseOrderListInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9670", inputData), HisProcurementPurchaseOrderListResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 获取订单明细
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementOrderListResponse listOrders(HisProcurementOrderListInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9657", inputData), HisProcurementOrderListResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 获取配送明细
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementShipmentListResponse listShipments(HisProcurementShipmentListInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9671", inputData), HisProcurementShipmentListResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 医疗机构收货
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementTakeDeliveryResponse takeDelivery(HisProcurementTakeDeliveryInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9658", inputData), HisProcurementTakeDeliveryResponse.class,
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
    return execute(new DefaultHisProcurementParameters("ZJ9659", inputData), HisProcurementReturnApplyResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 获取退货订单信息
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 响应结果不为空
   */
  default HisProcurementReturnListResponse listReturns(HisProcurementReturnListInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9660", inputData), HisProcurementReturnListResponse.class,
            getHisProcurementConfig().getServiceUri());
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
    return execute(new DefaultHisProcurementParameters("ZJ9666", inputData), HisProcurementStatementCreateResponse.class,
            getHisProcurementConfig().getServiceUri());
  }

  /**
   * 添加结算单明细
   *
   * @param inputData 请求参数数据部分不能为空
   * @return 响应结果不为空
   * @throws HisProcurementClientException 执行请求或者执行业务发生异常
   */
  default HisProcurementStatementDetailAddResponse addStatementDetails(HisProcurementStatementDetailAddInputData inputData)
          throws HisProcurementClientException {
    return execute(new DefaultHisProcurementParameters("ZJ9667", inputData), HisProcurementStatementDetailAddResponse.class,
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
    return execute(new DefaultHisProcurementParameters("ZJ9668", inputData), HisProcurementStatementSubmitResponse.class,
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
    return execute(new DefaultHisProcurementParameters("ZJ9669", inputData), HisProcurementStatementExamineResponse.class,
            getHisProcurementConfig().getServiceUri());
  }
}
