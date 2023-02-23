package work.gaigeshen.tripartite.core.parameter.typed.converter;

import work.gaigeshen.tripartite.core.parameter.Parameter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * 此转换器会将日期类型的单个参数转换为默认格式的字符串
 *
 * @author gaigeshen
 */
public class DateFormatterParameterConverter implements ParameterConverter {

    public static final DateFormatterParameterConverter INSTANCE = new DateFormatterParameterConverter();

    @Override
    public Parameter<?> convert(String name, Object rawParameter) throws ParameterConversionException {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (Objects.isNull(rawParameter)) {
            throw new IllegalArgumentException("raw parameter cannot be null");
        }
        if (!(rawParameter instanceof Date)) {
            throw new ParameterConversionException(rawParameter.getClass() + " not supported by this converter");
        }
        return Parameter.string(name, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rawParameter));
    }
}
