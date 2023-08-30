package work.gaigeshen.tripartite.retrofit.spring.boot.autoconfigure;

import java.lang.annotation.*;

/**
 * 服务接口注解
 *
 * @author gaigeshen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface RetrofitService {

}
