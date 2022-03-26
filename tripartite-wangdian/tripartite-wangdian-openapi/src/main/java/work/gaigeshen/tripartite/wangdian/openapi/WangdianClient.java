package work.gaigeshen.tripartite.wangdian.openapi;

import work.gaigeshen.tripartite.core.response.consumer.ResponseConsumer;
import work.gaigeshen.tripartite.core.response.converter.ResponseConverter;
import work.gaigeshen.tripartite.wangdian.openapi.config.WangdianConfig;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParameters;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.goods.GoodsPushParameters;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.goods.GoodsQueryParameters;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.purchase.*;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.refund.RefundQueryParameters;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.refund.SalesRefundPushParameters;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.refund.StockInRefundPushParameters;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.refund.StockInRefundQueryParameters;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.stock.*;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.trade.LogisticsSyncAckParameters;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.trade.LogisticsSyncQueryParameters;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.trade.TradePushParameters;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.trade.TradeQueryParameters;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.warehouse.WarehouseQueryParameters;
import work.gaigeshen.tripartite.wangdian.openapi.response.WangdianResponse;
import work.gaigeshen.tripartite.wangdian.openapi.response.goods.GoodsPushResponse;
import work.gaigeshen.tripartite.wangdian.openapi.response.goods.GoodsQueryResponse;
import work.gaigeshen.tripartite.wangdian.openapi.response.purchase.*;
import work.gaigeshen.tripartite.wangdian.openapi.response.refund.RefundQueryResponse;
import work.gaigeshen.tripartite.wangdian.openapi.response.refund.SalesRefundPushResponse;
import work.gaigeshen.tripartite.wangdian.openapi.response.refund.StockInRefundPushResponse;
import work.gaigeshen.tripartite.wangdian.openapi.response.refund.StockInRefundQueryResponse;
import work.gaigeshen.tripartite.wangdian.openapi.response.stock.*;
import work.gaigeshen.tripartite.wangdian.openapi.response.trade.LogisticsSyncAckResponse;
import work.gaigeshen.tripartite.wangdian.openapi.response.trade.LogisticsSyncQueryResponse;
import work.gaigeshen.tripartite.wangdian.openapi.response.trade.TradePushResponse;
import work.gaigeshen.tripartite.wangdian.openapi.response.trade.TradeQueryResponse;
import work.gaigeshen.tripartite.wangdian.openapi.response.warehouse.WarehouseQueryResponse;

/**
 * 旺店通客户端
 *
 * @author gaigeshen
 */
public interface WangdianClient {

  /**
   * 返回旺店通客户端配置
   *
   * @return 旺店通客户端配置
   */
  WangdianConfig getWangdianConfig();

  /**
   * 创建货品档案
   *
   * @param parameters 创建货品档案请求参数不能为空
   * @return 创建货品档案响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=goods_push.php">接口文档</a>
   */
  GoodsPushResponse pushGoods(GoodsPushParameters parameters) throws WangdianClientException;

  /**
   * 查询货品档案
   *
   * @param parameters 查询货品档案请求参数不能为空
   * @return 查询货品档案响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=goods_query.php">接口文档</a>
   */
  GoodsQueryResponse queryGoods(GoodsQueryParameters parameters) throws WangdianClientException;

  /**
   * 创建原始订单
   *
   * @param parameters 创建原始订单请求参数不能为空
   * @return 创建原始订单响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=trade_push.php">接口文档</a>
   */
  TradePushResponse pushTrade(TradePushParameters parameters) throws WangdianClientException;

  /**
   * 查询原始订单
   *
   * @param parameters 查询原始订单请求参数不能为空
   * @return 查询原始订单响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=trade_query.php">接口文档</a>
   */
  TradeQueryResponse queryTrade(TradeQueryParameters parameters) throws WangdianClientException;

  /**
   * 创建原始退款单
   *
   * @param parameters 创建原始退款单请求参数不能为空
   * @return 创建原始退款单响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=sales_refund_push.php">接口文档</a>
   */
  SalesRefundPushResponse pushSalesRefund(SalesRefundPushParameters parameters) throws WangdianClientException;

  /**
   * 创建退货入库单
   *
   * @param parameters 创建退货入库单请求参数不能为空
   * @return 创建退货入库单响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=stockin_refund_push.php">接口文档</a>
   */
  StockInRefundPushResponse pushStockInRefund(StockInRefundPushParameters parameters) throws WangdianClientException;

  /**
   * 查询退换单
   *
   * @param parameters 查询退换单请求参数不能为空
   * @return 查询退换单响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=refund_query.php">接口文档</a>
   */
  RefundQueryResponse queryRefund(RefundQueryParameters parameters) throws WangdianClientException;

  /**
   * 查询退货入库单
   *
   * @param parameters 查询退货入库单请求参数不能为空
   * @return 查询退货入库单响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=stockin_order_query_refund.php">接口文档</a>
   */
  StockInRefundQueryResponse queryStockInRefund(StockInRefundQueryParameters parameters) throws WangdianClientException;

  /**
   * 物流同步回写
   *
   * @param parameters 物流同步回写请求参数不能为空
   * @return 物流同步回写响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=logistics_sync_ack.php">接口文档</a>
   */
  LogisticsSyncAckResponse ackLogisticsSync(LogisticsSyncAckParameters parameters) throws WangdianClientException;

  /**
   * 查询物流同步
   *
   * @param parameters 查询物流同步请求参数不能为空
   * @return 查询物流同步响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=logistics_sync_query.php">接口文档</a>
   */
  LogisticsSyncQueryResponse queryLogisticsSync(LogisticsSyncQueryParameters parameters) throws WangdianClientException;

  /**
   * 查询仓库
   *
   * @param parameters 查询仓库请求参数不能为空
   * @return 查询仓库响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=warehouse_query.php">接口文档</a>
   */
  WarehouseQueryResponse queryWarehouses(WarehouseQueryParameters parameters) throws WangdianClientException;

  /**
   * 查询供应商
   *
   * @param parameters 查询供应商请求参数不能为空
   * @return 查询供应商响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=purchase_provider_query.php">接口文档</a>
   */
  PurchaseProviderQueryResponse queryPurchaseProviders(PurchaseProviderQueryParameters parameters) throws WangdianClientException;

  /**
   * 创建供应商
   *
   * @param parameters 创建供应商请求参数不能为空
   * @return 创建供应商响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=purchase_provider_create.php">接口文档</a>
   */
  PurchaseProviderCreateResponse createPurchaseProvider(PurchaseProviderCreateParameters parameters) throws WangdianClientException;

  /**
   * 取消采购单
   *
   * @param parameters 取消采购单请求参数不能为空
   * @return 取消采购单响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=purchase_order_end.php">接口文档</a>
   */
  PurchaseOrderEndResponse endPurchaseOrder(PurchaseOrderEndParameters parameters) throws WangdianClientException;

  /**
   * 创建采购单
   *
   * @param parameters 创建采购单请求参数不能为空
   * @return 创建采购单响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=purchase_order_push.php">接口文档</a>
   */
  PurchaseOrderPushResponse pushPurchaseOrder(PurchaseOrderPushParameters parameters) throws WangdianClientException;

  /**
   * 查询采购单
   *
   * @param parameters 查询采购单请求参数不能为空
   * @return 查询采购单响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=purchase_order_query.php">接口文档</a>
   */
  PurchaseOrderQueryResponse queryPurchaseOrder(PurchaseOrderQueryParameters parameters) throws WangdianClientException;

  /**
   * 创建采购入库单
   *
   * @param parameters 创建采购入库单请求参数不能为空
   * @return 创建采购入库单响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=stockin_purchase_push.php">接口文档</a>
   */
  PurchaseStockInOrderPushResponse pushPurchaseStockInOrder(PurchaseStockInOrderPushParameters parameters) throws WangdianClientException;

  /**
   * 查询采购入库单
   *
   * @param parameters 查询采购入库单请求参数不能为空
   * @return 查询采购入库单响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=stockin_order_query_purchase.php">接口文档</a>
   */
  PurchaseStockInOrderQueryResponse queryPurchaseStockInOrder(PurchaseStockInOrderQueryParameters parameters) throws WangdianClientException;

  /**
   * 创建采购退货单
   *
   * @param parameters 创建采购退货单请求参数不能为空
   * @return 创建采购退货单响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=purchase_return_push.php">接口文档</a>
   */
  PurchaseReturnPushResponse pushPurchaseReturn(PurchaseReturnPushParameters parameters) throws WangdianClientException;

  /**
   * 查询采购退货单
   *
   * @param parameters 查询采购退货单请求参数不能为空
   * @return 查询采购退货单响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=purchase_return_query.php">接口文档</a>
   */
  PurchaseReturnQueryResponse queryPurchaseReturn(PurchaseReturnQueryParameters parameters) throws WangdianClientException;

  /**
   * 创建采购退货出库单
   *
   * @param parameters 创建采购退货出库单请求参数不能为空
   * @return 创建采购退货出库单响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=purchase_return_order_push.php">接口文档</a>
   */
  PurchaseReturnOrderPushResponse pushPurchaseReturnOrder(PurchaseReturnOrderPushParameters parameters) throws WangdianClientException;

  /**
   * 查询采购退货出库单
   *
   * @param parameters 查询采购退货出库单请求参数不能为空
   * @return 查询采购退货出库单响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=stockout_order_query_return.php">接口文档</a>
   */
  PurchaseReturnOrderQueryResponse queryPurchaseReturnOrder(PurchaseReturnOrderQueryParameters parameters) throws WangdianClientException;

  /**
   * 取消采购退货单
   *
   * @param parameters 取消采购退货单请求参数不能为空
   * @return 取消采购退货单响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=purchase_return_end.php">接口文档</a>
   */
  PurchaseReturnEndResponse endPurchaseReturn(PurchaseReturnEndParameters parameters) throws WangdianClientException;

  /**
   * 库存同步回写
   *
   * @param parameters 库存同步回写请求参数不能为空
   * @return 库存同步回写响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=api_goods_stock_change_ack.php">接口文档</a>
   */
  StockChangeAckResponse ackStockChange(StockChangeAckParameters parameters) throws WangdianClientException;

  /**
   * 查询库存同步
   *
   * @param parameters 查询库存同步请求参数不能为空
   * @return 查询库存同步响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=api_goods_stock_change_query.php">接口文档</a>
   */
  StockChangeQueryResponse queryStockChange(StockChangeQueryParameters parameters) throws WangdianClientException;

  /**
   * 增量查询库存
   *
   * @param parameters 增量查询库存请求参数不能为空
   * @return 增量查询库存响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=stock_query.php">接口文档</a>
   */
  StockQueryResponse queryStock(StockQueryParameters parameters) throws WangdianClientException;

  /**
   * 创建调拨单
   *
   * @param parameters 创建调拨单请求参数不能为空
   * @return 创建调拨单响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=stock_transfer_push.php">接口文档</a>
   */
  StockTransferPushResponse pushStockTransfer(StockTransferPushParameters parameters) throws WangdianClientException;

  /**
   * 查询调拨单
   *
   * @param parameters 查询调拨单请求参数不能为空
   * @return 查询调拨单响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=stock_transfer_query.php">接口文档</a>
   */
  StockTransferQueryResponse queryStockTransfer(StockTransferQueryParameters parameters) throws WangdianClientException;

  /**
   * 此方法接受任何请求参数，然后执行请求并返回对应的响应结果
   *
   * @param parameters 请求参数不能为空
   * @param responseClass 响应结果类型不能为空
   * @param uri 该请求对应的服务器地址不能为空
   * @param <R> 表示响应结果类型
   * @return 响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   */
  <R extends WangdianResponse> R execute(WangdianParameters parameters, Class<R> responseClass, String uri) throws WangdianClientException;

  /**
   * 此方法接受任何请求参数，然后执行请求并返回对应的响应结果，可以指定响应结果数据转换器
   *
   * @param parameters 请求参数不能为空
   * @param converter 响应结果数据转换器不能为空
   * @param uri 该请求对应的服务器地址不能为空
   * @param <R> 表示响应结果类型
   * @return 响应结果不会为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   */
  <R extends WangdianResponse> R execute(WangdianParameters parameters, ResponseConverter<R> converter, String uri) throws WangdianClientException;

  /**
   * 此方法接受任何请求参数，然后执行请求并回调响应结果数据消费器
   *
   * @param parameters 请求参数不能为空
   * @param consumer 响应结果数据消费器不能为空
   * @param uri 该请求对应的服务器地址不能为空
   * @throws WangdianClientException 执行请求或者执行业务发生异常
   */
  void execute(WangdianParameters parameters, ResponseConsumer consumer, String uri) throws WangdianClientException;

}
