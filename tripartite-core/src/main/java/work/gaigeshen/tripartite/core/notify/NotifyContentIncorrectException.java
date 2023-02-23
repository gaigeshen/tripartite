package work.gaigeshen.tripartite.core.notify;

/**
 * 异步通知数据不合法的异常
 *
 * @author gaigeshen
 */
public class NotifyContentIncorrectException extends NotifyContentException {
    public NotifyContentIncorrectException(String message) {
        super(message);
    }

    public NotifyContentIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }
}
