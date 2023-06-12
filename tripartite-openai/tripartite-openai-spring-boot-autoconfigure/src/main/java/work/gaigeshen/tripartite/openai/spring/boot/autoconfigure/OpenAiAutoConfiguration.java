package work.gaigeshen.tripartite.openai.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import work.gaigeshen.tripartite.core.client.*;
import work.gaigeshen.tripartite.core.client.config.ConfigRepository;
import work.gaigeshen.tripartite.openai.openapi.client.OpenAiClient;
import work.gaigeshen.tripartite.openai.openapi.client.OpenAiClientCreator;
import work.gaigeshen.tripartite.openai.openapi.config.OpenAiConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaigeshen
 */
@EnableConfigurationProperties({OpenAiProperties.class})
@ConditionalOnClass({OpenAiClient.class})
@Configuration
public class OpenAiAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OpenAiAutoConfiguration.class);

    private final OpenAiProperties openAiProperties;

    public OpenAiAutoConfiguration(OpenAiProperties openAiProperties) {
        this.openAiProperties = openAiProperties;
    }

    @Bean
    public ClientsLoader<OpenAiConfig> openAiClientsLoader() {
        return new DefaultClientsLoader<>(openAiClients(), openAiConfigRepository()).load();
    }

    @Bean
    public Clients<OpenAiConfig> openAiClients() {
        ClientCreator<OpenAiConfig> openAiClientCreator = new OpenAiClientCreator();
        List<Client<OpenAiConfig>> openAiClients = new ArrayList<>();
        for (OpenAiProperties.Client client : openAiProperties.getClients()) {
            if (!StringUtils.hasText(client.getServerHost())) {
                throw new IllegalStateException("serverHost cannot be blank");
            }
            if (!StringUtils.hasText(client.getApiKey()) || !StringUtils.hasText(client.getOrganization())) {
                throw new IllegalStateException("apiKey and organization cannot be blank");
            }
            OpenAiConfig openAiConfig = new OpenAiConfig(client.getServerHost(), client.getApiKey(), client.getOrganization());
            openAiClients.add(openAiClientCreator.create(openAiConfig));
            log.info("loaded openai client: {}", openAiConfig);
        }
        return new DefaultClients<>(openAiClients, openAiClientCreator);
    }

    @ConditionalOnMissingBean
    @Bean
    public ConfigRepository<OpenAiConfig> openAiConfigRepository() {
        return new ConfigRepository<OpenAiConfig>() {};
    }
}
