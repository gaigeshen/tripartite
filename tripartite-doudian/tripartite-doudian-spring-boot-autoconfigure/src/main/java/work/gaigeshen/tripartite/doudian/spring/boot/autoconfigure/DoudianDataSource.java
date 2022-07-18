package work.gaigeshen.tripartite.doudian.spring.boot.autoconfigure;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * 当需要明确指定抖店数据源的时候需要用到此注解，否则默认使用系统中已经存在的候选数据源
 *
 * @author gaigeshen
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier
public @interface DoudianDataSource {
}
