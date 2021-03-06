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
 * ??????????????????
 *
 * @author gaigeshen
 */
public interface WangdianClient {

  /**
   * ??????????????????????????????
   *
   * @return ????????????????????????
   */
  WangdianConfig getWangdianConfig();

  /**
   * ??????????????????
   *
   * @param parameters ??????????????????????????????????????????
   * @return ??????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=goods_push.php">????????????</a>
   */
  GoodsPushResponse pushGoods(GoodsPushParameters parameters) throws WangdianClientException;

  /**
   * ??????????????????
   *
   * @param parameters ??????????????????????????????????????????
   * @return ??????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=goods_query.php">????????????</a>
   */
  GoodsQueryResponse queryGoods(GoodsQueryParameters parameters) throws WangdianClientException;

  /**
   * ??????????????????
   *
   * @param parameters ??????????????????????????????????????????
   * @return ??????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=trade_push.php">????????????</a>
   */
  TradePushResponse pushTrade(TradePushParameters parameters) throws WangdianClientException;

  /**
   * ??????????????????
   *
   * @param parameters ??????????????????????????????????????????
   * @return ??????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=trade_query.php">????????????</a>
   */
  TradeQueryResponse queryTrade(TradeQueryParameters parameters) throws WangdianClientException;

  /**
   * ?????????????????????
   *
   * @param parameters ?????????????????????????????????????????????
   * @return ?????????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=sales_refund_push.php">????????????</a>
   */
  SalesRefundPushResponse pushSalesRefund(SalesRefundPushParameters parameters) throws WangdianClientException;

  /**
   * ?????????????????????
   *
   * @param parameters ?????????????????????????????????????????????
   * @return ?????????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=stockin_refund_push.php">????????????</a>
   */
  StockInRefundPushResponse pushStockInRefund(StockInRefundPushParameters parameters) throws WangdianClientException;

  /**
   * ???????????????
   *
   * @param parameters ???????????????????????????????????????
   * @return ???????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=refund_query.php">????????????</a>
   */
  RefundQueryResponse queryRefund(RefundQueryParameters parameters) throws WangdianClientException;

  /**
   * ?????????????????????
   *
   * @param parameters ?????????????????????????????????????????????
   * @return ?????????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=stockin_order_query_refund.php">????????????</a>
   */
  StockInRefundQueryResponse queryStockInRefund(StockInRefundQueryParameters parameters) throws WangdianClientException;

  /**
   * ??????????????????
   *
   * @param parameters ??????????????????????????????????????????
   * @return ??????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=logistics_sync_ack.php">????????????</a>
   */
  LogisticsSyncAckResponse ackLogisticsSync(LogisticsSyncAckParameters parameters) throws WangdianClientException;

  /**
   * ??????????????????
   *
   * @param parameters ??????????????????????????????????????????
   * @return ??????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=logistics_sync_query.php">????????????</a>
   */
  LogisticsSyncQueryResponse queryLogisticsSync(LogisticsSyncQueryParameters parameters) throws WangdianClientException;

  /**
   * ????????????
   *
   * @param parameters ????????????????????????????????????
   * @return ????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=warehouse_query.php">????????????</a>
   */
  WarehouseQueryResponse queryWarehouses(WarehouseQueryParameters parameters) throws WangdianClientException;

  /**
   * ???????????????
   *
   * @param parameters ???????????????????????????????????????
   * @return ???????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=purchase_provider_query.php">????????????</a>
   */
  PurchaseProviderQueryResponse queryPurchaseProviders(PurchaseProviderQueryParameters parameters) throws WangdianClientException;

  /**
   * ???????????????
   *
   * @param parameters ???????????????????????????????????????
   * @return ???????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=purchase_provider_create.php">????????????</a>
   */
  PurchaseProviderCreateResponse createPurchaseProvider(PurchaseProviderCreateParameters parameters) throws WangdianClientException;

  /**
   * ???????????????
   *
   * @param parameters ???????????????????????????????????????
   * @return ???????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=purchase_order_end.php">????????????</a>
   */
  PurchaseOrderEndResponse endPurchaseOrder(PurchaseOrderEndParameters parameters) throws WangdianClientException;

  /**
   * ???????????????
   *
   * @param parameters ???????????????????????????????????????
   * @return ???????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=purchase_order_push.php">????????????</a>
   */
  PurchaseOrderPushResponse pushPurchaseOrder(PurchaseOrderPushParameters parameters) throws WangdianClientException;

  /**
   * ???????????????
   *
   * @param parameters ???????????????????????????????????????
   * @return ???????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=purchase_order_query.php">????????????</a>
   */
  PurchaseOrderQueryResponse queryPurchaseOrder(PurchaseOrderQueryParameters parameters) throws WangdianClientException;

  /**
   * ?????????????????????
   *
   * @param parameters ?????????????????????????????????????????????
   * @return ?????????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=stockin_purchase_push.php">????????????</a>
   */
  PurchaseStockInOrderPushResponse pushPurchaseStockInOrder(PurchaseStockInOrderPushParameters parameters) throws WangdianClientException;

  /**
   * ?????????????????????
   *
   * @param parameters ?????????????????????????????????????????????
   * @return ?????????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=stockin_order_query_purchase.php">????????????</a>
   */
  PurchaseStockInOrderQueryResponse queryPurchaseStockInOrder(PurchaseStockInOrderQueryParameters parameters) throws WangdianClientException;

  /**
   * ?????????????????????
   *
   * @param parameters ?????????????????????????????????????????????
   * @return ?????????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=purchase_return_push.php">????????????</a>
   */
  PurchaseReturnPushResponse pushPurchaseReturn(PurchaseReturnPushParameters parameters) throws WangdianClientException;

  /**
   * ?????????????????????
   *
   * @param parameters ?????????????????????????????????????????????
   * @return ?????????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=purchase_return_query.php">????????????</a>
   */
  PurchaseReturnQueryResponse queryPurchaseReturn(PurchaseReturnQueryParameters parameters) throws WangdianClientException;

  /**
   * ???????????????????????????
   *
   * @param parameters ???????????????????????????????????????????????????
   * @return ???????????????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=purchase_return_order_push.php">????????????</a>
   */
  PurchaseReturnOrderPushResponse pushPurchaseReturnOrder(PurchaseReturnOrderPushParameters parameters) throws WangdianClientException;

  /**
   * ???????????????????????????
   *
   * @param parameters ???????????????????????????????????????????????????
   * @return ???????????????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=stockout_order_query_return.php">????????????</a>
   */
  PurchaseReturnOrderQueryResponse queryPurchaseReturnOrder(PurchaseReturnOrderQueryParameters parameters) throws WangdianClientException;

  /**
   * ?????????????????????
   *
   * @param parameters ?????????????????????????????????????????????
   * @return ?????????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=purchase_return_end.php">????????????</a>
   */
  PurchaseReturnEndResponse endPurchaseReturn(PurchaseReturnEndParameters parameters) throws WangdianClientException;

  /**
   * ??????????????????
   *
   * @param parameters ??????????????????????????????????????????
   * @return ??????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=api_goods_stock_change_ack.php">????????????</a>
   */
  StockChangeAckResponse ackStockChange(StockChangeAckParameters parameters) throws WangdianClientException;

  /**
   * ??????????????????
   *
   * @param parameters ??????????????????????????????????????????
   * @return ??????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=api_goods_stock_change_query.php">????????????</a>
   */
  StockChangeQueryResponse queryStockChange(StockChangeQueryParameters parameters) throws WangdianClientException;

  /**
   * ??????????????????
   *
   * @param parameters ??????????????????????????????????????????
   * @return ??????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=stock_query.php">????????????</a>
   */
  StockQueryResponse queryStock(StockQueryParameters parameters) throws WangdianClientException;

  /**
   * ???????????????
   *
   * @param parameters ???????????????????????????????????????
   * @return ???????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=stock_transfer_push.php">????????????</a>
   */
  StockTransferPushResponse pushStockTransfer(StockTransferPushParameters parameters) throws WangdianClientException;

  /**
   * ???????????????
   *
   * @param parameters ???????????????????????????????????????
   * @return ???????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   * @see <a href="https://open.wangdian.cn/qyb/open/apidoc/doc?path=stock_transfer_query.php">????????????</a>
   */
  StockTransferQueryResponse queryStockTransfer(StockTransferQueryParameters parameters) throws WangdianClientException;

  /**
   * ????????????????????????????????????????????????????????????????????????????????????
   *
   * @param parameters ????????????????????????
   * @param responseClass ??????????????????????????????
   * @param uri ?????????????????????????????????????????????
   * @param <R> ????????????????????????
   * @return ????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   */
  <R extends WangdianResponse> R execute(WangdianParameters parameters, Class<R> responseClass, String uri) throws WangdianClientException;

  /**
   * ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
   *
   * @param parameters ????????????????????????
   * @param converter ???????????????????????????????????????
   * @param uri ?????????????????????????????????????????????
   * @param <R> ????????????????????????
   * @return ????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   */
  <R extends WangdianResponse> R execute(WangdianParameters parameters, ResponseConverter<R> converter, String uri) throws WangdianClientException;

  /**
   * ??????????????????????????????????????????????????????????????????????????????????????????
   *
   * @param parameters ????????????????????????
   * @param consumer ???????????????????????????????????????
   * @param uri ?????????????????????????????????????????????
   * @throws WangdianClientException ??????????????????????????????????????????
   */
  void execute(WangdianParameters parameters, ResponseConsumer consumer, String uri) throws WangdianClientException;

}
