package work.gaigeshen.tripartite.qyweixin.openapi.suite;

import work.gaigeshen.tripartite.core.client.config.Config;

import java.util.Map;

/**
 * 应用票据存储器
 *
 * @author gaigeshen
 */
public interface QyWeixinSuiteTicketStore<C extends Config> {

    boolean save(C config, QyWeixinSuiteTicket suiteTicket);

    QyWeixinSuiteTicket find(C config);

    Map<C, QyWeixinSuiteTicket> findAll();
}
