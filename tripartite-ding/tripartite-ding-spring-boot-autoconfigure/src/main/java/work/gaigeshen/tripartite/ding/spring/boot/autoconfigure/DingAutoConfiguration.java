package work.gaigeshen.tripartite.ding.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentProcessor;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.ding.openapi.DefaultDingCompositeClient;
import work.gaigeshen.tripartite.ding.openapi.DefaultDingCompositeClients;
import work.gaigeshen.tripartite.ding.openapi.DingCompositeClient;
import work.gaigeshen.tripartite.ding.openapi.DingCompositeClients;
import work.gaigeshen.tripartite.ding.openapi.accesstoken.DefaultDingAccessTokenManager;
import work.gaigeshen.tripartite.ding.openapi.accesstoken.DingAccessTokenManager;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.notify.DingDefaultNotifyContentFilter;
import work.gaigeshen.tripartite.ding.openapi.notify.DingDefaultNotifyContentReceiver;
import work.gaigeshen.tripartite.ding.openapi.robotwebhook.DingRobotWebhook;
import work.gaigeshen.tripartite.ding.openapi.robotwebhook.DingRobotWebhookClient;

import java.util.*;

/**
 *
 * @author gaigeshen
 */
@EnableConfigurationProperties({ DingProperties.class })
@ConditionalOnClass({ DingCompositeClient.class })
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
  public FilterRegistrationBean<DingDefaultNotifyContentFilter> dingNotifyContentFilter(DingDefaultNotifyContentReceiver receiver) {
    DingDefaultNotifyContentFilter filter = new DingDefaultNotifyContentFilter(receiver);
    FilterRegistrationBean<DingDefaultNotifyContentFilter> filterBean = new FilterRegistrationBean<>();
    filterBean.setUrlPatterns(Collections.singletonList("/ding-notify-receiver"));
    filterBean.setFilter(filter);
    return filterBean;
  }

  @Bean
  public DingDefaultNotifyContentReceiver dingNotifyContentReceiver(DingCompositeClients clients) {
    DingDefaultNotifyContentReceiver receiver = new DingDefaultNotifyContentReceiver(clients);
    receiver.setProcessors(new ArrayList<>(processors));
    return receiver;
  }

  @Bean
  public DingCompositeClients dingCompositeClients(DingAccessTokenManager manager) {
    Collection<DingCompositeClient> clients = new ArrayList<>();

    String legacyServerHost = properties.getLegacyServerHost();
    if (!StringUtils.hasText(legacyServerHost)) {
      throw new IllegalStateException("the legacy server host cannot be blank");
    }
    String modernServerHost = properties.getModernServerHost();
    if (!StringUtils.hasText(modernServerHost)) {
      throw new IllegalStateException("the modern server host cannot be blank");
    }

    for (DingProperties.Client client : properties.getClients()) {
      DingConfig.Builder builder = DingConfig.builder();
      builder.setAppKey(client.getAppKey());
      builder.setAppSecret(client.getAppSecret());
      builder.setSecretKey(client.getSecretKey());
      builder.setToken(client.getToken());
      DingConfig config = builder.build();

      clients.add(new DefaultDingCompositeClient(config, manager, legacyServerHost, modernServerHost));

      log.info("loaded ding composite client: {}", config);
    }
    return new DefaultDingCompositeClients(clients);
  }

  @ConditionalOnMissingBean
  @Bean
  public DingAccessTokenManager dingAccessTokenManager() {
    return DefaultDingAccessTokenManager.INSTANCE;
  }

  @Bean
  public DingRobotWebhookClient dingRobotWebhookClient() {
    Set<DingRobotWebhook> robotWebhooks = new HashSet<>();
    for (DingProperties.RobotWebhook robotWebhook : properties.getRobotWebhooks()) {
      DingRobotWebhook webhook = new DingRobotWebhook(robotWebhook.getName(), robotWebhook.getWebhook());
      log.info("created robot webhook object: {}", webhook);
      robotWebhooks.add(webhook);
    }
    return new DingRobotWebhookClient(robotWebhooks);
  }
}
