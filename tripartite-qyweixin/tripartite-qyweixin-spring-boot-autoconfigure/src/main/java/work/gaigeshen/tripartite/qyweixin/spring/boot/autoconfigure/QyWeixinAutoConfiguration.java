package work.gaigeshen.tripartite.qyweixin.spring.boot.autoconfigure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.gaigeshen.tripartite.core.client.*;
import work.gaigeshen.tripartite.core.client.accesstoken.*;
import work.gaigeshen.tripartite.core.client.config.ConfigRepository;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.core.notify.NotifyContentProcessor;
import work.gaigeshen.tripartite.qyweixin.openapi.client.DefaultQyWeixinClient;
import work.gaigeshen.tripartite.qyweixin.openapi.client.QyWeixinClient;
import work.gaigeshen.tripartite.qyweixin.openapi.client.QyWeixinClientCreator;
import work.gaigeshen.tripartite.qyweixin.openapi.config.QyWeixinConfig;
import work.gaigeshen.tripartite.qyweixin.openapi.notify.QyWeixinNotifyContentFilter;
import work.gaigeshen.tripartite.qyweixin.openapi.notify.QyWeixinNotifyContentReceiver;
import work.gaigeshen.tripartite.qyweixin.openapi.notify.message.notify.QyWeixinMessageNotifyContentProcessor;

import java.util.*;

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

    @Bean
    public QyWeixinNotifyContentReceiver qyWeixinNotifyContentReceiver(Clients<QyWeixinConfig> clients,
                                                                       List<QyWeixinMessageNotifyContentProcessor> processors) {
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
        List<Client<QyWeixinConfig>> qyWeixinClients = new ArrayList<>();
        for (QyWeixinProperties.Client client : qyWeixinProperties.getClients()) {
            if (StringUtils.isAnyBlank(client.getServerHost(), client.getCorpId(), client.getCorpSecret())) {
                throw new IllegalStateException("serverHost, corpId and corpSecret cannot be blank");
            }
            if (Objects.isNull(client.getAgentId())) {
                throw new IllegalStateException("agentId cannot be null");
            }
            if (StringUtils.isAnyBlank(client.getToken(), client.getAesKey())) {
                throw new IllegalStateException("token and aesKey cannot be blank");
            }
            QyWeixinConfig qyWeixinConfig = QyWeixinConfig.builder()
                    .setServerHost(client.getServerHost()).setCorpId(client.getCorpId())
                    .setCorpSecret(client.getCorpSecret())
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

    @ConditionalOnMissingBean(name = "qyWeixinAccessTokenStore")
    @Bean
    public AccessTokenStore<QyWeixinConfig> qyWeixinAccessTokenStore() {
        return new DefaultAccessTokenStore<>();
    }

    @ConditionalOnMissingBean(name = "qyWeixinConfigRepository")
    @Bean
    public ConfigRepository<QyWeixinConfig> qyWeixinConfigRepository() {
        return new ConfigRepository<QyWeixinConfig>() {};
    }

    @ConditionalOnMissingBean(QyWeixinMessageNotifyContentProcessor.class)
    @Configuration
    static class QyWeixinMessageNotifyContentProcessorConfiguration {

        @Bean
        public QyWeixinMessageNotifyContentProcessor qyWeixinMessageNotifyContentProcessor() {
            return new QyWeixinMessageNotifyContentProcessor() {
                @Override
                protected boolean supportsMessageContent(Map<?, ?> messageContent) {
                    return true;
                }

                @Override
                protected void processMessageContent(Map<?, ?> eventContent,
                                                     DefaultNotifyContent content,
                                                     NotifyContentProcessor.ProcessorChain<DefaultNotifyContent> chain) {
                    log.info("<<<< Message Content: {}", eventContent);
                }
            };
        }
    }
}
