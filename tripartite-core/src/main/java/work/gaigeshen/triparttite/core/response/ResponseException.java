package work.gaigeshen.triparttite.core.response;

import work.gaigeshen.triparttite.core.WebException;

/**
 * 有关响应结果的异常
 *
 * @author gaigeshen
 */
public class ResponseException extends WebException {
  public ResponseException(String message) {
    super(message);
  }
  public ResponseException(String message, Throwable cause) {
    super(message, cause);
  }
}
