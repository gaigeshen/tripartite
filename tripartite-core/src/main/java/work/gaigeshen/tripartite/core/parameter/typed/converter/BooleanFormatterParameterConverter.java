package work.gaigeshen.tripartite.core.parameter.typed.converter;

import work.gaigeshen.tripartite.core.parameter.Parameter;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

/**
 * 此转换器会将布尔值的单个参数转换为默认格式的数字字符串
 *
 * @author gaigeshen
 */
public class BooleanFormatterParameterConverter implements ParameterConverter {

    public static final BooleanFormatterParameterConverter INSTANCE = new BooleanFormatterParameterConverter();

    @Override
    public Parameter<?> convert(String name, Object rawParameter) throws ParameterConversionException {
        ArgumentValidate.notNull(name, "name cannot be null");
        ArgumentValidate.notNull(rawParameter, "rawParameter cannot be null");
        if (!(rawParameter instanceof Boolean)) {
            throw new ParameterConversionException(rawParameter.getClass() + " not supported by this converter");
        }
        return Parameter.string(name, ((Boolean) rawParameter) ? "1" : "0");
    }
}
