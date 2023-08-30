package work.gaigeshen.tripartite.retrofit.spring.boot.autoconfigure;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import work.gaigeshen.tripartite.retrofit.core.server.*;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author gaigeshen
 */
@EnableConfigurationProperties(RetrofitProperties.class)
@Configuration
public class RetrofitConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RetrofitConfiguration.class);

    private final RetrofitProperties retrofitProperties;

    public RetrofitConfiguration(RetrofitProperties retrofitProperties) {
        this.retrofitProperties = retrofitProperties;
    }

    @ConditionalOnMissingBean
    @Bean
    public ServerHostSelector serverHostSelector() {
        return new DefaultServerHostSelector();
    }

    @Bean
    public Retrofit retrofit(ServerHostSelector serverHostSelector) {
        List<ServerHost> serverHostList = new ArrayList<>();
        for (RetrofitProperties.ServerHost serverHost : retrofitProperties.getServerHosts()) {
            serverHostList.add(ServerHost.create(serverHost.getServerId(), serverHost.getServerHost()));
        }
        ServerHosts serverHosts = DefaultServerHosts.create(serverHostList);
        ServerHost defaultServerHost = serverHosts.getServerHost();
        if (Objects.isNull(defaultServerHost)) {
            throw new IllegalStateException("please configure default server host");
        }
        OkHttpClient httpClient = createHttpClient(serverHosts, serverHostSelector);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Converter.Factory jacksonConverterFactory = JacksonConverterFactory.create(objectMapper);

        return new Retrofit.Builder()
                .client(httpClient).baseUrl(defaultServerHost.getServerHost())
                .addConverterFactory(jacksonConverterFactory)
                .build();
    }

    private OkHttpClient createHttpClient(ServerHosts serverHosts, ServerHostSelector serverHostSelector) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        if (retrofitProperties.getConnectTimeout() > 0) {
            httpClientBuilder.connectTimeout(Duration.ofMillis(retrofitProperties.getConnectTimeout()));
        }
        if (retrofitProperties.getReadTimeout() > 0) {
            httpClientBuilder.readTimeout(Duration.ofMillis(retrofitProperties.getReadTimeout()));
        }

        Interceptor serverHostInterceptor = new ServerHostInterceptor(serverHosts, serverHostSelector);
        httpClientBuilder.addInterceptor(serverHostInterceptor);

        return httpClientBuilder.build();
    }

    /**
     * @author gaigeshen
     */
    private static class ServerHostInterceptor implements Interceptor {

        private final ServerHosts serverHosts;

        private final ServerHostSelector serverHostSelector;

        private ServerHostInterceptor(ServerHosts serverHosts, ServerHostSelector serverHostSelector) {
            this.serverHosts = serverHosts;
            this.serverHostSelector = serverHostSelector;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request userRequest = chain.request();

            ServerHost serverHostSelected = serverHostSelector.select(serverHosts, userRequest);

            if (Objects.nonNull(serverHostSelected)) {
                HttpUrl serverHostUrl = HttpUrl.get(serverHostSelected.getServerHost());

                HttpUrl newHttpUrl = userRequest.url().newBuilder()
                        .scheme(serverHostUrl.scheme())
                        .host(serverHostUrl.host()).port(serverHostUrl.port())
                        .build();

                Request newRequest = userRequest.newBuilder().url(newHttpUrl).build();

                return chain.proceed(newRequest);
            }
            return chain.proceed(userRequest);
        }
    }

    /**
     * 基于自动配置的情况下扫描服务接口
     *
     * @author gaigeshen
     */
    public static class AutoConfiguredRetrofitServiceScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, BeanFactoryAware {

        private ResourceLoader resourceLoader;

        private BeanFactory beanFactory;

        @Override
        public void setResourceLoader(ResourceLoader resourceLoader) {
            this.resourceLoader = resourceLoader;
        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            this.beanFactory = beanFactory;
        }

        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

            log.debug("searching for retrofit services annotated with @RetrofitService");

            List<String> packagesToScan = AutoConfigurationPackages.get(beanFactory);

            if (log.isDebugEnabled()) {
                for (String pkg : packagesToScan) {
                    log.debug("using auto-configuration base package '{}'", pkg);
                }
            }
            ClassPathRetrofitServiceScanner scanner = new ClassPathRetrofitServiceScanner(registry, RetrofitService.class);
            scanner.setResourceLoader(resourceLoader);
            scanner.scan(packagesToScan.toArray(new String[0]));
        }
    }

    /**
     * 在缺失服务接口扫描注解的情况启用自动配置扫描
     *
     * @author gaigeshen
     */
    @ConditionalOnMissingBean(RetrofitServiceFactoryBean.class)
    @Import(AutoConfiguredRetrofitServiceScannerRegistrar.class)
    @Configuration
    public static class RetrofitServiceScannerRegistrarNotFoundConfiguration { }
}
