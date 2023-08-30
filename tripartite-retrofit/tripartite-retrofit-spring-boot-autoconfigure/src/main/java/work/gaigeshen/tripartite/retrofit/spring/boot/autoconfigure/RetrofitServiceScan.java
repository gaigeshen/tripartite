package work.gaigeshen.tripartite.retrofit.spring.boot.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 用于设置服务接口所在包，默认为此注解标注的类所在的包
 *
 * @author gaigeshen
 */
@Import({RetrofitServiceScannerRegistrar.class})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface RetrofitServiceScan {

    Class<?>[] value() default {};
}
