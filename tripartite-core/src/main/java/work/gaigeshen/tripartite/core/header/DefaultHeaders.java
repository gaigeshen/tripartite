package work.gaigeshen.tripartite.core.header;

import java.util.*;

/**
 * 默认的请求头或者响应头
 *
 * @author gaigeshen
 */
public class DefaultHeaders implements Headers {

  private final Map<String, List<String>> headers = new HashMap<>();

  @Override
  public void addValue(String name, String value) {
    if (Objects.isNull(name)) {
      throw new IllegalArgumentException("name cannot be null");
    }
    if (Objects.isNull(value)) {
      throw new IllegalArgumentException("value cannot be null");
    }
    addValues(name, Collections.singletonList(value));
  }

  @Override
  public void addValues(String name, List<String> values) {
    if (Objects.isNull(name)) {
      throw new IllegalArgumentException("name cannot be null");
    }
    if (Objects.isNull(values) || values.isEmpty()) {
      throw new IllegalArgumentException("values cannot be null or empty");
    }
    headers.computeIfAbsent(name, n -> new ArrayList<>()).addAll(values);
  }

  @Override
  public void putValue(String name, String value) {
    if (Objects.isNull(name)) {
      throw new IllegalArgumentException("name cannot be null");
    }
    if (Objects.isNull(value)) {
      throw new IllegalArgumentException("value cannot be null");
    }
    putValues(name, Collections.singletonList(value));
  }

  @Override
  public void putValues(String name, List<String> values) {
    if (Objects.isNull(name)) {
      throw new IllegalArgumentException("name cannot be null");
    }
    if (Objects.isNull(values) || values.isEmpty()) {
      throw new IllegalArgumentException("values cannot be null or empty");
    }
    headers.put(name, values);
  }

  @Override
  public List<String> getValues(String name) {
    if (Objects.isNull(name)) {
      throw new IllegalArgumentException("name cannot be null");
    }
    return headers.get(name);
  }

  @Override
  public String getValue(String name) {
    if (Objects.isNull(name)) {
      throw new IllegalArgumentException("name cannot be null");
    }
    List<String> values = getValues(name);
    if (Objects.isNull(values)) {
      return null;
    }
    return values.get(0);
  }

  @Override
  public boolean contains(String name) {
    if (Objects.isNull(name)) {
      throw new IllegalArgumentException("name cannot be null");
    }
    return headers.containsKey(name);
  }

  @Override
  public void remove(String name) {
    if (Objects.isNull(name)) {
      throw new IllegalArgumentException("name cannot be null");
    }
    headers.remove(name);
  }

  @Override
  public String toString() {
    return headers.toString();
  }
}
