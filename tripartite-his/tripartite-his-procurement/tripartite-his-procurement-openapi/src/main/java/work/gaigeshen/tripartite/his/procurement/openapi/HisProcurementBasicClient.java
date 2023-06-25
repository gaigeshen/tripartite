package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.DefaultHisProcurementParameters;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementParameters;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.basic.HisProcurementStorehouseListInputData;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.basic.HisProcurementStorehouseSaveInputData;
import work.gaigeshen.tripartite.his.procurement.openapi.response.HisProcurementResponse;
import work.gaigeshen.tripartite.his.procurement.openapi.response.basic.HisProcurementStorehouseListResponse;
import work.gaigeshen.tripartite.his.procurement.openapi.response.basic.HisProcurementStorehouseSaveResponse;

/**
 * 浙江省两定平台客户端
 *
 * @author gaigeshen
 */
public interface HisProcurementBasicClient {

    /**
     * 返回此客户端的配置
     *
     * @return 此客户端的配置
     */
    HisProcurementConfig getHisProcurementConfig();

    /**
     * 获取库房信息
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws HisProcurementClientException 执行请求或者执行业务发生异常
     */
    default HisProcurementStorehouseListResponse listStorehouses(HisProcurementStorehouseListInputData inputData)
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
     * @param parameters    请求参数不能为空
     * @param responseClass 响应结果类型不能为空
     * @param uri           该请求对应的服务器地址不能为空
     * @param <R>           表示响应结果类型
     * @return 响应结果不会为空
     * @throws HisProcurementClientException 执行请求或者执行业务发生异常
     */
    <R extends HisProcurementResponse> R execute(HisProcurementParameters parameters, Class<R> responseClass, String uri)
            throws HisProcurementClientException;

}
