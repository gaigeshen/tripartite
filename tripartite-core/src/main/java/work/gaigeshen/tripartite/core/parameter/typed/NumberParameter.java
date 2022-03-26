package work.gaigeshen.tripartite.core.parameter.typed;

/**
 * 数字类型的单个请求参数
 *
 * @author gaigeshen
 */
public class NumberParameter extends AbstractParameter<Number> {

  public NumberParameter(String name, Number value) {
    super(name, value);
  }
}
