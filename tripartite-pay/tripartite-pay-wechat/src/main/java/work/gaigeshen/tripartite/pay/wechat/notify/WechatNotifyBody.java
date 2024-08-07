package work.gaigeshen.tripartite.pay.wechat.notify;

import lombok.Getter;
import work.gaigeshen.tripartite.core.notify.AbstractNotifyBody;

import java.util.Objects;

/**
 * 微信支付异步通知数据体
 *
 * @author gaigeshen
 */
@Getter
public class WechatNotifyBody extends AbstractNotifyBody {
    /**
     * 应用编号
     */
    private final String appId;
    /**
     * 商户编号
     */
    private final String merchantId;
    /**
     * 通知类型
     */
    private String eventType;
    /**
     * 通知资源
     */
    private String resource;

    /**
     * 创建微信支付异步通知数据体
     *
     * @param body       数据体不能为空
     * @param appId      应用编号不能为空
     * @param merchantId 商户号不能为空
     */
    public WechatNotifyBody(byte[] body, String appId, String merchantId) {
        super(body);
        if (Objects.isNull(appId)) {
            throw new IllegalArgumentException("appId cannot be null");
        }
        if (Objects.isNull(merchantId)) {
            throw new IllegalArgumentException("merchantId cannot be null");
        }
        this.appId = appId;
        this.merchantId = merchantId;
    }

    void setEventType(String eventType) {
        this.eventType = eventType;
    }

    void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * 通知类型
     *
     * @author gaigeshen
     */
    public static class EventType {
        /**
         * 支付成功
         */
        public static final String TRANSACTION_SUCCESS = "TRANSACTION.SUCCESS";
        /**
         * 退款成功
         */
        public static final String REFUND_SUCCESS = "REFUND.SUCCESS";
        /**
         * 退款异常
         */
        public static final String REFUND_ABNORMAL = "REFUND.ABNORMAL";
        /**
         * 退款关闭
         */
        public static final String REFUND_CLOSED = "REFUND.CLOSED";
    }

    /**
     * 支付成功的通知资源
     *
     * @author gaigeshen
     */
    public static class TransactionSuccessResource {

        /**
         * 支付成功
         */
        public static final String TRADE_STATE_SUCCESS = "SUCCESS";

        /**
         * 商户系统内部订单号
         */
        public String out_trade_no;

        /**
         * 微信支付订单号
         */
        public String transaction_id;

        /**
         * 交易类型
         */
        public String trade_type;

        /**
         * 交易状态
         */
        public String trade_state;

        /**
         * 交易状态描述
         */
        public String trade_state_desc;

        /**
         * 银行类型
         */
        public String bank_type;

        /**
         * 附加数据
         */
        public String attach;

        /**
         * 支付完成时间
         */
        public String success_time;

        /**
         * 支付者信息
         */
        public Payer payer;

        /**
         * 订单金额信息
         */
        public Amount amount;

        /**
         * 支付者信息
         */
        public static class Payer {

            /**
             * 支付者用户标识
             */
            public String openid;
        }

        /**
         * 订单金额信息
         */
        public static class Amount {

            /**
             * 订单总金额
             */
            public Integer total;

            /**
             * 用户支付金额
             */
            public Integer payer_total;

            /**
             * 订单币种
             */
            public String currency;

            /**
             * 用户支付币种
             */
            public String payer_currency;
        }
    }

    /**
     * 退款的通知资源
     */
    public static class RefundResource {

        /**
         * 退款成功
         */
        public static final String REFUND_STATUS_SUCCESS = "SUCCESS";

        /**
         * 退款关闭
         */
        public static final String REFUND_STATUS_CLOSED = "CLOSED";

        /**
         * 退款异常
         */
        public static final String REFUND_STATUS_ABNORMAL = "ABNORMAL";

        /**
         * 商户系统内部订单号
         */
        public String out_trade_no;

        /**
         * 微信支付订单号
         */
        public String transaction_id;

        /**
         * 商户退款单号
         */
        public String out_refund_no;

        /**
         * 微信退款单号
         */
        public String refund_id;

        /**
         * 退款状态
         */
        public String refund_status;

        /**
         * 退款成功时间
         */
        public String success_time;

        /**
         * 用户账号
         */
        public String user_received_account;

        /**
         * 金额信息
         */
        public Amount amount;

        /**
         * 金额信息
         */
        public static class Amount {

            /**
             * 订单总金额
             */
            public Integer total;

            /**
             * 退款金额
             */
            public Integer refund;

            /**
             * 用户实际支付金额
             */
            public Integer payer_total;

            /**
             * 退款给用户的金额
             */
            public Integer payer_refund;
        }
    }
}
