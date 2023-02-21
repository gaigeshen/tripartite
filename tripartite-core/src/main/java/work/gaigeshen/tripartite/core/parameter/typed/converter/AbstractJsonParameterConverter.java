package work.gaigeshen.tripartite.core.parameter.typed.converter;

import work.gaigeshen.tripartite.core.parameter.Parameter;

import java.util.Objects;

/**
 * 这是个抽象的单个请求参数的转换器，具体请查看每个子类的实现，注意继承此类的子类必须提供默认的无参构造方法
 *
 * @author gaigeshen
 */
public abstract class AbstractJsonParameterConverter implements ParameterConverter {

    @Override
    public final Parameter<?> convert(String name, Object rawParameter) throws ParameterConversionException {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (Objects.isNull(rawParameter)) {
            throw new IllegalArgumentException("raw parameter cannot be null");
        }
        return Parameter.string(name, convertJson(rawParameter));
    }

    /**
     * 子类实现此方法用于将原始的对象转换为字符串
     *
     * @param rawParameter 原始的对象不为空
     * @return 转换后的字符串不能为空
     * @throws ParameterConversionException 转换失败的时候抛出此异常
     */
    protected abstract String convertJson(Object rawParameter) throws ParameterConversionException;
}
