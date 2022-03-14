package work.gaigeshen.triparttite.core.parameter.converter;

import java.lang.annotation.*;

/**
 * 此注解使用在请求参数类上
 *
 * @author gaigeshen
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Parameters {

  /**
   * 指定请求参数转换器用于将原始的请求参数对象转换为指定类型的请求参数对象，该转换器必须有默认的无参构造方法
   *
   * @return 请求参数转换器类型
   */
  Class<? extends ParametersConverter> converter() default ParametersParametersConverter.class;

  /**
   * 指定请求参数对象自定义器，该自定义器必须有默认的无参构造方法
   *
   * @return 请求参数对象自定义器
   */
  Class<? extends ParametersCustomizer> customizer() default DefaultParametersCustomizer.class;
}
