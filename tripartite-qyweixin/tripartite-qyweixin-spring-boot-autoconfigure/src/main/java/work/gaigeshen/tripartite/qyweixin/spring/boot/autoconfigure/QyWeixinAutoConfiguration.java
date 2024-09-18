package work.gaigeshen.tripartite.qyweixin.spring.boot.autoconfigure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import work.gaigeshen.tripartite.core.client.*;
import work.gaigeshen.tripartite.core.client.accesstoken.*;
import work.gaigeshen.tripartite.core.client.config.ConfigRepository;
import work.gaigeshen.tripartite.qyweixin.openapi.client.DefaultQyWeixinClient;
import work.gaigeshen.tripartite.qyweixin.openapi.client.QyWeixinClient;
import work.gaigeshen.tripartite.qyweixin.openapi.client.QyWexinClientCreator;
import work.gaigeshen.tripartite.qyweixin.openapi.config.QyWeixinConfig;

import java.util.ArrayList;
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

    @Bean
    public ClientsLoader<QyWeixinConfig> qyWeixinClientsLoader() {
        return new DefaultClientsLoader<>(dingClients(), qyWeixinConfigRepository()).load();
    }

    @Bean
    public Clients<QyWeixinConfig> dingClients() {
        ClientCreator<QyWeixinConfig> dingClientCreator = new QyWexinClientCreator(qyWeixinAccessTokenManager());
        List<Client<QyWeixinConfig>> dingClients = new ArrayList<>();
        for (QyWeixinProperties.Client client : qyWeixinProperties.getClients()) {
            if (!StringUtils.hasText(client.getServerHost())) {
                throw new IllegalStateException("serverHost cannot be blank");
            }
            if (!StringUtils.hasText(client.getCorpId()) || !StringUtils.hasText(client.getCorpSecret())) {
                throw new IllegalStateException("corpId and corpSecret cannot be blank");
            }
            QyWeixinConfig dingConfig = QyWeixinConfig.builder()
                    .setServerHost(client.getServerHost())
                    .setCorpId(client.getCorpId())
                    .setCorpSecret(client.getCorpSecret())
                    .build();
            dingClients.add(dingClientCreator.create(dingConfig));
            log.info("loaded qyweixin client: {}", dingConfig);
        }
        return new DefaultClients<>(dingClients, dingClientCreator);
    }

    @Bean(destroyMethod = "shutdown")
    public AccessTokenManager<QyWeixinConfig> qyWeixinAccessTokenManager() {
        return new DefaultAccessTokenManager<>(qyWeixinAccessTokenStore(), qyWeixinAccessTokenRefresher());
    }

    @Bean
    public AccessTokenRefresher<QyWeixinConfig> qyWeixinAccessTokenRefresher() {
        return (config, oat) -> {
            try {
                DefaultQyWeixinClient qyWeixinClient = (DefaultQyWeixinClient) dingClients().getClientOrCreate(config);
                return qyWeixinClient.getNewAccessToken();
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
    public ConfigRepository<QyWeixinConfig> qyWeixinConfigRepository() {
        return new ConfigRepository<QyWeixinConfig>() {};
    }
}
