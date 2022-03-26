package work.gaigeshen.tripartite.pay.alipay.notify;

import work.gaigeshen.tripartite.core.notify.AbstractNotifyParameters;

/**
 * 支付宝异步通知参数
 *
 * @author gaigeshen
 */
public class AlipayNotifyParameters extends AbstractNotifyParameters {
  /**
   * 通知时间
   */
  public static final String PARAMETER_NOTIFY_TIME = "notify_time";
  /**
   * 通知类型
   */
  public static final String PARAMETER_NOTIFY_TYPE = "notify_type";
  /**
   * 通知编号
   */
  public static final String PARAMETER_NOTIFY_ID = "notify_id";
  /**
   * 签名
   */
  public static final String PARAMETER_SIGN = "sign";
  /**
   * 签名类型
   */
  public static final String PARAMETER_SIGN_TYPE = "sign_type";
  /**
   * 应用编号
   */
  public static final String PARAMETER_APP_ID = "app_id";
  /**
   * 支付宝交易号
   */
  public static final String PARAMETER_TRADE_NO = "trade_no";
  /**
   * 商户订单号
   */
  public static final String PARAMETER_OUT_TRADE_NO = "out_trade_no";
  /**
   * 交易状态
   */
  public static final String PARAMETER_TRADE_STATUS = "trade_status";
  /**
   * 订单金额
   */
  public static final String PARAMETER_TOTAL_AMOUNT = "total_amount";
  /**
   * 实收金额
   */
  public static final String PARAMETER_RECEIPT_AMOUNT = "receipt_amount";
  /**
   * 开票金额
   */
  public static final String PARAMETER_INVOICE_AMOUNT = "invoice_amount";
  /**
   * 总退款金额
   */
  public static final String PARAMETER_REFUND_FEE = "refund_fee";
  /**
   * 买家支付宝用户号
   */
  public static final String PARAMETER_BUYER_ID = "buyer_id";
  /**
   * 买家支付宝账号
   */
  public static final String PARAMETER_BUYER_LOGON_ID = "buyer_logon_id";
  /**
   * 卖家支付宝用户号
   */
  public static final String PARAMETER_SELLER_ID = "seller_id";
  /**
   * 卖家支付宝账号
   */
  public static final String PARAMETER_SELLER_EMAIL = "seller_email";
  /**
   * 订单标题
   */
  public static final String PARAMETER_SUBJECT = "subject";
  /**
   * 商品描述
   */
  public static final String PARAMETER_BODY = "body";
  /**
   * 交易创建时间
   */
  public static final String PARAMETER_GMT_CREATE = "gmt_create";
  /**
   * 交易付款时间
   */
  public static final String PARAMETER_GMT_PAYMENT = "gmt_payment";
  /**
   * 交易退款时间
   */
  public static final String PARAMETER_GMT_REFUND = "gmt_refund";
  /**
   * 交易结束时间
   */
  public static final String PARAMETER_GMT_CLOSE = "gmt_close";

  /**
   * 通知类型
   *
   * @author gaigeshen
   */
  public static class NotifyType {
    /**
     * 交易状态同步
     */
    public static final String TRADE_STATUS_SYNC = "trade_status_sync";
  }

  /**
   * 交易状态
   *
   * @author gaigeshen
   */
  public static class TradeStatus {
    /**
     * 等待买家付款
     */
    public static final String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
    /**
     * 未付款交易超时关闭或者支付完成后全额退款
     */
    public static final String TRADE_CLOSED = "TRADE_CLOSED";
    /**
     * 交易支付成功
     */
    public static final String TRADE_SUCCESS = "TRADE_SUCCESS";
    /**
     * 交易结束不可退款
     */
    public static final String TRADE_FINISHED = "TRADE_FINISHED";
  }
}
