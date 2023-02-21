package work.gaigeshen.tripartite.core.parameter.typed.converter;

import work.gaigeshen.tripartite.core.parameter.DefaultParameters;
import work.gaigeshen.tripartite.core.parameter.Parameter;
import work.gaigeshen.tripartite.core.parameter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.ParameterResolver;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 默认的单个请求参数的转换器
 *
 * @author gaigeshen
 */
public class DefaultParameterConverter implements ParameterConverter {

    public static final DefaultParameterConverter INSTANCE = new DefaultParameterConverter();

    @Override
    public Parameter<?> convert(String name, Object rawParameter) throws ParameterConversionException {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (Objects.isNull(rawParameter)) {
            throw new IllegalArgumentException("raw parameter cannot be null");
        }
        if (rawParameter instanceof Boolean) {
            return Parameter.bool(name, (Boolean) rawParameter);
        }
        if (rawParameter instanceof String) {
            return Parameter.string(name, (String) rawParameter);
        }
        if (rawParameter instanceof Number) {
            return Parameter.number(name, (Number) rawParameter);
        }
        if (rawParameter instanceof Date) {
            return Parameter.date(name, (Date) rawParameter);
        }
        if (rawParameter instanceof byte[]) {
            return Parameter.byteArray(name, (byte[]) rawParameter);
        }
        if (rawParameter instanceof Collection) {
            Collection<?> collectionValue = (Collection<?>) rawParameter;
            if (collectionValue.isEmpty()) {
                return Parameter.collection(name, Collections.emptyList());
            }
            Collection<Object> itemCollection = new ArrayList<>();
            for (Object item : collectionValue) {
                if (item instanceof Boolean || item instanceof String || item instanceof Number || item instanceof byte[]) {
                    itemCollection.add(item);
                    continue;
                }
                try {
                    itemCollection.add(convertParameters(item));
                } catch (IllegalAccessException e) {
                    throw new ParameterConversionException("could not convert parameter: " + item, e);
                }
            }
            return Parameter.collection(name, itemCollection);
        }
        try {
            return Parameter.parameters(name, convertParameters(rawParameter));
        } catch (IllegalAccessException e) {
            throw new ParameterConversionException("could not convert parameter: " + rawParameter, e);
        }
    }

    private Parameters convertParameters(Object parameters) throws IllegalAccessException {

        Parameters converted = new DefaultParameters(Parameters.Type.INTERNAL);

        if (parameters instanceof Map) {
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) parameters).entrySet()) {
                Object value = entry.getValue();
                Object key = entry.getKey();
                if (Objects.isNull(value) || Objects.isNull(key) || !(key instanceof String)) {
                    continue;
                }
                converted.put(convert((String) key, value));
            }
            return converted;
        }
        Class<?> currentClass = parameters.getClass();
        while (Objects.nonNull(currentClass)) {
            for (Field field : currentClass.getDeclaredFields()) {
                ParameterResolver.addParameter(converted, field, parameters);
            }
            currentClass = currentClass.getSuperclass();
        }
        return converted;
    }

}
