package work.gaigeshen.triparttite.core.parameter;

import java.util.*;

/**
 * 请求参数
 *
 * @author gaigeshen
 */
public interface Parameters extends Iterable<Parameter<?>> {

  static Parameters json() {
    return new DefaultParameters(Type.JSON);
  }

  static Parameters json(Collection<Parameter<?>> parameters) {
    return json().put(parameters);
  }

  static Parameters json(Parameter<?>... parameters) {
    if (Objects.isNull(parameters)) {
      return json();
    }
    return json(Arrays.asList(parameters));
  }

  static Parameters parameters() {
    return new DefaultParameters(Type.PARAMETERS);
  }

  static Parameters parameters(Collection<Parameter<?>> parameters) {
    return parameters().put(parameters);
  }

  static Parameters parameters(Parameter<?>... parameters) {
    if (Objects.isNull(parameters)) {
      return parameters();
    }
    return parameters(Arrays.asList(parameters));
  }

  static Parameters multipartParameters() {
    return new DefaultParameters(Type.MULTIPART_PARAMETERS);
  }

  static Parameters multipartParameters(Collection<Parameter<?>> parameters) {
    return multipartParameters().put(parameters);
  }

  static Parameters multipartParameters(Parameter<?>... parameters) {
    if (Objects.isNull(parameters)) {
      return multipartParameters();
    }
    return multipartParameters(Arrays.asList(parameters));
  }

  Type getType();

  Parameters put(Collection<Parameter<?>> parameters);

  Parameters put(Parameter<?>... parameters);

  default Parameters put(String name, String value) {
    return put(Parameter.string(name, value));
  }

  default Parameters put(String name, Number value) {
    return put(Parameter.number(name, value));
  }

  default Parameters put(String name, Date value) {
    return put(Parameter.date(name, value));
  }

  default Parameters put(String name, byte[] value) {
    return put(Parameter.byteArray(name, value));
  }

  default Parameters put(String name, Parameters value) {
    return put(Parameter.parameters(name, value));
  }

  @Override
  Iterator<Parameter<?>> iterator();

  default String join(String delimiter) {
    if (Objects.isNull(delimiter)) {
      throw new IllegalArgumentException("delimiter cannot be null");
    }
    StringJoiner joiner = new StringJoiner(delimiter);
    for (Parameter<?> parameter : this) {
      joiner.add(parameter.getName() + "=" + parameter.getValue());
    }
    return joiner.toString();
  }

  /**
   *
   * @author gaigeshen
   */
  enum Type {
    JSON, PARAMETERS, MULTIPART_PARAMETERS, INTERNAL
  }

}
