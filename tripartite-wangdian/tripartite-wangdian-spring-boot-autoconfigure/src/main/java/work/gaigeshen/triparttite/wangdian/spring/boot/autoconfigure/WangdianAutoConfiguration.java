package work.gaigeshen.triparttite.wangdian.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.gaigeshen.triparttite.wangdian.openapi.DefaultWangdianClient;
import work.gaigeshen.triparttite.wangdian.openapi.WangdianClient;
import work.gaigeshen.triparttite.wangdian.openapi.WangdianResponseTypeInterceptor;
import work.gaigeshen.triparttite.wangdian.openapi.config.WangdianConfig;

/**
 *
 * @author gaigeshen
 */
@EnableConfigurationProperties({ WangdianProperties.class })
@ConditionalOnClass({ WangdianClient.class })
@Configuration
public class WangdianAutoConfiguration {

  private static final Logger log = LoggerFactory.getLogger(WangdianAutoConfiguration.class);

  private final WangdianProperties wangdianProperties;

  public WangdianAutoConfiguration(WangdianProperties wangdianProperties) {
    this.wangdianProperties = wangdianProperties;
  }

  @Bean
  public WangdianClient wangdianClient() {
    WangdianConfig.Builder builder = WangdianConfig.builder();

    builder.setServerHost(wangdianProperties.getServerHost());
    builder.setSellerId(wangdianProperties.getSellerId());
    builder.setAppKey(wangdianProperties.getAppKey());
    builder.setAppSecret(wangdianProperties.getAppSecret());

    WangdianConfig config = builder.build();

    log.info("loaded wangdian client: {}", config);

    return DefaultWangdianClient.create(config, new WangdianResponseTypeInterceptor());
  }
}
