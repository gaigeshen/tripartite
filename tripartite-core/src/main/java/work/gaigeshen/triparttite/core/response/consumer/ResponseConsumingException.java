package work.gaigeshen.triparttite.core.response.consumer;

import work.gaigeshen.triparttite.core.response.ResponseException;

/**
 * 消费响应结果数据的时候发生异常
 *
 * @author gaigeshen
 */
public class ResponseConsumingException extends ResponseException {
  public ResponseConsumingException(String message) {
    super(message);
  }
  public ResponseConsumingException(String message, Throwable cause) {
    super(message, cause);
  }
}
