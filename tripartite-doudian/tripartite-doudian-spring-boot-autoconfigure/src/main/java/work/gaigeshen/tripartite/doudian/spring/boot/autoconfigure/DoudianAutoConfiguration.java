package work.gaigeshen.tripartite.doudian.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.gaigeshen.tripartite.doudian.openapi.DoudianClient;

/**
 *
 * @author gaigeshen
 */
@EnableConfigurationProperties({ DoudianProperties.class })
@ConditionalOnClass({ DoudianClient.class })
@Configuration
public class DoudianAutoConfiguration {

  private static final Logger log = LoggerFactory.getLogger(DoudianAutoConfiguration.class);

  private final DoudianProperties doudianProperties;

  public DoudianAutoConfiguration(DoudianProperties doudianProperties) {
    this.doudianProperties = doudianProperties;
  }

  @Bean
  public DoudianClient doudianClient() {
    return null;
  }
}
