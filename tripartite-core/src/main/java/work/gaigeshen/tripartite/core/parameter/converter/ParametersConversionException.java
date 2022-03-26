package work.gaigeshen.tripartite.core.parameter.converter;

import work.gaigeshen.tripartite.core.parameter.ParametersException;

/**
 * 转换请求参数的时候发生异常
 *
 * @author gaigeshen
 */
public class ParametersConversionException extends ParametersException {
  public ParametersConversionException(String message) {
    super(message);
  }
  public ParametersConversionException(String message, Throwable cause) {
    super(message, cause);
  }
}
