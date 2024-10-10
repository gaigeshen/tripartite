package work.gaigeshen.tripartite.ding.openapi.notify.event;

import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentProcessor;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.core.notify.NotifyContentProcessingException;
import work.gaigeshen.tripartite.core.util.json.JsonUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * 用于钉钉事件推送的处理
 *
 * @author gaigeshen
 */
public abstract class DingEventNotifyContentProcessor extends AbstractNotifyContentProcessor<DefaultNotifyContent> {

    @Override
    protected final boolean supports(DefaultNotifyContent content) {
        return true;
    }

    @Override
    protected final void processInternal(DefaultNotifyContent content, ProcessorChain<DefaultNotifyContent> chain)
            throws NotifyContentProcessingException {
        Map<String, Object> eventContent = extractEventContent(content);
        if (supportsEventContent(eventContent)) {
            processEventContent(eventContent, content, chain);
        } else {
            chain.process(content);
        }
    }

    /**
     * 检查事件内容的事件类型
     *
     * @param eventContent 事件内容
     * @param eventType 期望的事件类型
     * @return 返回是否为期望的事件类型
     */
    protected boolean checkEventType(Map<String, Object> eventContent, String eventType) {
        return Objects.equals(eventContent.get("EventType"), eventType);
    }

    /**
     * 此方法用于从通知数据中获取事件内容，需要配合钉钉异步通知数据接收器使用，否则获取不到事件内容
     *
     * @param content 通知数据
     * @return 事件内容
     */
    protected Map<String, Object> extractEventContent(DefaultNotifyContent content) {
        String decrypted = (String) content.getValue("decrypted");
        if (Objects.isNull(decrypted)) {
            return Collections.emptyMap();
        }
        return JsonUtils.decodeObject(decrypted);
    }

    /**
     * 子类需要实现此方法，确定此处理器是否能处理该事件内容
     *
     * @param eventContent 事件内容
     * @return 返回是否能处理该事件内容，只有能处理该事件内容的时候才会继续调用处理方法
     * @see #processEventContent(Map, DefaultNotifyContent, ProcessorChain)
     */
    protected abstract boolean supportsEventContent(Map<String, Object> eventContent);

    /**
     * 处理事件内容的方法
     *
     * @param eventContent 事件内容
     * @param content 原始的通知数据
     * @param chain 通知数据处理器链不为空，用于决定是否继续处理
     */
    protected abstract void processEventContent(Map<String, Object> eventContent, DefaultNotifyContent content, ProcessorChain<DefaultNotifyContent> chain);
}
