package work.gaigeshen.tripartite.core.parameter.converter;

import work.gaigeshen.tripartite.core.parameter.Parameter;
import work.gaigeshen.tripartite.core.parameter.Parameters;

/**
 * 请求参数转换器
 *
 * @author gaigeshen
 */
public interface ParametersConverter {
  /**
   * 将任意对象转换为请求参数
   *
   * @param parameters 任意对象不能为空
   * @return 请求参数不为空
   * @throws ParametersConversionException 转换的时候发生异常
   */
  Parameters convert(Object parameters) throws ParametersConversionException;

  /**
   * 静态方法用于直接将任意对象转换为指定类型的请求参数
   *
   * @param parameters 任意对象不能为空
   * @param otherParameters 其他可选的附加参数
   * @return 请求参数不为空
   * @throws ParametersConversionException 转换的时候发生异常
   */
  static Parameters convertJson(Object parameters, Parameter<?>... otherParameters) throws ParametersConversionException {
    return JsonParametersConverter.INSTANCE.convert(parameters).put(otherParameters);
  }

  /**
   * 静态方法用于直接将任意对象转换为指定类型的请求参数
   *
   * @param parameters 任意对象不能为空
   * @param otherParameters 其他可选的附加参数
   * @return 请求参数不为空
   * @throws ParametersConversionException 转换的时候发生异常
   */
  static Parameters convertParameters(Object parameters, Parameter<?>... otherParameters) throws ParametersConversionException {
    return ParametersParametersConverter.INSTANCE.convert(parameters).put(otherParameters);
  }

  /**
   * 静态方法用于直接将任意对象转换为指定类型的请求参数
   *
   * @param parameters 任意对象不能为空
   * @param otherParameters 其他可选的附加参数
   * @return 请求参数不为空
   * @throws ParametersConversionException 转换的时候发生异常
   */
  static Parameters convertMultipartParameters(Object parameters, Parameter<?>... otherParameters) throws ParametersConversionException {
    return MultipartParametersParametersConverter.INSTANCE.convert(parameters).put(otherParameters);
  }
}
