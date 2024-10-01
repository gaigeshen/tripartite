package work.gaigeshen.tripartite.core.util.xml;

/**
 *
 * @author gaigeshen
 */
public abstract class XmlUtils {

    private XmlUtils() { }

    public static String encode(Object obj) {
        return XmlCodec.instance().encode(obj);
    }

    public static <T> T decodeObject(String xml, Class<T> resultClass) {
        return XmlCodec.instance().decodeObject(xml, resultClass);
    }
}
