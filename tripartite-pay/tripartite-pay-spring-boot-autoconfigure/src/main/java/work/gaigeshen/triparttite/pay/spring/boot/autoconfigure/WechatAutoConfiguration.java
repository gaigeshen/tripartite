package work.gaigeshen.triparttite.pay.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import work.gaigeshen.triparttite.core.notify.AbstractNotifyContentProcessor;
import work.gaigeshen.triparttite.pay.wechat.*;
import work.gaigeshen.triparttite.pay.wechat.config.AutoUpdateWechatCertificates;
import work.gaigeshen.triparttite.pay.wechat.config.WechatConfig;
import work.gaigeshen.triparttite.pay.wechat.interceptor.DefaultWechatInterceptor;
import work.gaigeshen.triparttite.pay.wechat.notify.WechatNotifyBody;
import work.gaigeshen.triparttite.pay.wechat.notify.WechatNotifyBodyFilter;
import work.gaigeshen.triparttite.pay.wechat.notify.WechatNotifyBodyReceiver;

import java.util.*;

/**
 *
 * @author gaigeshen
 */
@EnableConfigurationProperties({ WechatProperties.class })
@ConditionalOnClass({ WechatClient.class })
@Configuration
public class WechatAutoConfiguration {

  private static final Logger log = LoggerFactory.getLogger(WechatAutoConfiguration.class);

  private final WechatProperties wechatProperties;

  private final List<AbstractNotifyContentProcessor<WechatNotifyBody>> processors;

  public WechatAutoConfiguration(WechatProperties wechatProperties, List<AbstractNotifyContentProcessor<WechatNotifyBody>> processors) {
    this.wechatProperties = wechatProperties;
    this.processors = processors;
  }

  @Bean
  public FilterRegistrationBean<WechatNotifyBodyFilter> wechatNotifyBodyFilter(WechatNotifyBodyReceiver receiver) {
    WechatNotifyBodyFilter filter = new WechatNotifyBodyFilter(receiver);
    FilterRegistrationBean<WechatNotifyBodyFilter> filterBean = new FilterRegistrationBean<>();
    filterBean.setUrlPatterns(Collections.singletonList("/wechat-notify-receiver/*"));
    filterBean.setFilter(filter);
    return filterBean;
  }

  @Bean
  public WechatNotifyBodyReceiver wechatNotifyBodyReceiver(WechatClients wechatClients) {
    WechatNotifyBodyReceiver receiver = new WechatNotifyBodyReceiver(wechatClients);
    receiver.setProcessors(new ArrayList<>(processors));
    return receiver;
  }

  @Bean
  public WechatClients wechatClients() {
    Collection<WechatClient> wechatClients = new ArrayList<>();

    for (WechatProperties.Client client : wechatProperties.getClients()) {
      WechatConfig.Builder builder = WechatConfig.builder();

      if (!StringUtils.hasText(client.getServerHost())) {
        throw new IllegalStateException("server host cannot be null or empty");
      }
      builder.setServerHost(client.getServerHost());

      if (!StringUtils.hasText(client.getAppId()) || !StringUtils.hasText(client.getMerchantId())) {
        throw new IllegalStateException("appId and merchantId cannot be null or empty");
      }
      builder.setAppId(client.getAppId());
      builder.setMerchantId(client.getMerchantId());

      if (!StringUtils.hasText(client.getNotifyServerHost())) {
        throw new IllegalStateException("notify server host cannot be null or empty");
      }
      builder.setNotifyUrl(client.getNotifyServerHost() + "/wechat-notify-receiver/" + client.getAppId() + "/" + client.getMerchantId());

      WechatProperties.Client.Certificates certificates = client.getCertificates();

      switch (certificates.getType()) {
        case CLASSPATH:
          builder.setSecretKeyClasspath(certificates.getSecretKey());
          builder.setPrivateKeyClasspath(certificates.getPrivateKey(), certificates.getAppCertSerialNumber());
          builder.setCertificateClasspath(certificates.getCertificate());
          break;
        case FILE:
          builder.setSecretKeyFile(certificates.getSecretKey());
          builder.setPrivateKeyFile(certificates.getPrivateKey(), certificates.getAppCertSerialNumber());
          builder.setCertificateFile(certificates.getCertificate());
          break;
        case CONTENT:
          builder.setSecretKeyContent(certificates.getSecretKey());
          builder.setPrivateKeyContent(certificates.getPrivateKey(), certificates.getAppCertSerialNumber());
          builder.setCertificateContent(certificates.getCertificate());
          break;
      }
      WechatConfig wechatConfig = builder.build();

      DefaultWechatClient wechatClient = DefaultWechatClient.create(wechatConfig, new DefaultWechatInterceptor(wechatConfig));
      wechatClients.add(wechatClient);

      Integer updatePeriodSeconds = certificates.getCertificateUpdatePeriodSeconds();
      if (Objects.isNull(updatePeriodSeconds) || updatePeriodSeconds < 0) {
        throw new IllegalStateException("certificate update period seconds cannot be null or less than zero");
      }
      new AutoUpdateWechatCertificates(wechatConfig.getCertificates()).startUpdate(new DefaultWechatCertificatesFetcher(wechatClient), 1, updatePeriodSeconds);

      log.info("loaded wechat client: {}", wechatConfig);
    }

    return new DefaultWechatClients(wechatClients);
  }
}
