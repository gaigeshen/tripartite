package work.gaigeshen.tripartite.ding.openapi.notify.event;

import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.core.util.json.JsonCodec;
import work.gaigeshen.tripartite.ding.openapi.client.DingSuiteTicketStore;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * 钉钉套件票据事件推送处理器
 *
 * @author gaigeshen
 */
public class DingSuiteTicketEventNotifyContentProcessor extends DingEventNotifyContentProcessor {

    private final DingSuiteTicketStore suiteTicketStore;

    /**
     * 创建此事件推送处理器
     *
     * @param suiteTicketStore 钉钉套件票据存储器
     */
    public DingSuiteTicketEventNotifyContentProcessor(DingSuiteTicketStore suiteTicketStore) {
        if (Objects.isNull(suiteTicketStore)) {
            throw new IllegalArgumentException("suite ticket store cannot be null");
        }
        this.suiteTicketStore = suiteTicketStore;
    }

    @Override
    protected boolean supportsEventContent(Map<String, Object> eventContent) {
        return checkEventType(eventContent, "SYNC_HTTP_PUSH_HIGH");
    }

    /**
     * 此方法只会处理票据事件推送，如果不是该事件则会交给下个处理器继续处理
     *
     * @param eventContent 事件内容
     * @param content 原始的通知数据
     * @param chain 通知数据处理器链不为空，用于决定是否继续处理
     */
    @Override
    protected void processEventContent(Map<String, Object> eventContent, DefaultNotifyContent content, ProcessorChain<DefaultNotifyContent> chain) {
        Collection<?> bizDataCollection = (Collection<?>) eventContent.get("bizData");
        if (Objects.isNull(bizDataCollection) || bizDataCollection.isEmpty()) {
            chain.process(content);
            return;
        }
        for (Object bizDataObject : bizDataCollection) {
            Map<?, ?> bizDataContent = (Map<?, ?>) bizDataObject;
            if (bizDataContent.isEmpty() || !Objects.equals(2, bizDataContent.get("biz_type"))) {
                continue;
            }
            String suiteIdText = (String) bizDataContent.get("biz_id");
            String bizDataText = (String) bizDataContent.get("biz_data");
            if (Objects.isNull(suiteIdText) || Objects.isNull(bizDataText)) {
                return;
            }
            Map<String, Object> bizDataWanted = JsonCodec.instance().decodeObject(bizDataText);
            String suiteTicket = (String) bizDataWanted.get("suiteTicket");
            if (Objects.isNull(suiteTicket)) {
                return;
            }
            suiteTicketStore.setTicket(suiteIdText, suiteTicket);
            return;
        }
        chain.process(content);
    }
}
