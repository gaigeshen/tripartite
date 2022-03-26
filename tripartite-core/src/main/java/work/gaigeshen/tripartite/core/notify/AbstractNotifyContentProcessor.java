package work.gaigeshen.tripartite.core.notify;

/**
 * 异步通知数据处理器抽象
 *
 * @author gaigeshen
 */
public abstract class AbstractNotifyContentProcessor<C extends NotifyContent> implements NotifyContentProcessor<C> {

  @Override
  public final void process(C content, ProcessorChain<C> chain) throws NotifyContentProcessingException {
    if (supports(content)) {
      processInternal(content, chain);
    } else {
      chain.process(content);
    }
  }

  /**
   * 此处理器在处理异步通知数据之前，将会调用此方法确认是否需要处理此异步通知数据
   *
   * @param content 异步通知数据不为空
   * @return 返回值确认是否需要处理此异步通知数据
   */
  protected abstract boolean supports(C content);

  /**
   * 当确认需要处理该异步通知数据的时候会调用此方法
   *
   * @param content 异步通知数据不为空
   * @param chain 异步通知数据处理器链不为空
   * @throws NotifyContentProcessingException 处理异步通知数据的时候发生异常
   */
  protected abstract void processInternal(C content, ProcessorChain<C> chain) throws NotifyContentProcessingException;
}
