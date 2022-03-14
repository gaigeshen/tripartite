package work.gaigeshen.triparttite.ding.openapi.robotwebhook;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author gaigeshen
 */
public class DefaultDingRobotWebhookResponse implements DingRobotWebhookResponse, Map<String, Object> {

  private final Map<String, Object> internalData = new HashMap<>();

  @Override
  public int size() {
    return internalData.size();
  }

  @Override
  public boolean isEmpty() {
    return internalData.isEmpty();
  }

  @Override
  public boolean containsKey(Object key) {
    return internalData.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value) {
    return internalData.containsValue(value);
  }

  @Override
  public Object get(Object key) {
    return internalData.get(key);
  }

  @Override
  public Object put(String key, Object value) {
    return internalData.put(key, value);
  }

  @Override
  public Object remove(Object key) {
    return internalData.remove(key);
  }

  @Override
  public void putAll(Map<? extends String, ?> m) {
    internalData.putAll(m);
  }

  @Override
  public void clear() {
    internalData.clear();
  }

  @Override
  public Set<String> keySet() {
    return internalData.keySet();
  }

  @Override
  public Collection<Object> values() {
    return internalData.values();
  }

  @Override
  public Set<Entry<String, Object>> entrySet() {
    return internalData.entrySet();
  }

  @Override
  public String toString() {
    return internalData.toString();
  }
}
