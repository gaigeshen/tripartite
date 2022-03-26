package work.gaigeshen.tripartite.core.parameter.converter;

import work.gaigeshen.tripartite.core.parameter.ParametersException;

/**
 * 请求参数在自定义操作的时候发生异常
 *
 * @author gaigeshen
 */
public class ParametersCustomizingException extends ParametersException {
  public ParametersCustomizingException(String message) {
    super(message);
  }
  public ParametersCustomizingException(String message, Throwable cause) {
    super(message, cause);
  }
}
