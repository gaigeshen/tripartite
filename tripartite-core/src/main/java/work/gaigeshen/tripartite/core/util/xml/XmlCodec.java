package work.gaigeshen.tripartite.core.util.xml;

/**
 *
 * @author gaigeshen
 */
public interface XmlCodec {

    static XmlCodec instance() {
        return JacksonXmlCodec.INSTANCE;
    }

    String encode(Object obj);

    <T> T decodeObject(String xml, Class<T> resultClass);
}
