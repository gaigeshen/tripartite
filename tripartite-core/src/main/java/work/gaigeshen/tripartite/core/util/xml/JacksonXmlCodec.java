package work.gaigeshen.tripartite.core.util.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
class JacksonXmlCodec implements XmlCodec {

    public static final JacksonXmlCodec INSTANCE = new JacksonXmlCodec();

    private final XmlMapper xmlMapper;

    public JacksonXmlCodec(XmlMapper xmlMapper) {
        ArgumentValidate.notNull(xmlMapper, "xmlMapper cannot be null");
        this.xmlMapper = xmlMapper;
    }

    public JacksonXmlCodec() {
        this(new XmlMapper());
    }

    @Override
    public String encode(Object obj) {
        ArgumentValidate.notNull(obj, "object cannot be null");
        try {
            return xmlMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new IllegalArgumentException("could not encode: " + obj, e);
        }
    }

    @Override
    public <T> T decodeObject(String xml, Class<T> resultClass) {
        ArgumentValidate.notTrue(Objects.isNull(xml) || Objects.isNull(resultClass),
                "xml and result class cannot be null");
        try {
            return xmlMapper.readValue(xml, resultClass);
        } catch (Exception e) {
            throw new IllegalArgumentException("could not decode: " + xml, e);
        }
    }
}
