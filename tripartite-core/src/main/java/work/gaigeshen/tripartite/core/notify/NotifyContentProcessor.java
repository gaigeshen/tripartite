package work.gaigeshen.tripartite.core.notify;

/**
 * 异步通知数据处理器
 *
 * @author gaigeshen
 */
public interface NotifyContentProcessor<C extends NotifyContent> {
  /**
   * 处理异步通知数据
   *
   * @param content 异步通知数据不为空
   * @param chain 处理器链不为空
   * @throws NotifyContentProcessingException 处理异步通知数据的时候发生异常
   */
  void process(C content, ProcessorChain<C> chain) throws NotifyContentProcessingException;

  /**
   * 异步通知数据处理器链
   *
   * @author gaigeshen
   */
  interface ProcessorChain<C extends NotifyContent> {
    /**
     * 处理器链的处理方法接受异步通知数据
     *
     * @param content 异步通知数据不能为空
     * @throws NotifyContentProcessingException 处理异步通知数据的时候发生异常
     */
    void process(C content) throws NotifyContentProcessingException;

  }

}
