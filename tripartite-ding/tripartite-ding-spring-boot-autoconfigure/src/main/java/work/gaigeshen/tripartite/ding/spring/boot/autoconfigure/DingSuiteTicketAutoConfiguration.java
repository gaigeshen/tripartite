package work.gaigeshen.tripartite.ding.spring.boot.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.gaigeshen.tripartite.ding.openapi.client.DingSuiteTicketStore;
import work.gaigeshen.tripartite.ding.openapi.notify.event.DingSuiteTicketEventNotifyContentProcessor;

/**
 * 钉钉套件票据自动配置
 *
 * @author gaigeshen
 */
@ConditionalOnProperty(prefix = "ding", name = "suite-enabled", havingValue = "true")
@Configuration
public class DingSuiteTicketAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public DingSuiteTicketStore dingSuiteTicketStore() {
        return DingSuiteTicketStore.create();
    }

    @Bean
    public DingSuiteTicketEventNotifyContentProcessor dingSuiteTicketEventNotifyContentProcessor() {
        return new DingSuiteTicketEventNotifyContentProcessor(dingSuiteTicketStore());
    }
}
