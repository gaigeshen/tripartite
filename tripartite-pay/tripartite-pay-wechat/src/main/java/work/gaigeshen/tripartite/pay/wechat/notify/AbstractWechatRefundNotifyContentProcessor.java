package work.gaigeshen.tripartite.pay.wechat.notify;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentProcessor;
import work.gaigeshen.tripartite.core.notify.NotifyContentProcessingException;
import work.gaigeshen.tripartite.core.util.json.JsonCodec;
import work.gaigeshen.tripartite.pay.wechat.notify.WechatNotifyBody.RefundResource;

import java.util.Objects;

/**
 * 抽象的微信退款异步通知数据处理器
 *
 * @author gaigeshen
 */
public abstract class AbstractWechatRefundNotifyContentProcessor extends AbstractNotifyContentProcessor<WechatNotifyBody> {

    private static final Logger log = LoggerFactory.getLogger(AbstractWechatRefundNotifyContentProcessor.class);

    @Override
    protected final boolean supports(WechatNotifyBody content) {
        return StringUtils.equalsIgnoreCase(content.getEventType(), WechatNotifyBody.EventType.REFUND_SUCCESS)
                || StringUtils.equalsIgnoreCase(content.getEventType(), WechatNotifyBody.EventType.REFUND_CLOSED)
                || StringUtils.equalsIgnoreCase(content.getEventType(), WechatNotifyBody.EventType.REFUND_ABNORMAL);
    }

    @Override
    protected final void processInternal(WechatNotifyBody content, ProcessorChain<WechatNotifyBody> chain) throws NotifyContentProcessingException {
        String resource = content.getResource();
        if (StringUtils.isNotBlank(resource)) {
            log.info("<<<< Refund: {}", resource);
            RefundResource refundResource = JsonCodec.instance().decodeObject(resource, RefundResource.class);
            if (Objects.nonNull(refundResource)) {
                handleRefundResource(refundResource);
            }
        }
    }

    /**
     * 处理退款的资源数据
     *
     * @param refundResource 退款的资源数据
     */
    protected abstract void handleRefundResource(RefundResource refundResource);
}
