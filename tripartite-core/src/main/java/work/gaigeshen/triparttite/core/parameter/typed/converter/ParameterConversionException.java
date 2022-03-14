package work.gaigeshen.triparttite.core.parameter.typed.converter;

import work.gaigeshen.triparttite.core.parameter.ParametersException;

/**
 * 单个请求参数转换失败的时候抛出此异常
 *
 * @author gaigeshen
 */
public class ParameterConversionException extends ParametersException {
  public ParameterConversionException(String message) {
    super(message);
  }
  public ParameterConversionException(String message, Throwable cause) {
    super(message, cause);
  }
}
