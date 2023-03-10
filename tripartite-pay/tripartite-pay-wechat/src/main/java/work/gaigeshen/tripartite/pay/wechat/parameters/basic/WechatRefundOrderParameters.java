package work.gaigeshen.tripartite.pay.wechat.parameters.basic;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.pay.wechat.parameters.WechatParameters;
import work.gaigeshen.tripartite.pay.wechat.parameters.WechatParametersCustomizer;

/**
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class,
        customizer = WechatParametersCustomizer.class
)
public class WechatRefundOrderParameters implements WechatParameters {

    public String transaction_id;

    public String out_trade_no;

    public String out_refund_no;

    public String reason;

    public String funds_account;

    public Amount amount;

    public GoodsDetail[] goods_detail;

    public static class Amount {

        public Integer refund;

        public From[] from;

        public Integer total;

        public String currency;
    }

    public static class From {

        public String account;

        public Integer amount;
    }

    public static class GoodsDetail {

        public String merchant_goods_id;

        public String goods_name;

        public Integer unit_price;

        public Integer refund_amount;

        public Integer refund_quantity;
    }
}
