package work.gaigeshen.tripartite.core.parameter;

import work.gaigeshen.tripartite.core.parameter.typed.*;

import java.util.Collection;
import java.util.Date;

/**
 * 单个请求参数
 *
 * @author gaigeshen
 */
public interface Parameter<T> extends Comparable<Parameter<?>> {

  /**
   * 创建布尔类型的单个请求参数
   *
   * @param name 参数名称不能为空
   * @param value 参数值不能为空
   * @return 单个请求参数
   */
  static Parameter<Boolean> bool(String name, Boolean value) {
    return new BooleanParameter(name, value);
  }

  /**
   * 创建字符串类型的单个请求参数
   *
   * @param name 参数名称不能为空
   * @param value 参数值不能为空
   * @return 单个请求参数
   */
  static Parameter<String> string(String name, String value) {
    return new StringParameter(name, value);
  }

  /**
   * 创建数字类型的单个请求参数
   *
   * @param name 参数名称不能为空
   * @param value 参数值不能为空
   * @return 单个请求参数
   */
  static Parameter<Number> number(String name, Number value) {
    return new NumberParameter(name, value);
  }

  /**
   * 创建日期类型的单个请求参数
   *
   * @param name 参数名称不能为空
   * @param value 参数值不能为空
   * @return 单个请求参数
   */
  static Parameter<Date> date(String name, Date value) {
    return new DateParameter(name, value);
  }

  /**
   * 创建字节串类型的单个请求参数
   *
   * @param name 参数名称不能为空
   * @param value 参数值不能为空
   * @return 单个请求参数
   */
  static Parameter<byte[]> byteArray(String name, byte[] value) {
    return new ByteArrayParameter(name, value);
  }

  /**
   * 创建请求参数类型的单个请求参数
   *
   * @param name 参数名称不能为空
   * @param value 参数值不能为空
   * @return 单个请求参数
   */
  static Parameter<Parameters> parameters(String name, Parameters value) {
    return new ParametersParameter(name, value);
  }

  /**
   * 创建集合类型的单个请求参数
   *
   * @param name 参数名称不能为空
   * @param value 参数值不能为空
   * @return 单个请求参数
   */
  static Parameter<Collection<?>> collection(String name, Collection<?> value) {
    return new CollectionParameter(name, value);
  }

  /**
   * 返回参数名称
   *
   * @return 参数名称不为空
   */
  String getName();

  /**
   * 返回参数值
   *
   * @return 参数值不为空
   */
  T getValue();
}
