package work.gaigeshen.triparttite.core.notify;

/**
 * 异步通知数据接收器
 *
 * @author gaigeshen
 */
public interface NotifyContentReceiver<C extends NotifyContent> {
  /**
   * 此方法接收异步通知数据
   *
   * @param content 异步通知数据不能为空
   * @throws NotifyContentException 接收此异步通知数据的时候发生异常
   */
  void receive(C content) throws NotifyContentException;
}
