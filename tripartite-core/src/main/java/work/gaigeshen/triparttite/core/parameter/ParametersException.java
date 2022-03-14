package work.gaigeshen.triparttite.core.parameter;

import work.gaigeshen.triparttite.core.WebException;

/**
 * 有关请求参数的异常
 *
 * @author gaigeshen
 */
public class ParametersException extends WebException {
  public ParametersException(String message) {
    super(message);
  }
  public ParametersException(String message, Throwable cause) {
    super(message, cause);
  }
}
