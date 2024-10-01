package work.gaigeshen.tripartite.core.util.json;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @author gaigeshen
 */
public abstract class JsonUtils {

    private JsonUtils() { }

    public static String encode(Object obj) {
        return JsonCodec.instance().encode(obj);
    }

    public static <T> T decodeObject(String json, Class<T> resultClass) {
        return JsonCodec.instance().decodeObject(json, resultClass);
    }

    public static <E> Collection<E> decodeCollection(String json, Class<E> itemClass) {
        return JsonCodec.instance().decodeCollection(json, itemClass);
    }

    public static Map<String, Object> decodeObject(String json) {
        return JsonCodec.instance().decodeObject(json);
    }

    public static Collection<Object> decodeCollection(String json) {
        return JsonCodec.instance().decodeCollection(json);
    }
}
