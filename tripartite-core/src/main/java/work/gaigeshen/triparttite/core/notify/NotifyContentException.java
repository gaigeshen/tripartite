package work.gaigeshen.triparttite.core.notify;

/**
 * 异步通知数据异常
 *
 * @author gaigeshen
 */
public class NotifyContentException extends RuntimeException {
  public NotifyContentException(String message) {
    super(message);
  }
  public NotifyContentException(String message, Throwable cause) {
    super(message, cause);
  }
}
