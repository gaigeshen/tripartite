package work.gaigeshen.tripartite.wangdian.openapi;

import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebException;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;
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
import work.gaigeshen.tripartite.wangdian.openapi.response.AbstractWangdianResponse;
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

import java.util.Objects;

/**
 * @author gaigeshen
 */
public class DefaultWangdianClient implements WangdianClient {

  private static final String GOODS_PUSH_URI = "/openapi2/goods_push.php";

  private static final String GOODS_QUERY_URI = "/openapi2/goods_query.php";

  private static final String TRADE_PUSH_URI = "/openapi2/trade_push.php";

  private static final String TRADE_QUERY_URI = "/openapi2/trade_query.php";

  private static final String SALES_REFUND_PUSH_URI = "/openapi2/sales_refund_push.php";

  private static final String STOCK_IN_REFUND_PUSH_URI = "/openapi2/stockin_refund_push.php";

  private static final String REFUND_QUERY_URI = "/openapi2/refund_query.php";

  private static final String STOCK_IN_REFUND_QUERY_URI = "/openapi2/stockin_order_query_refund.php";

  private static final String LOGISTICS_SYNC_ACK_URI = "/openapi2/logistics_sync_ack.php";

  private static final String LOGISTICS_SYNC_QUERY_URI = "/openapi2/logistics_sync_query.php";

  private static final String WAREHOUSE_QUERY_URI = "/openapi2/warehouse_query.php";

  private static final String PURCHASE_PROVIDER_QUERY_URI = "/openapi2/purchase_provider_query.php";

  private static final String PURCHASE_PROVIDER_CREATE_URI = "/openapi2/purchase_provider_create.php";

  private static final String PURCHASE_ORDER_END_URI = "/openapi2/purchase_order_end.php";

  private static final String PURCHASE_ORDER_PUSH_URI = "/openapi2/purchase_order_push.php";

  private static final String PURCHASE_ORDER_QUERY_URI = "/openapi2/purchase_order_query.php";

  private static final String PURCHASE_STOCK_IN_ORDER_PUSH_URI = "/openapi2/stockin_purchase_push.php";

  private static final String PURCHASE_STOCK_IN_ORDER_QUERY_URI = "/openapi2/stockin_order_query_purchase.php";

  private static final String PURCHASE_RETURN_PUSH_URI = "/openapi2/purchase_return_push.php";

  private static final String PURCHASE_RETURN_QUERY_URI = "/openapi2/purchase_return_query.php";

  private static final String PURCHASE_RETURN_ORDER_PUSH_URI = "/openapi2/purchase_return_order_push.php";

  private static final String PURCHASE_RETURN_ORDER_QUERY_URI = "/openapi2/stockout_order_query_return.php";

  private static final String PURCHASE_RETURN_END_URI = "/openapi2/purchase_return_end.php";

  private static final String STOCK_CHANGE_ACK_URI = "/openapi2/api_goods_stock_change_ack.php";

  private static final String STOCK_CHANGE_QUERY_URI = "/openapi2/api_goods_stock_change_query.php";

  private static final String STOCK_QUERY_URI = "/openapi2/stock_query.php";

  private static final String STOCK_TRANSFER_PUSH_URI = "/openapi2/stock_transfer_push.php";

  private static final String STOCK_TRANSFER_QUERY_URI = "/openapi2/stock_transfer_query.php";

  private final WangdianConfig config;

  private final WebExecutor executor;


  protected DefaultWangdianClient(WangdianConfig config, WebExecutor executor) {
    if (Objects.isNull(config)) {
      throw new IllegalArgumentException("config cannot be null");
    }
    if (Objects.isNull(executor)) {
      throw new IllegalArgumentException("web executor cannot be null");
    }
    this.config = config;
    this.executor = executor;
  }

  public static DefaultWangdianClient create(WangdianConfig config, AbstractInterceptor... interceptors) {
    if (Objects.isNull(interceptors)) {
      throw new IllegalArgumentException("interceptors cannot be null");
    }
    RestTemplateWebExecutor executor = RestTemplateWebExecutor.create();
    executor.setInterceptors(interceptors);
    executor.setParametersConverter(new ParametersMetadataParametersConverter(config));
    return new DefaultWangdianClient(config, executor);
  }

  public static DefaultWangdianClient create(WangdianConfig config, WebExecutor executor) {
    return new DefaultWangdianClient(config, executor);
  }

  @Override
  public WangdianConfig getWangdianConfig() {
    return config;
  }

  @Override
  public GoodsPushResponse pushGoods(GoodsPushParameters parameters) throws WangdianClientException {
    return execute(parameters, GoodsPushResponse.class, GOODS_PUSH_URI);
  }

  @Override
  public GoodsQueryResponse queryGoods(GoodsQueryParameters parameters) throws WangdianClientException {
    return execute(parameters, GoodsQueryResponse.class, GOODS_QUERY_URI);
  }

  @Override
  public TradePushResponse pushTrade(TradePushParameters parameters) throws WangdianClientException {
    return execute(parameters, TradePushResponse.class, TRADE_PUSH_URI);
  }

  @Override
  public TradeQueryResponse queryTrade(TradeQueryParameters parameters) throws WangdianClientException {
    return execute(parameters, TradeQueryResponse.class, TRADE_QUERY_URI);
  }

  @Override
  public SalesRefundPushResponse pushSalesRefund(SalesRefundPushParameters parameters) throws WangdianClientException {
    return execute(parameters, SalesRefundPushResponse.class, SALES_REFUND_PUSH_URI);
  }

  @Override
  public StockInRefundPushResponse pushStockInRefund(StockInRefundPushParameters parameters) throws WangdianClientException {
    return execute(parameters, StockInRefundPushResponse.class, STOCK_IN_REFUND_PUSH_URI);
  }

  @Override
  public RefundQueryResponse queryRefund(RefundQueryParameters parameters) throws WangdianClientException {
    return execute(parameters, RefundQueryResponse.class, REFUND_QUERY_URI);
  }

  @Override
  public StockInRefundQueryResponse queryStockInRefund(StockInRefundQueryParameters parameters) throws WangdianClientException {
    return execute(parameters, StockInRefundQueryResponse.class, STOCK_IN_REFUND_QUERY_URI);
  }

  @Override
  public LogisticsSyncAckResponse ackLogisticsSync(LogisticsSyncAckParameters parameters) throws WangdianClientException {
    return execute(parameters, LogisticsSyncAckResponse.class, LOGISTICS_SYNC_ACK_URI);
  }

  @Override
  public LogisticsSyncQueryResponse queryLogisticsSync(LogisticsSyncQueryParameters parameters) throws WangdianClientException {
    return execute(parameters, LogisticsSyncQueryResponse.class, LOGISTICS_SYNC_QUERY_URI);
  }

  @Override
  public WarehouseQueryResponse queryWarehouses(WarehouseQueryParameters parameters) throws WangdianClientException {
    return execute(parameters, WarehouseQueryResponse.class, WAREHOUSE_QUERY_URI);
  }

  @Override
  public PurchaseProviderQueryResponse queryPurchaseProviders(PurchaseProviderQueryParameters parameters) throws WangdianClientException {
    return execute(parameters, PurchaseProviderQueryResponse.class, PURCHASE_PROVIDER_QUERY_URI);
  }

  @Override
  public PurchaseProviderCreateResponse createPurchaseProvider(PurchaseProviderCreateParameters parameters) throws WangdianClientException {
    return execute(parameters, PurchaseProviderCreateResponse.class, PURCHASE_PROVIDER_CREATE_URI);
  }

  @Override
  public PurchaseOrderEndResponse endPurchaseOrder(PurchaseOrderEndParameters parameters) throws WangdianClientException {
    return execute(parameters, PurchaseOrderEndResponse.class, PURCHASE_ORDER_END_URI);
  }

  @Override
  public PurchaseOrderPushResponse pushPurchaseOrder(PurchaseOrderPushParameters parameters) throws WangdianClientException {
    return execute(parameters, PurchaseOrderPushResponse.class, PURCHASE_ORDER_PUSH_URI);
  }

  @Override
  public PurchaseOrderQueryResponse queryPurchaseOrder(PurchaseOrderQueryParameters parameters) throws WangdianClientException {
    return execute(parameters, PurchaseOrderQueryResponse.class, PURCHASE_ORDER_QUERY_URI);
  }

  @Override
  public PurchaseStockInOrderPushResponse pushPurchaseStockInOrder(PurchaseStockInOrderPushParameters parameters) throws WangdianClientException {
    return execute(parameters, PurchaseStockInOrderPushResponse.class, PURCHASE_STOCK_IN_ORDER_PUSH_URI);
  }

  @Override
  public PurchaseStockInOrderQueryResponse queryPurchaseStockInOrder(PurchaseStockInOrderQueryParameters parameters) throws WangdianClientException {
    return execute(parameters, PurchaseStockInOrderQueryResponse.class, PURCHASE_STOCK_IN_ORDER_QUERY_URI);
  }

  @Override
  public PurchaseReturnPushResponse pushPurchaseReturn(PurchaseReturnPushParameters parameters) throws WangdianClientException {
    return execute(parameters, PurchaseReturnPushResponse.class, PURCHASE_RETURN_PUSH_URI);
  }

  @Override
  public PurchaseReturnQueryResponse queryPurchaseReturn(PurchaseReturnQueryParameters parameters) throws WangdianClientException {
    return execute(parameters, PurchaseReturnQueryResponse.class, PURCHASE_RETURN_QUERY_URI);
  }

  @Override
  public PurchaseReturnOrderPushResponse pushPurchaseReturnOrder(PurchaseReturnOrderPushParameters parameters) throws WangdianClientException {
    return execute(parameters, PurchaseReturnOrderPushResponse.class, PURCHASE_RETURN_ORDER_PUSH_URI);
  }

  @Override
  public PurchaseReturnOrderQueryResponse queryPurchaseReturnOrder(PurchaseReturnOrderQueryParameters parameters) throws WangdianClientException {
    return execute(parameters, PurchaseReturnOrderQueryResponse.class, PURCHASE_RETURN_ORDER_QUERY_URI);
  }

  @Override
  public PurchaseReturnEndResponse endPurchaseReturn(PurchaseReturnEndParameters parameters) throws WangdianClientException {
    return execute(parameters, PurchaseReturnEndResponse.class, PURCHASE_RETURN_END_URI);
  }

  @Override
  public StockChangeAckResponse ackStockChange(StockChangeAckParameters parameters) throws WangdianClientException {
    return execute(parameters, StockChangeAckResponse.class, STOCK_CHANGE_ACK_URI);
  }

  @Override
  public StockChangeQueryResponse queryStockChange(StockChangeQueryParameters parameters) throws WangdianClientException {
    return execute(parameters, StockChangeQueryResponse.class, STOCK_CHANGE_QUERY_URI);
  }

  @Override
  public StockQueryResponse queryStock(StockQueryParameters parameters) throws WangdianClientException {
    return execute(parameters, StockQueryResponse.class, STOCK_QUERY_URI);
  }

  @Override
  public StockTransferPushResponse pushStockTransfer(StockTransferPushParameters parameters) throws WangdianClientException {
    return execute(parameters, StockTransferPushResponse.class, STOCK_TRANSFER_PUSH_URI);
  }

  @Override
  public StockTransferQueryResponse queryStockTransfer(StockTransferQueryParameters parameters) throws WangdianClientException {
    return execute(parameters, StockTransferQueryResponse.class, STOCK_TRANSFER_QUERY_URI);
  }

  @Override
  public <R extends WangdianResponse> R execute(WangdianParameters parameters, Class<R> responseClass, String uri) throws WangdianClientException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("parameters cannot be null");
    }
    if (Objects.isNull(responseClass) || Objects.isNull(uri)) {
      throw new IllegalArgumentException("response class and uri cannot be null");
    }
    try {
      R response = executor.execute(config.getServerHost() + uri, parameters, responseClass);
      return validateResponse(response);
    } catch (WebException e) {
      throw new WangdianClientException(e.getMessage(), e);
    }
  }

  @Override
  public <R extends WangdianResponse> R execute(WangdianParameters parameters, ResponseConverter<R> converter, String uri) throws WangdianClientException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("parameters cannot be null");
    }
    if (Objects.isNull(converter) || Objects.isNull(uri)) {
      throw new IllegalArgumentException("response converter and uri cannot be null");
    }
    try {
      R response = executor.execute(config.getServerHost() + uri, parameters, converter);
      return validateResponse(response);
    } catch (WebException e) {
      throw new WangdianClientException(e.getMessage(), e);
    }
  }

  @Override
  public void execute(WangdianParameters parameters, ResponseConsumer consumer, String uri) throws WangdianClientException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("parameters cannot be null");
    }
    if (Objects.isNull(consumer) || Objects.isNull(uri)) {
      throw new IllegalArgumentException("response consumer and uri cannot be null");
    }
    try {
      executor.execute(config.getServerHost() + uri, parameters, consumer);
    } catch (WebException e) {
      throw new WangdianClientException(e.getMessage(), e);
    }
  }

  protected <R extends WangdianResponse> R validateResponse(R response) throws WangdianClientException {
    if (Objects.isNull(response)) {
      throw new WangdianClientException("could not validate null response");
    }
    if (response instanceof AbstractWangdianResponse) {
      AbstractWangdianResponse abstractResponse = (AbstractWangdianResponse) response;
      if (!Objects.equals(abstractResponse.code, 0)) {
        throw new WangdianClientException("[ " + abstractResponse.code + " ] - [ " + abstractResponse.message + " ]");
      }
    }
    return response;
  }
}
