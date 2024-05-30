package work.gaigeshen.tripartite.pay.wechat.notify;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentProcessor;
import work.gaigeshen.tripartite.core.notify.NotifyContentProcessingException;
import work.gaigeshen.tripartite.core.util.json.JsonCodec;
import work.gaigeshen.tripartite.pay.wechat.notify.WechatNotifyBody.TransactionSuccessResource;

import java.util.Objects;

/**
 * 抽象的微信支付成功异步通知数据处理器
 *
 * @author gaigeshen
 */
public abstract class AbstractWechatTransactionSuccessNotifyContentProcessor extends AbstractNotifyContentProcessor<WechatNotifyBody> {

    private static final Logger log = LoggerFactory.getLogger(AbstractWechatTransactionSuccessNotifyContentProcessor.class);

    @Override
    protected final boolean supports(WechatNotifyBody content) {
        return StringUtils.equalsIgnoreCase(content.getEventType(), WechatNotifyBody.EventType.TRANSACTION_SUCCESS);
    }

    @Override
    protected final void processInternal(WechatNotifyBody content, ProcessorChain<WechatNotifyBody> chain) throws NotifyContentProcessingException {
        String resource = content.getResource();
        if (StringUtils.isNotBlank(resource)) {
            log.info("<<<< Transaction Success: {}", resource);
            TransactionSuccessResource transactionSuccessResource = JsonCodec.instance().decodeObject(resource, TransactionSuccessResource.class);
            if (Objects.nonNull(transactionSuccessResource)) {
                handleTransactionSuccessResource(transactionSuccessResource);
            }
        }
    }

    /**
     * 处理支付成功的资源数据
     *
     * @param transactionSuccessResource 支付成功的资源数据
     */
    protected abstract void handleTransactionSuccessResource(TransactionSuccessResource transactionSuccessResource);
}
