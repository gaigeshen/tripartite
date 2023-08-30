package work.gaigeshen.tripartite.retrofit.spring.boot.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 基于注解开启扫描所有的服务接口
 *
 * @author gaigeshen
 */
public class RetrofitServiceScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(RetrofitServiceScan.class.getName()));
        if (Objects.nonNull(attributes)) {
            Set<String> packagesToScan = new HashSet<>();
            Class<?>[] basePackageClasses = attributes.getClassArray("value");
            for (Class<?> basePackageClass : basePackageClasses) {
                packagesToScan.add(ClassUtils.getPackageName(basePackageClass));
            }
            if (packagesToScan.isEmpty()) {
                String defaultPackage = ClassUtils.getPackageName(metadata.getClassName());
                packagesToScan.add(defaultPackage);
            }
            ClassPathRetrofitServiceScanner scanner = new ClassPathRetrofitServiceScanner(registry, RetrofitService.class);
            scanner.setResourceLoader(resourceLoader);
            scanner.scan(packagesToScan.toArray(new String[0]));
        }
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
