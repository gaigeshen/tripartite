package work.gaigeshen.tripartite.ding.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import work.gaigeshen.tripartite.core.client.*;
import work.gaigeshen.tripartite.core.client.accesstoken.*;
import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentProcessor;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.ding.openapi.client.DefaultDingClient;
import work.gaigeshen.tripartite.ding.openapi.client.DingClient;
import work.gaigeshen.tripartite.ding.openapi.client.DingClientCreator;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.notify.DingNotifyContentFilter;
import work.gaigeshen.tripartite.ding.openapi.notify.DingNotifyContentReceiver;
import work.gaigeshen.tripartite.ding.openapi.notify.event.DingCheckCreateSuiteUrlNotifyContentProcessor;
import work.gaigeshen.tripartite.ding.openapi.notify.event.DingCheckUpdateSuiteUrlNotifyContentProcessor;
import work.gaigeshen.tripartite.ding.openapi.notify.event.DingCheckUrlNotifyContentProcessor;

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

    private final DingProperties dingProperties;

    private final List<AbstractNotifyContentProcessor<DefaultNotifyContent>> processors;

    public DingAutoConfiguration(DingProperties dingProperties, List<AbstractNotifyContentProcessor<DefaultNotifyContent>> processors) {
        this.dingProperties = dingProperties;
        this.processors = processors;
    }

    @Bean
    public FilterRegistrationBean dingNotifyContentFilter(DingNotifyContentReceiver receiver) {
        DingNotifyContentFilter filter = new DingNotifyContentFilter(receiver);
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setUrlPatterns(Collections.singletonList("/ding-notify-receiver"));
        filterBean.setFilter(filter);
        return filterBean;
    }

    @Bean
    public DingNotifyContentReceiver dingNotifyContentReceiver(Clients<DingConfig> clients) {
        DingNotifyContentReceiver receiver = new DingNotifyContentReceiver(clients);
        receiver.setProcessors(new ArrayList<>(processors));
        return receiver;
    }

    @Bean
    public Clients<DingConfig> dingClients() {
        ClientCreator<DingConfig> dingClientCreator = new DingClientCreator(dingAccessTokenManager());
        List<Client<DingConfig>> dingClients = new ArrayList<>();
        for (DingProperties.Client client : dingProperties.getClients()) {
            if (!StringUtils.hasText(client.getApiServerHost()) || !StringUtils.hasText(client.getOapiServerHost())) {
                throw new IllegalStateException("apiServerHost and oapiServerHost cannot be blank");
            }
            if (!StringUtils.hasText(client.getAppKey()) || !StringUtils.hasText(client.getAppSecret())) {
                throw new IllegalStateException("appKey and appSecret cannot be blank");
            }
            DingConfig dingConfig = DingConfig.builder()
                    .setApiServerHost(client.getApiServerHost()).setOapiServerHost(client.getOapiServerHost())
                    .setAppKey(client.getAppKey()).setAppSecret(client.getAppSecret())
                    .setSecretKey(client.getSecretKey()).setToken(client.getToken())
                    .build();
            dingClients.add(dingClientCreator.create(dingConfig));
            log.info("loaded ding client: {}", dingConfig);
        }
        return new DefaultClients<>(dingClients, dingClientCreator);
    }

    @Bean(destroyMethod = "shutdown")
    public AccessTokenManager<DingConfig> dingAccessTokenManager() {
        return new DefaultAccessTokenManager<>(dingAccessTokenStore(), dingAccessTokenRefresher());
    }

    @Bean
    public AccessTokenStore<DingConfig> dingAccessTokenStore() {
        return new DefaultAccessTokenStore<>();
    }

    @Bean
    public AccessTokenRefresher<DingConfig> dingAccessTokenRefresher() {
        return (config, oat) -> {
            try {
                DefaultDingClient dingClient = (DefaultDingClient) dingClients().getClientOrCreate(config);
                return dingClient.getNewAccessToken();
            } catch (ClientException e) {
                throw new AccessTokenRefreshException(e.getMessage(), e).setCurrentAccessToken(oat).setCanRetry(true);
            }
        };
    }

    @Configuration
    static class DingEventNotifyContentProcessorConfiguration {

        @Bean
        public DingCheckUrlNotifyContentProcessor dingCheckUrlNotifyContentProcessor() {
            return new DingCheckUrlNotifyContentProcessor();
        }

        @Bean
        public DingCheckCreateSuiteUrlNotifyContentProcessor dingCheckCreateSuiteUrlNotifyContentProcessor() {
            return new DingCheckCreateSuiteUrlNotifyContentProcessor();
        }

        @Bean
        public DingCheckUpdateSuiteUrlNotifyContentProcessor dingCheckUpdateSuiteUrlNotifyContentProcessor() {
            return new DingCheckUpdateSuiteUrlNotifyContentProcessor();
        }
    }
}
