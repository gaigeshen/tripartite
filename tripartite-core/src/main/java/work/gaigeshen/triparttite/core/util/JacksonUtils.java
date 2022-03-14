package work.gaigeshen.triparttite.core.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author gaigeshen
 */
public abstract class JacksonUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static String toJson(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T fromJson(String json, Class<T> resultClass) {
    try {
      return objectMapper.readValue(json, resultClass);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static JsonNode toJsonNode(Object object) {
    try {
      return objectMapper.valueToTree(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T fromJsonNode(JsonNode jsonNode, Class<T> resultClass) {
    try {
      return objectMapper.treeToValue(jsonNode, resultClass);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static JsonNode toJsonNode(String json) {
    try {
      return objectMapper.readTree(json);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
