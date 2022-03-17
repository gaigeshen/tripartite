package work.gaigeshen.triparttite.core.util.json;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @author gaigeshen
 * @see <a href="http://www.json.org/json-en.html">JSON (JavaScript Object Notation)</a>
 */
public interface JsonCodec {

  /**
   * 调用此方法返回默认的编解码器
   *
   * @return 返回默认的编解码器不能为空
   */
  static JsonCodec instance() {
    return GsonJsonCodec.INSTANCE;
  }

  /**
   * 将任意对象实例编码为字符串
   *
   * @param obj 任意对象不能为空
   * @return 编码为字符串不能为空
   */
  String encode(Object obj);

  /**
   * 将字符串解码为指定类型的对象实例
   *
   * @param json 字符串不能为空
   * @param resultClass 指定的类型不能为空
   * @param <T> 解码对象类型
   * @return 字符串解码的对象实例不能为空
   */
  <T> T decode(String json, Class<T> resultClass);

  /**
   * 将字符串解码为映射类型的对象实例
   *
   * @param json 字符串不能为空
   * @return 映射类型的对象实例不能为空
   */
  Map<String, Object> decodeObject(String json);

  /**
   * 将字符串解码为集合类型的对象实例
   *
   * @param json 字符串不能为空
   * @return 集合类型的对象实例不能为空
   */
  Collection<Object> decodeArray(String json);

}
