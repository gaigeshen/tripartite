package work.gaigeshen.tripartite.pay.wechat.response.basic;

import work.gaigeshen.tripartite.pay.wechat.response.WechatResponse;

/**
 * @author gaigeshen
 */
public class WechatQueryOrderResponse implements WechatResponse {

    public String out_trade_no;

    public String transaction_id;

    public String trade_type;

    public String trade_state;

    public String trade_state_desc;

    public String bank_type;

    public String attach;

    public String success_time;

    public Payer payer;

    public Amount amount;

    public SceneInfo scene_info;

    public PromotionDetail[] promotion_detail;

    public static class Payer {

        public String openid;
    }

    public static class Amount {

        public Integer total;

        public Integer payer_total;

        public String currency;

        public String payer_currency;
    }

    public static class SceneInfo {

        public String device_id;
    }

    public static class PromotionDetail {

        public String coupon_id;

        public String name;

        public String scope;

        public String type;

        public Integer amount;

        public String stock_id;

        public Integer merchant_contribute;

        public Integer other_contribute;

        public String currency;

        public GoodsDetail[] goods_detail;
    }

    public static class GoodsDetail {

        public String goods_id;

        public Integer quantity;

        public Integer unit_price;

        public Integer discount_amount;

        public String goods_remark;
    }
}
