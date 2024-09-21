package work.gaigeshen.tripartite.qyweixin.openapi.suite;

import work.gaigeshen.tripartite.core.client.config.Config;

import java.util.Map;

/**
 * 应用票据存储器
 *
 * @author gaigeshen
 */
public interface SuiteTicketStore<C extends Config> {

    boolean save(C config, SuiteTicket suiteTicket);

    void delete(C config);

    SuiteTicket find(C config);

    Map<C, SuiteTicket> findAll();
}
