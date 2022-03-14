package work.gaigeshen.triparttite.core.parameter.converter;

import work.gaigeshen.triparttite.core.parameter.Parameters;

/**
 * 请求参数对象自定义器
 *
 * @author gaigeshen
 * @see ParametersMetadataParametersConverter
 */
@FunctionalInterface
public interface ParametersCustomizer {

  /**
   * 此方法在请求参数转换之后被调用，这个方法可以用来对请求参数进行修改操作
   *
   * @param parameters 请求参数不会为空
   * @param rawParameters 原始的请求参数对象不会为空
   * @param config 可以传递额外的配置对象，此对象支持可以为空
   * @throws ParametersCustomizingException 这个方法可能抛出的异常
   */
  void customize(Parameters parameters, Object rawParameters, Object config) throws ParametersCustomizingException;
}
