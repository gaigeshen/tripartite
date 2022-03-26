package work.gaigeshen.tripartite.pay.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentProcessor;
import work.gaigeshen.tripartite.pay.alipay.*;
import work.gaigeshen.tripartite.pay.alipay.config.AlipayConfig;
import work.gaigeshen.tripartite.pay.alipay.notify.AlipayNotifyParameters;
import work.gaigeshen.tripartite.pay.alipay.notify.AlipayNotifyParametersFilter;
import work.gaigeshen.tripartite.pay.alipay.notify.AlipayNotifyParametersReceiver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author gaigeshen
 */
@EnableConfigurationProperties({ AlipayProperties.class })
@ConditionalOnClass({ AlipayClient.class })
@Configuration
public class AlipayAutoConfiguration {

  private static final Logger log = LoggerFactory.getLogger(AlipayAutoConfiguration.class);

  private final AlipayProperties alipayProperties;

  private final List<AbstractNotifyContentProcessor<AlipayNotifyParameters>> processors;

  public AlipayAutoConfiguration(AlipayProperties alipayProperties, List<AbstractNotifyContentProcessor<AlipayNotifyParameters>> processors) {
    this.alipayProperties = alipayProperties;
    this.processors = processors;
  }

  @Bean
  public FilterRegistrationBean<AlipayNotifyParametersFilter> alipayNotifyParametersFilter(AlipayNotifyParametersReceiver receiver) {
    AlipayNotifyParametersFilter filter = new AlipayNotifyParametersFilter(receiver);
    FilterRegistrationBean<AlipayNotifyParametersFilter> filterBean = new FilterRegistrationBean<>();
    filterBean.setUrlPatterns(Collections.singletonList("/alipay-notify-receiver"));
    filterBean.setFilter(filter);
    return filterBean;
  }

  @Bean
  public AlipayNotifyParametersReceiver alipayNotifyParametersReceiver(AlipayClients alipayClients) {
    AlipayNotifyParametersReceiver receiver = new AlipayNotifyParametersReceiver(alipayClients);
    receiver.setProcessors(new ArrayList<>(processors));
    return receiver;
  }

  @Bean
  public AlipayClients alipayClients() {

    Collection<AlipayClient> alipayClients = new ArrayList<>();

    for (AlipayProperties.Client client : alipayProperties.getClients()) {
      AlipayConfig.Builder builder = AlipayConfig.builder();

      if (!StringUtils.hasText(client.getAppId()) || !StringUtils.hasText(client.getServerUrl())) {
        throw new IllegalStateException("appId and server url cannot be null or empty");
      }
      builder.setAppId(client.getAppId());
      builder.setServerUrl(client.getServerUrl());

      if (!StringUtils.hasText(client.getNotifyServerHost())) {
        throw new IllegalStateException("notify server host cannot be null or empty");
      }
      builder.setNotifyUrl(client.getNotifyServerHost() + "/alipay-notify-receiver");

      AlipayProperties.Client.Certificates certificates = client.getCertificates();
      switch (certificates.getType()) {
        case CLASSPATH:
          builder.setPrivateKeyClasspath(certificates.getAppPrivateKey(), certificates.getAppCertificate());
          builder.setRootCertificateClasspath(certificates.getRootCertificate());
          builder.setCertificateClasspath(certificates.getCertificate());
          break;
        case FILE:
          builder.setPrivateKeyFile(certificates.getAppPrivateKey(), certificates.getAppCertificate());
          builder.setRootCertificateFile(certificates.getRootCertificate());
          builder.setCertificateFile(certificates.getCertificate());
          break;
        case CONTENT:
          builder.setPrivateKeyContent(certificates.getAppPrivateKey(), certificates.getAppCertificate());
          builder.setRootCertificateContent(certificates.getRootCertificate());
          builder.setCertificateContent(certificates.getCertificate());
          break;
      }
      AlipayConfig alipayConfig = builder.build();

      alipayClients.add(DefaultAlipayClient.create(alipayConfig, new AlipayResponseInterceptor(alipayConfig)));

      log.info("loaded alipay client: {}", alipayConfig);
    }
    return new DefaultAlipayClients(alipayClients);
  }


}
