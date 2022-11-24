package work.gaigeshen.tripartite.ding.openapi.notify;

import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentProcessor;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.core.notify.NotifyContentProcessingException;
import work.gaigeshen.tripartite.core.util.json.JsonCodec;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * 用于钉钉事件推送的处理
 *
 * @author gaigeshen
 */
public abstract class DingEventNotifyContentProcessor extends AbstractNotifyContentProcessor<DefaultNotifyContent> {

    /**
     * 测试回调事件
     */
    protected static final String EVENT_TYPE_CHECK_URL = "check_url";

    /**
     * 验证回调事件
     */
    protected static final String EVENT_TYPE_CHECK_CREATE_SUITE_URL = "check_create_suite_url";

    /**
     * 回调地址更新事件
     */
    protected static final String EVENT_TYPE_CHECK_UPDATE_SUITE_URL = "check_update_suite_url";

    /**
     * 高优先级数据例如激活应用等
     */
    protected static final String EVENT_TYPE_SYNC_HTTP_PUSH_HIGH = "SYNC_HTTP_PUSH_HIGH";

    /**
     * 普通优先级数据例如通讯录变更等
     */
    protected static final String EVENT_TYPE_SYNC_HTTP_PUSH_MEDIUM = "SYNC_HTTP_PUSH_MEDIUM";

    @Override
    protected final boolean supports(DefaultNotifyContent content) {
        return true;
    }

    @Override
    protected final void processInternal(DefaultNotifyContent content, ProcessorChain<DefaultNotifyContent> chain) throws NotifyContentProcessingException {
        Map<String, Object> eventContent = extractEventContent(content);
        if (supportsEventContent(eventContent)) {
            processEventContent(eventContent, chain);
        } else {
            chain.process(content);
        }
    }

    /**
     * 此方法用于从通知数据中获取事件内容，需要配合钉钉异步通知数据接收器使用，否则获取不到事件内容
     *
     * @param content 通知数据
     * @return 事件内容
     * @see DingNotifyContentReceiver
     */
    protected Map<String, Object> extractEventContent(DefaultNotifyContent content) {
        String decrypted = (String) content.getValue("decrypted");
        if (Objects.isNull(decrypted)) {
            return Collections.emptyMap();
        }
        return JsonCodec.instance().decodeObject(decrypted);
    }

    /**
     * 子类需要实现此方法，确定此处理器是否能处理该事件内容
     *
     * @param eventContent 事件内容
     * @return 返回是否能处理该事件内容，只有能处理该事件内容的时候才会继续调用处理方法
     * @see #processEventContent(Map, ProcessorChain)
     */
    protected abstract boolean supportsEventContent(Map<String, Object> eventContent);

    /**
     * 处理事件内容的方法
     *
     * @param eventContent 事件内容
     * @param chain 通知数据处理器链不为空，用于决定是否继续处理
     */
    protected abstract void processEventContent(Map<String, Object> eventContent, ProcessorChain<DefaultNotifyContent> chain);
}
