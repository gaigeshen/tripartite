package work.gaigeshen.triparttite.core.parameter.converter;

import work.gaigeshen.triparttite.core.parameter.Parameters;

/**
 * 指定类型的请求参数转换器
 *
 * @author gaigeshen
 */
public class JsonParametersConverter extends AbstractParametersConverter {

  public static final JsonParametersConverter INSTANCE = new JsonParametersConverter();

  @Override
  protected Parameters initParameters(Object parameters) {
    return Parameters.json();
  }
}
