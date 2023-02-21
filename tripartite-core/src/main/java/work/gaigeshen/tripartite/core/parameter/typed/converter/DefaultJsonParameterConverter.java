package work.gaigeshen.tripartite.core.parameter.typed.converter;

import work.gaigeshen.tripartite.core.util.json.JsonCodec;

/**
 * @author gaigeshen
 */
public class DefaultJsonParameterConverter extends AbstractJsonParameterConverter {

    public static final DefaultJsonParameterConverter INSTANCE = new DefaultJsonParameterConverter();

    @Override
    protected String convertJson(Object rawParameter) throws ParameterConversionException {
        try {
            return JsonCodec.instance().encode(rawParameter);
        } catch (Exception e) {
            throw new ParameterConversionException("could not convert to json: " + rawParameter, e);
        }
    }
}
