package work.gaigeshen.tripartite.core.notify;

import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * 异步通知数据处理器集合包装，持有所有的处理器
 *
 * @author gaigeshen
 */
class NotifyContentProcessors<C extends NotifyContent> {

    private final List<NotifyContentProcessor<C>> processors = new ArrayList<>();

    /**
     * 设置异步通知数据处理器集合，每次调用将会替换掉所有原有的处理器
     *
     * @param processors 新的异步通知数据处理器集合不能为空
     */
    public void setProcessors(List<NotifyContentProcessor<C>> processors) {
        ArgumentValidate.notNull(processors, "notify content processors cannot be null");
        this.processors.clear();
        for (NotifyContentProcessor<C> processor : processors) {
            if (Objects.isNull(processor)) {
                continue;
            }
            this.processors.add(processor);
        }
    }

    /**
     * 调用此方法将传入的异步通知数据委托给内部所有的处理器进行处理
     *
     * @param content 异步通知数据不能为空
     * @throws NotifyContentProcessingException 处理异步通知数据的时候发生异常
     */
    public void process(C content) throws NotifyContentProcessingException {
        ArgumentValidate.notNull(content, "notify content cannot be null");
        if (processors.isEmpty()) {
            return;
        }
        new InternalProcessorChain<>(processors).process(content);
    }

    /**
     * 异步通知数据处理器链内部实现
     *
     * @author gaigeshen
     */
    private static class InternalProcessorChain<C extends NotifyContent> implements NotifyContentProcessor.ProcessorChain<C> {

        private final Iterator<NotifyContentProcessor<C>> iterator;

        private InternalProcessorChain(List<NotifyContentProcessor<C>> processors) {
            this.iterator = processors.iterator();
        }

        @Override
        public void process(C content) throws NotifyContentProcessingException {
            ArgumentValidate.notNull(content, "notify content cannot be null");
            while (iterator.hasNext()) {
                iterator.next().process(content, this);
            }
        }
    }
}
