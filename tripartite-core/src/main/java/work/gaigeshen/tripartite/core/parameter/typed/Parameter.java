package work.gaigeshen.tripartite.core.parameter.typed;

import work.gaigeshen.tripartite.core.parameter.typed.converter.DefaultParameterConverter;
import work.gaigeshen.tripartite.core.parameter.typed.converter.ParameterConverter;

import java.lang.annotation.*;

/**
 * 这个注解可以使用在任意类型的字段上，用于指定某个参数的名称和对应的转换器
 *
 * @author gaigeshen
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Parameter {
    /**
     * 指定参数名称，如不指定或指定了空字符串则会取字段名称
     *
     * @return 参数名称
     */
    String name() default "";

    /**
     * 指定参数转换器用于转换对应字段的值为单个请求参数，默认使用默认的参数转换器
     *
     * @return 参数转换器
     */
    Class<? extends ParameterConverter> converter() default DefaultParameterConverter.class;
}
