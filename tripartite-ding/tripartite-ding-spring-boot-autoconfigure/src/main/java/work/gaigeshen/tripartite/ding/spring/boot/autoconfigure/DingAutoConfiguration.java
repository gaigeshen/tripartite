package work.gaigeshen.tripartite.ding.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.gaigeshen.tripartite.core.client.Client;
import work.gaigeshen.tripartite.core.client.Clients;
import work.gaigeshen.tripartite.core.client.DefaultClients;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenManager;
import work.gaigeshen.tripartite.core.client.accesstoken.AccessTokenStore;
import work.gaigeshen.tripartite.core.client.accesstoken.DefaultAccessTokenManager;
import work.gaigeshen.tripartite.core.client.accesstoken.DefaultAccessTokenStore;
import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentProcessor;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.ding.openapi.client.DingClientCreator;
import work.gaigeshen.tripartite.ding.openapi.client.accesstoken.DingAccessTokenRefresher;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.notify.DingDefaultNotifyContentFilter;
import work.gaigeshen.tripartite.ding.openapi.notify.DingDefaultNotifyContentReceiver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author gaigeshen
 */
@EnableConfigurationProperties({DingProperties.class})
@ConditionalOnClass({Client.class})
@Configuration
public class DingAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DingAutoConfiguration.class);

    private final DingProperties properties;

    private final List<AbstractNotifyContentProcessor<DefaultNotifyContent>> processors;

    public DingAutoConfiguration(DingProperties properties, List<AbstractNotifyContentProcessor<DefaultNotifyContent>> processors) {
        this.properties = properties;
        this.processors = processors;
    }

    @Bean
    public FilterRegistrationBean dingNotifyContentFilter(DingDefaultNotifyContentReceiver receiver) {
        DingDefaultNotifyContentFilter filter = new DingDefaultNotifyContentFilter(receiver);
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setUrlPatterns(Collections.singletonList("/ding-notify-receiver"));
        filterBean.setFilter(filter);
        return filterBean;
    }

    @Bean
    public DingDefaultNotifyContentReceiver dingNotifyContentReceiver(Clients<DingConfig> clients) {
        DingDefaultNotifyContentReceiver receiver = new DingDefaultNotifyContentReceiver(clients);
        receiver.setProcessors(new ArrayList<>(processors));
        return receiver;
    }

    @Bean
    public Clients<DingConfig> dingClients() {
        return new DefaultClients<>(new ArrayList<>(), new DingClientCreator(dingAccessTokenManager()));
    }

    @Bean(destroyMethod = "shutdown")
    public AccessTokenManager<DingConfig> dingAccessTokenManager() {
        return new DefaultAccessTokenManager<>(dingAccessTokenStore(), dingAccessTokenRefresher());
    }

    @Bean
    public DingAccessTokenRefresher dingAccessTokenRefresher() {
        return new DingAccessTokenRefresher(cfg -> dingClients().getClientOrCreate(cfg));
    }

    @Bean
    public AccessTokenStore<DingConfig> dingAccessTokenStore() {
        return new DefaultAccessTokenStore<>();
    }
}
