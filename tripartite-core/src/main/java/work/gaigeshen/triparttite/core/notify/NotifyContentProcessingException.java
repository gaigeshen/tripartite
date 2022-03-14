package work.gaigeshen.triparttite.core.notify;

/**
 * 异步通知数据被处理的时候发生异常
 *
 * @author gaigeshen
 */
public class NotifyContentProcessingException extends NotifyContentException {
  public NotifyContentProcessingException(String message) {
    super(message);
  }
  public NotifyContentProcessingException(String message, Throwable cause) {
    super(message, cause);
  }
}
