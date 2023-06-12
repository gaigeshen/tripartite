package work.gaigeshen.tripartite.openai.spring.boot.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import work.gaigeshen.tripartite.openai.openapi.client.OpenAiClient;

@EnableConfigurationProperties({OpenAiProperties.class})
@ConditionalOnClass({OpenAiClient.class})
@Configuration
public class OpenAiAutoConfiguration {
}
