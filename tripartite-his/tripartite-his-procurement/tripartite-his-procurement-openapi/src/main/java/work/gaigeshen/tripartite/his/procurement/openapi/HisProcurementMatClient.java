package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.DefaultHisProcurementParameters;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat.*;
import work.gaigeshen.tripartite.his.procurement.openapi.response.mat.*;

/**
 * 浙江省两定平台客户端，此客户端只针对耗材类型
 *
 * @author gaigeshen
 */
public interface HisProcurementMatClient extends HisProcurementBasicClient {

    /**
     * 勾选本院目录
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws HisProcurementClientException 执行请求或者执行业务发生异常
     */
    default HisProcurementDirectoryAddResponse addDirectories(HisProcurementDirectoryAddInputData inputData)
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
    default HisProcurementDirectoryListResponse listDirectories(HisProcurementDirectoryListInputData inputData)
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
    default HisProcurementDirectoryUsedListResponse listUsedDirectories(HisProcurementDirectoryUsedListInputData inputData)
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
     * 添加采购订单明细
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws HisProcurementClientException 执行请求或者执行业务发生异常
     */
    default HisProcurementPurchaseOrderDetailAddResponse addPurchaseOrderDetails(HisProcurementPurchaseOrderDetailAddInputData inputData)
            throws HisProcurementClientException {
        return execute(new DefaultHisProcurementParameters("ZJ9705", inputData), HisProcurementPurchaseOrderDetailAddResponse.class,
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
     * 获取订单明细
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws HisProcurementClientException 执行请求或者执行业务发生异常
     */
    default HisProcurementOrderListResponse listOrders(HisProcurementOrderListInputData inputData)
            throws HisProcurementClientException {
        return execute(new DefaultHisProcurementParameters("ZJ9708", inputData), HisProcurementOrderListResponse.class,
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
    default HisProcurementReturnListResponse listReturns(HisProcurementReturnListInputData inputData)
            throws HisProcurementClientException {
        return execute(new DefaultHisProcurementParameters("ZJ9711", inputData), HisProcurementReturnListResponse.class,
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
    default HisProcurementStatementDetailAddResponse addStatementDetails(HisProcurementStatementDetailAddInputData inputData)
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

}
