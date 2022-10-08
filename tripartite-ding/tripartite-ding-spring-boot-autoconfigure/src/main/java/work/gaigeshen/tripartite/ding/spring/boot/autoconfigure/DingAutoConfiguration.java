package work.gaigeshen.tripartite.ding.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentProcessor;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.ding.openapi.DingClient;
import work.gaigeshen.tripartite.ding.openapi.DingClients;
import work.gaigeshen.tripartite.ding.openapi.accesstoken.*;
import work.gaigeshen.tripartite.ding.openapi.notify.DingDefaultNotifyContentFilter;
import work.gaigeshen.tripartite.ding.openapi.notify.DingDefaultNotifyContentReceiver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author gaigeshen
 */
@EnableConfigurationProperties({DingProperties.class})
@ConditionalOnClass({DingClient.class})
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
    public DingDefaultNotifyContentReceiver dingNotifyContentReceiver(DingClients clients) {
        DingDefaultNotifyContentReceiver receiver = new DingDefaultNotifyContentReceiver(clients);
        receiver.setProcessors(new ArrayList<>(processors));
        return receiver;
    }

    @Bean
    public DingClients dingClients() {
        return null;
    }

    @Bean(destroyMethod = "shutdown")
    public DingAccessTokenManager dingAccessTokenManager() {
        return null;
    }

    @Bean
    public DingAccessTokenRefresher dingAccessTokenRefresher() {
        return new DefaultDingAccessTokenRefresher((cfg, oat) -> dingClients().getClientOrCreate(cfg));
    }

    @Bean
    public DingAccessTokenStore dingAccessTokenStore() {
        return new DefaultDingAccessTokenStore();
    }
}
