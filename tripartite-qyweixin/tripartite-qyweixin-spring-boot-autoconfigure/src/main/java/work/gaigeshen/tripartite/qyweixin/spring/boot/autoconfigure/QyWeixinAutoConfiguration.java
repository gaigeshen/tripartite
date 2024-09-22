package work.gaigeshen.tripartite.qyweixin.spring.boot.autoconfigure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.gaigeshen.tripartite.core.client.*;
import work.gaigeshen.tripartite.core.client.accesstoken.*;
import work.gaigeshen.tripartite.core.client.config.ConfigRepository;
import work.gaigeshen.tripartite.qyweixin.openapi.client.*;
import work.gaigeshen.tripartite.qyweixin.openapi.config.QyWeixinConfig;
import work.gaigeshen.tripartite.qyweixin.openapi.notify.QyWeixinNotifyContentFilter;
import work.gaigeshen.tripartite.qyweixin.openapi.notify.QyWeixinNotifyContentReceiver;
import work.gaigeshen.tripartite.qyweixin.openapi.notify.message.notify.QyWeixinMessageNotifyContentProcessor;
import work.gaigeshen.tripartite.qyweixin.openapi.notify.message.notify.QyWeixinSuiteTicketMessageNotifyContentProcessor;
import work.gaigeshen.tripartite.qyweixin.openapi.suite.DefaultQyWeixinSuiteTicketStore;
import work.gaigeshen.tripartite.qyweixin.openapi.suite.QyWeixinSuiteTicketStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 企业微信自动配置
 *
 * @author gaigeshen
 */
@EnableConfigurationProperties({QyWeixinProperties.class})
@ConditionalOnClass({QyWeixinClient.class})
@Configuration
@RequiredArgsConstructor
@Slf4j
public class QyWeixinAutoConfiguration {

    private final QyWeixinProperties qyWeixinProperties;

    private final List<QyWeixinMessageNotifyContentProcessor> processors;

    @Bean
    public QyWeixinNotifyContentReceiver qyWeixinNotifyContentReceiver(Clients<QyWeixinConfig> clients) {
        QyWeixinNotifyContentReceiver receiver = new QyWeixinNotifyContentReceiver(clients);
        receiver.setProcessors(new ArrayList<>(processors));
        return receiver;
    }

    @Bean
    public FilterRegistrationBean qyWeixinNotifyContentFilter(QyWeixinNotifyContentReceiver receiver) {
        QyWeixinNotifyContentFilter filter = new QyWeixinNotifyContentFilter(receiver);
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setUrlPatterns(Collections.singletonList("/qyweixin-notify-receiver"));
        filterBean.setFilter(filter);
        return filterBean;
    }

    @Bean
    public ClientsLoader<QyWeixinConfig> qyWeixinClientsLoader() {
        return new DefaultClientsLoader<>(qyWeixinClients(), qyWeixinConfigRepository()).load();
    }

    @Bean
    public Clients<QyWeixinConfig> qyWeixinClients() {
        ClientCreator<QyWeixinConfig> qyWeixinClientCreator = new QyWeixinClientCreator(qyWeixinAccessTokenManager());
        if (BooleanUtils.isTrue(qyWeixinProperties.getProvider())) {
            qyWeixinClientCreator = new QyWeixinProviderClientCreator(qyWeixinAccessTokenManager(),
                    qyWeixinSuiteAccessTokenManager(), qyWeixinSuiteTicketStore());
        }
        List<Client<QyWeixinConfig>> qyWeixinClients = new ArrayList<>();
        for (QyWeixinProperties.Client client : qyWeixinProperties.getClients()) {
            QyWeixinConfig qyWeixinConfig = QyWeixinConfig.builder()
                    .setServerHost(client.getServerHost()).setCorpId(client.getCorpId())
                    .setCorpSecret(client.getCorpSecret()).setProviderSecret(client.getProviderSecret())
                    .setSuiteId(client.getSuiteId()).setSuiteSecret(client.getSuiteSecret())
                    .setAgentId(client.getAgentId())
                    .setToken(client.getToken()).setAesKey(client.getAesKey())
                    .build();
            qyWeixinClients.add(qyWeixinClientCreator.create(qyWeixinConfig));
            log.info("loaded qyweixin client: {}", qyWeixinConfig);
        }
        return new DefaultClients<>(qyWeixinClients, qyWeixinClientCreator);
    }

    @Bean(destroyMethod = "shutdown")
    public AccessTokenManager<QyWeixinConfig> qyWeixinAccessTokenManager() {
        return new DefaultAccessTokenManager<>(qyWeixinAccessTokenStore(), qyWeixinAccessTokenRefresher());
    }

    @Bean(destroyMethod = "shutdown")
    public AccessTokenManager<QyWeixinConfig> qyWeixinSuiteAccessTokenManager() {
        return new DefaultAccessTokenManager<>(qyWeixinSuiteAccessTokenStore(), qyWeixinSuiteAccessTokenRefresher());
    }

    @Bean
    public AccessTokenRefresher<QyWeixinConfig> qyWeixinAccessTokenRefresher() {
        return (config, oat) -> {
            try {
                DefaultQyWeixinClient qyWeixinClient = (DefaultQyWeixinClient) qyWeixinClients().getClientOrCreate(config);
                return qyWeixinClient.getNewAccessToken();
            } catch (ClientException e) {
                throw new AccessTokenRefreshException(e.getMessage(), e).setCurrentAccessToken(oat).setCanRetry(true);
            }
        };
    }

    @Bean
    public AccessTokenRefresher<QyWeixinConfig> qyWeixinSuiteAccessTokenRefresher() {
        return (config, oat) -> {
            try {
                DefaultQyWeixinProviderClient qyWeixinClient = (DefaultQyWeixinProviderClient) qyWeixinClients().getClientOrCreate(config);
                return qyWeixinClient.getNewSuiteAccessToken();
            } catch (ClientException e) {
                throw new AccessTokenRefreshException(e.getMessage(), e).setCurrentAccessToken(oat).setCanRetry(true);
            }
        };
    }

    @ConditionalOnMissingBean
    @Bean
    public AccessTokenStore<QyWeixinConfig> qyWeixinAccessTokenStore() {
        return new DefaultAccessTokenStore<>();
    }

    @ConditionalOnMissingBean
    @Bean
    public AccessTokenStore<QyWeixinConfig> qyWeixinSuiteAccessTokenStore() {
        return new DefaultAccessTokenStore<>();
    }

    @ConditionalOnMissingBean
    @Bean
    public QyWeixinSuiteTicketStore<QyWeixinConfig> qyWeixinSuiteTicketStore() {
        return new DefaultQyWeixinSuiteTicketStore<>();
    }

    @ConditionalOnMissingBean
    @Bean
    public ConfigRepository<QyWeixinConfig> qyWeixinConfigRepository() {
        return new ConfigRepository<QyWeixinConfig>() {};
    }

    @Bean
    public QyWeixinSuiteTicketMessageNotifyContentProcessor qyWeixinSuiteTicketMessageNotifyContentProcessor() {
        return new QyWeixinSuiteTicketMessageNotifyContentProcessor(qyWeixinClients());
    }
}
