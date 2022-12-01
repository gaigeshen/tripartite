package work.gaigeshen.tripartite.ding.openapi.notify.event;

import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.core.util.json.JsonCodec;
import work.gaigeshen.tripartite.ding.openapi.client.DingSuiteTicketStore;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * 第三方企业应用票据事件推送处理器
 *
 * @author gaigeshen
 */
public class DingSuiteTicketEventNotifyContentProcessor extends DingEventNotifyContentProcessor {

    private final DingConfig config;

    private final DingSuiteTicketStore suiteTicketStore;

    /**
     * 创建此事件推送处理器
     *
     * @param config 钉钉配置信息
     * @param suiteTicketStore 票据存储器
     */
    public DingSuiteTicketEventNotifyContentProcessor(DingConfig config, DingSuiteTicketStore suiteTicketStore) {
        if (Objects.isNull(config) || Objects.isNull(suiteTicketStore)) {
            throw new IllegalArgumentException("config and suite ticket store cannot be null");
        }
        this.config = config;
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
        Collection<?> bizData = (Collection<?>) eventContent.get("bizData");
        if (Objects.isNull(bizData) || bizData.isEmpty()) {
            chain.process(content);
        }
        Map<?, ?> bizDataContent = (Map<?, ?>) bizData.iterator().next();
        if (bizDataContent.isEmpty() || !Objects.equals(2, bizDataContent.get("biz_type"))) {
            chain.process(content);
        }
        String bizDataWantedText = (String) bizDataContent.get("biz_data");
        if (Objects.isNull(bizDataWantedText)) {
            chain.process(content);
        }
        Map<String, Object> bizDataWanted = JsonCodec.instance().decodeObject(bizDataWantedText);
        if (Objects.isNull(bizDataWanted) || bizDataWanted.isEmpty()) {
            chain.process(content);
        }
        suiteTicketStore.setSuiteTicket(config.getAppKey(), (String) bizDataWanted.get("suiteTicket"));
    }
}
