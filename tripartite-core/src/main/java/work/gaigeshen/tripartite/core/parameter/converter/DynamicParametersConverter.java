package work.gaigeshen.tripartite.core.parameter.converter;

import work.gaigeshen.tripartite.core.parameter.Parameters;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.HashMap;
import java.util.Map;

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
        ArgumentValidate.notNull(converters, "parameters converters cannot be null");
        this.converters.putAll(converters);
    }

    @Override
    public Parameters convert(Object parameters) throws ParametersConversionException {
        ArgumentValidate.notNull(parameters, "parameters cannot be null");
        ParametersConverter converter = converters.get(parameters.getClass());
        ArgumentValidate.notNull(converter, "parameters is not supported: " + parameters.getClass());
        return converter.convert(parameters);
    }
}
