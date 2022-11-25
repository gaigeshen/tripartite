package work.gaigeshen.tripartite.ding.openapi.notify.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;

import java.util.Map;

/**
 *
 * @author gaigeshen
 */
public class DingSyncHttpPushMediumNotifyContentProcessor extends DingEventNotifyContentProcessor {

    private static final Logger log = LoggerFactory.getLogger(DingSyncHttpPushMediumNotifyContentProcessor.class);

    @Override
    protected boolean supportsEventContent(Map<String, Object> eventContent) {
        return checkEventType(eventContent, EVENT_TYPE_SYNC_HTTP_PUSH_MEDIUM);
    }

    @Override
    protected void processEventContent(Map<String, Object> eventContent, ProcessorChain<DefaultNotifyContent> chain) {
        log.info("<<<< Sync Http Push Medium Notify Content: {}", eventContent);
    }
}
