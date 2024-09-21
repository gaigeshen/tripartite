package work.gaigeshen.tripartite.qyweixin.openapi.notify;

import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentProcessor;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.core.notify.NotifyContentProcessingException;
import work.gaigeshen.tripartite.core.util.xml.XmlCodec;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 用于企业微信回调通知推送的处理
 *
 * @author gaigeshen
 */
public abstract class QyWeixinMessageNotifyContentProcessor extends AbstractNotifyContentProcessor<DefaultNotifyContent> {

    @Override
    protected final boolean supports(DefaultNotifyContent content) {
        return true;
    }

    @Override
    protected final void processInternal(DefaultNotifyContent content, ProcessorChain<DefaultNotifyContent> chain)
            throws NotifyContentProcessingException {
        Map<?, ?> messageContent = extractMessageContent(content);
        if (supportsMessageContent(messageContent)) {
            processMessageContent(messageContent, content, chain);
        } else {
            chain.process(content);
        }
    }

    /**
     * 此方法用于从通知数据中获取回调通知内容，需要配合企业微信异步通知数据接收器使用，否则获取不到回调通知内容
     *
     * @param content 通知数据
     * @return 回调通知内容
     */
    protected Map<?, ?> extractMessageContent(DefaultNotifyContent content) throws NotifyContentProcessingException {
        String decrypted = (String) content.getValue("decrypted");
        if (Objects.isNull(decrypted)) {
            return Collections.emptyMap();
        }
        return XmlCodec.instance().decodeObject(decrypted, HashMap.class);
    }

    /**
     * 子类需要实现此方法，确定此处理器是否能处理该回调通知内容
     *
     * @param messageContent 回调通知内容
     * @return 返回是否能处理该回调通知内容，只有能处理该回调通知内容的时候才会继续调用处理方法
     * @see #processMessageContent(Map, DefaultNotifyContent, ProcessorChain)
     */
    protected abstract boolean supportsMessageContent(Map<?, ?> messageContent);

    /**
     * 处理回调通知内容的方法
     *
     * @param messageContent 回调通知内容
     * @param content 原始的通知数据
     * @param chain 通知数据处理器链不为空，用于决定是否继续处理
     */
    protected abstract void processMessageContent(Map<?, ?> messageContent, DefaultNotifyContent content, ProcessorChain<DefaultNotifyContent> chain);
}
