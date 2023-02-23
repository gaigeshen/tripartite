package work.gaigeshen.tripartite.core.parameter.converter;

import work.gaigeshen.tripartite.core.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 支持动态选择的请求参数转换器
 *
 * @author gaigeshen
 */
public class DynamicParametersConverter implements ParametersConverter {

    private final Map<Class<?>, ParametersConverter> converters = new HashMap<>();

    /**
     * 设置指定类型应该选择哪个请求参数转换器
     *
     * @param converters 指定类型对应的请求参数转换器，不可为空对象
     */
    public void setConverters(Map<Class<?>, ParametersConverter> converters) {
        if (Objects.isNull(converters)) {
            throw new IllegalArgumentException("parameter converters cannot be null");
        }
        this.converters.putAll(converters);
    }

    @Override
    public Parameters convert(Object parameters) throws ParametersConversionException {
        if (Objects.isNull(parameters)) {
            throw new IllegalArgumentException("parameters cannot be null");
        }
        ParametersConverter converter = converters.get(parameters.getClass());
        if (Objects.isNull(converter)) {
            throw new IllegalArgumentException("parameters is not supported: " + parameters.getClass());
        }
        return converter.convert(parameters);
    }
}
