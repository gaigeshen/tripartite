package work.gaigeshen.tripartite.pay.wechat.notify;

import work.gaigeshen.tripartite.core.notify.AbstractNotifyBody;

import java.util.Objects;

/**
 * 微信支付异步通知数据体
 *
 * @author gaigeshen
 */
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

    public String getAppId() {
        return appId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getResource() {
        return resource;
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
}
