package work.gaigeshen.tripartite.ding.sample.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentProcessor;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.core.notify.NotifyContentProcessingException;

/**
 * @author gaigeshen
 */
@Component
public class DingNotifyContentProcessor extends AbstractNotifyContentProcessor<DefaultNotifyContent> {

    private static final Logger log = LoggerFactory.getLogger(DingNotifyContentProcessor.class);

    @Override
    protected boolean supports(DefaultNotifyContent content) {
        return true;
    }

    @Override
    protected void processInternal(DefaultNotifyContent content, ProcessorChain<DefaultNotifyContent> chain) throws NotifyContentProcessingException {
        log.info("notify content: {}", content);
        chain.process(content);
    }
}
