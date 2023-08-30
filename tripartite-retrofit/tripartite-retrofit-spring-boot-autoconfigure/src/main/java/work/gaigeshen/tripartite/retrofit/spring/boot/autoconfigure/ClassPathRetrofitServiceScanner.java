package work.gaigeshen.tripartite.retrofit.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/**
 * 扫描所有的服务接口
 *
 * @author gaigeshen
 */
public class ClassPathRetrofitServiceScanner extends ClassPathBeanDefinitionScanner {

    private static final Logger log = LoggerFactory.getLogger(ClassPathRetrofitServiceScanner.class);

    public ClassPathRetrofitServiceScanner(BeanDefinitionRegistry registry, Class<? extends Annotation> annotationClass) {
        super(registry, false);
        addIncludeFilter(new AnnotationTypeFilter(annotationClass));
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        if (beanDefinitionHolders.isEmpty()) {
            log.warn("No retrofit service interface was found in " + Arrays.toString(basePackages));
        } else {
            for (BeanDefinitionHolder holder : beanDefinitionHolders) {
                AbstractBeanDefinition definition = (AbstractBeanDefinition) holder.getBeanDefinition();
                String beanClassName = definition.getBeanClassName();
                if (Objects.isNull(beanClassName)) {
                    continue;
                }
                definition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
                definition.setBeanClass(RetrofitServiceFactoryBean.class);
                log.info("Retrofit service bean with name [" + holder.getBeanName() + "]");
            }
        }
        return beanDefinitionHolders;
    }
}
