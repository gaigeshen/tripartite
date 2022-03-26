package work.gaigeshen.tripartite.core.parameter.converter;

import work.gaigeshen.tripartite.core.parameter.Parameters;

/**
 * 指定类型的请求参数转换器
 *
 * @author gaigeshen
 */
public class MultipartParametersParametersConverter extends AbstractParametersConverter {

  public static final MultipartParametersParametersConverter INSTANCE = new MultipartParametersParametersConverter();

  @Override
  protected Parameters initParameters(Object parameters) {
    return Parameters.multipartParameters();
  }
}
