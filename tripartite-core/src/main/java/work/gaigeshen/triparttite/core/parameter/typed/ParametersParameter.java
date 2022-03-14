package work.gaigeshen.triparttite.core.parameter.typed;

import work.gaigeshen.triparttite.core.parameter.Parameters;

/**
 * 请求参数类型的单个请求参数
 *
 * @author gaigeshen
 */
public class ParametersParameter extends AbstractParameter<Parameters> {

  public ParametersParameter(String name, Parameters value) {
    super(name, value);
  }
}
