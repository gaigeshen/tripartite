package work.gaigeshen.triparttite.core.header;

import java.util.List;

/**
 * 请求头或者响应头
 *
 * @author gaigeshen
 */
public interface Headers {

  void addValue(String name, String value);

  void addValues(String name, List<String> values);

  void putValue(String name, String value);

  void putValues(String name, List<String> values);

  List<String> getValues(String name);

  String getValue(String name);

  boolean contains(String name);

  void remove(String name);
}
