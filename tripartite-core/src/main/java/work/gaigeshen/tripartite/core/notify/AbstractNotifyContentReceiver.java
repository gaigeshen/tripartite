package work.gaigeshen.tripartite.core.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.List;
import java.util.Objects;

/**
 * 异步通知数据接收器抽象，结合异步通知数据处理器
 *
 * @author gaigeshen
 */
public abstract class AbstractNotifyContentReceiver<C extends NotifyContent> implements NotifyContentReceiver<C> {

    private static final Logger log = LoggerFactory.getLogger(AbstractNotifyContentReceiver.class);

    private final NotifyContentProcessors<C> processors = new NotifyContentProcessors<>();

    /**
     * 设置异步通知数据处理器，每次调用将会替换原先的所有处理器
     *
     * @param processors 新的异步通知数据处理器集合不能为空
     */
    public final void setProcessors(List<NotifyContentProcessor<C>> processors) {
        ArgumentValidate.notNull(processors, "notify parameters processors cannot be null");
        this.processors.setProcessors(processors);
    }

    /**
     * 此方法用于校验传入的异步通知数据，校验通过之后会将返回的异步通知数据交给处理器进行处理
     *
     * @param content 异步通知数据不为空
     * @return 返回校验后的异步通知数据不能为空
     * @throws NotifyContentIncorrectException 校验的时候发现该异步通知数据不合法
     */
    protected C validate(C content) throws NotifyContentIncorrectException {
        return content;
    }

    @Override
    public final void receive(C content) throws NotifyContentException {
        ArgumentValidate.notNull(content, "notify content cannot be null");
        log.info("<<<< Notify Content: {}", content);
        C validated = validate(content);
        if (Objects.isNull(validated)) {
            throw new NotifyContentIncorrectException("validated content cannot be null: " + content);
        }
        processors.process(validated);
    }
}
