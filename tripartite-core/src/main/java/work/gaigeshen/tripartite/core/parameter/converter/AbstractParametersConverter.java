package work.gaigeshen.tripartite.core.parameter.converter;

import work.gaigeshen.tripartite.core.parameter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.ParameterResolver;
import work.gaigeshen.tripartite.core.parameter.typed.converter.DefaultParameterConverter;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

/**
 * 抽象的请求参数转换器
 *
 * @author gaigeshen
 */
public abstract class AbstractParametersConverter implements ParametersConverter {

    @Override
    public final Parameters convert(Object parameters) throws ParametersConversionException {
        ArgumentValidate.notNull(parameters, "parameters cannot be null");
        Parameters initialParameters = initParameters(parameters);
        ArgumentValidate.notNull(initialParameters, "init parameters cannot be null");
        if (parameters instanceof Map) {
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) parameters).entrySet()) {
                Object value = entry.getValue();
                Object key = entry.getKey();
                if (Objects.isNull(value) || Objects.isNull(key) || !(key instanceof String)) {
                    continue;
                }
                initialParameters.put(DefaultParameterConverter.INSTANCE.convert((String) key, value));
            }
        }
        else {
            Class<?> currentClass = parameters.getClass();
            while (Objects.nonNull(currentClass)) {
                try {
                    for (Field field : currentClass.getDeclaredFields()) {
                        ParameterResolver.addParameter(initialParameters, field, parameters);
                    }
                } catch (IllegalAccessException e) {
                    throw new ParametersConversionException("could not convert parameters: " + parameters, e);
                }
                currentClass = currentClass.getSuperclass();
            }
        }
        overrideParameters(initialParameters);
        return initialParameters;
    }

    /**
     * 子类提供请求参数的初始版本
     *
     * @param parameters 任意对象不为空
     * @return 返回的请求参数初始版本不可为空
     */
    protected abstract Parameters initParameters(Object parameters);

    /**
     * 重写此方法用于修改转换后的请求参数
     *
     * @param parameters 转换后的请求参数不会为空
     */
    protected void overrideParameters(Parameters parameters) {

    }
}
