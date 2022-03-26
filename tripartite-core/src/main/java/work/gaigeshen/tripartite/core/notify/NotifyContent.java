package work.gaigeshen.tripartite.core.notify;

import java.util.Map;

/**
 * 异步通知数据至少包含请求头
 *
 * @author gaigeshen
 */
public interface NotifyContent {
  /**
   * 返回所有的请求头和其对应的值
   *
   * @return 请求头不为空
   */
  Map<String, String[]> getHeaders();

  /**
   * 追加请求头
   *
   * @param name 请求头名称不能为空
   * @param values 请求头的值不能为空或者空集合
   */
  void putHeader(String name, String[] values);

  /**
   * 返回是否存在指定的请求头
   *
   * @param name 请求头名称不能为空
   * @return 是否存在指定的请求头
   */
  boolean containsHeader(String name);

  /**
   * 获取请求头的值
   *
   * @param name 请求头名称不能为空
   * @return 如果存在该请求头名称，则返回的值不为空或者空集合
   */
  String[] getHeaderValues(String name);

  /**
   * 获取请求头的首个值
   *
   * @param name 请求头名称不能为空
   * @return 如果存在该请求头名称，则返回的请求头的首个值不为空
   */
  String getHeaderValue(String name);
}
