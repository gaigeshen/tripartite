package work.gaigeshen.triparttite.pay.wechat.response.basic;

import work.gaigeshen.triparttite.pay.wechat.response.WechatResponse;

/**
 *
 * @author gaigeshen
 */
public class WechatOrderRefundResponse implements WechatResponse {

  public String refund_id;

  public String out_refund_no;

  public String transaction_id;

  public String out_trade_no;

  public String channel;

  public String user_received_account;

  public String success_time;

  public String create_time;

  public String status;

  public String funds_account;

  public Amount amount;

  public PromotionDetail[] promotion_detail;

  public static class Amount {

    public Integer total;

    public Integer refund;

    public From[] from;

    public Integer payer_total;

    public Integer payer_refund;

    public Integer settlement_refund;

    public Integer settlement_total;

    public Integer discount_refund;

    public String currency;
  }

  public static class From {

    public String account;

    public Integer amount;
  }

  public static class PromotionDetail {

    public String promotion_id;

    public String scope;

    public String type;

    public Integer amount;

    public Integer refund_amount;

    public GoodsDetail[] goods_detail;
  }

  public static class GoodsDetail {

    public String merchant_goods_id;

    public String goods_name;

    public Integer unit_price;

    public Integer refund_amount;

    public Integer refund_quantity;
  }
}
