package work.gaigeshen.triparttite.core.util.json;

import java.util.Map;

/**
 *
 * @author gaigeshen
 */
public interface JsonCodec {

  String encode(Object obj);

  <T> T decode(String json, Class<T> resultClass);

  Map<String, Object> decode(String json);

}
