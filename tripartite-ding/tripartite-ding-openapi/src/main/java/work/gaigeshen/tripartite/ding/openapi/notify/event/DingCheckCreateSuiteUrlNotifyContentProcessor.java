package work.gaigeshen.tripartite.ding.openapi.notify.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;

import java.util.Map;

/**
 *
 * @author gaigeshen
 */
public class DingCheckCreateSuiteUrlNotifyContentProcessor extends DingEventNotifyContentProcessor {

    private static final Logger log = LoggerFactory.getLogger(DingCheckCreateSuiteUrlNotifyContentProcessor.class);

    @Override
    protected boolean supportsEventContent(Map<String, Object> eventContent) {
        return checkEventType(eventContent, EVENT_TYPE_CHECK_CREATE_SUITE_URL);
    }

    @Override
    protected void processEventContent(Map<String, Object> eventContent, ProcessorChain<DefaultNotifyContent> chain) {
        log.info("<<<< Check Create Suite Url Notify Content: {}", eventContent);
    }
}
