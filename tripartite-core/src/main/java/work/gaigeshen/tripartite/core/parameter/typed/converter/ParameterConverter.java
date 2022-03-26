package work.gaigeshen.tripartite.core.parameter.typed.converter;

import work.gaigeshen.tripartite.core.parameter.Parameter;

/**
 * 单个请求参数的转换器，用于转换任意类型的对象为单个请求参数对象，注意实现此接口的类必须提供默认的无参构造方法
 *
 * @author gaigeshen
 * @see DefaultParameterConverter
 */
public interface ParameterConverter {
  /**
   * 转换请求参数方法
   *
   * @param name 请求参数名称不能为空
   * @param rawParameter 原始的对象将会转换为请求参数对象不能为空
   * @return 转换后的请求参数对象
   * @throws ParameterConversionException 转换失败抛出此异常
   */
  Parameter<?> convert(String name, Object rawParameter) throws ParameterConversionException;
}
