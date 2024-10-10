package work.gaigeshen.tripartite.pay.wechat.notify;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentProcessor;
import work.gaigeshen.tripartite.core.notify.NotifyContentProcessingException;
import work.gaigeshen.tripartite.core.util.json.JsonUtils;

import java.util.Objects;

/**
 * 抽象微信支付异步通知数据体处理器
 *
 * @author gaigeshen
 */
public abstract class AbstractWechatNotifyContentProcessor extends AbstractNotifyContentProcessor<WechatNotifyBody> {

    private static final Logger log = LoggerFactory.getLogger(AbstractWechatNotifyContentProcessor.class);

    @Override
    protected boolean supports(WechatNotifyBody content) {
        return true;
    }

    @Override
    protected void processInternal(WechatNotifyBody content, ProcessorChain<WechatNotifyBody> chain) throws NotifyContentProcessingException {
        String resource = content.getResource();
        if (StringUtils.isBlank(resource)) {
            log.warn("<<<< Resource content is blank: {}", content);
            return;
        }
        log.info("<<<< Event: {}, Resource: {}", content.getEventType(), resource);
        switch (content.getEventType()) {
            case WechatNotifyBody.EventType.TRANSACTION_SUCCESS:
                WechatNotifyBody.TransactionSuccessResource transactionSuccessResource = JsonUtils.decodeObject(resource, WechatNotifyBody.TransactionSuccessResource.class);
                if (Objects.nonNull(transactionSuccessResource)) {
                    handleTransactionSuccessResource(transactionSuccessResource);
                }
                break;
            case WechatNotifyBody.EventType.REFUND_SUCCESS:
            case WechatNotifyBody.EventType.REFUND_CLOSED:
            case WechatNotifyBody.EventType.REFUND_ABNORMAL:
                WechatNotifyBody.RefundResource refundResource = JsonUtils.decodeObject(resource, WechatNotifyBody.RefundResource.class);
                if (Objects.nonNull(refundResource)) {
                    handleRefundResource(refundResource);
                }
                break;
            default:
                log.warn("<<<< Cannot process event '{}' with resource '{}'", content.getEventType(), resource);
                break;
        }
    }

    /**
     * 处理支付成功的资源数据
     *
     * @param transactionSuccessResource 支付成功的资源数据
     * @throws NotifyContentProcessingException 处理失败
     */
    protected abstract void handleTransactionSuccessResource(WechatNotifyBody.TransactionSuccessResource transactionSuccessResource) throws NotifyContentProcessingException;

    /**
     * 处理退款的资源数据
     *
     * @param refundResource 退款的资源数据
     * @throws NotifyContentProcessingException 处理失败
     */
    protected abstract void handleRefundResource(WechatNotifyBody.RefundResource refundResource) throws NotifyContentProcessingException;
}
