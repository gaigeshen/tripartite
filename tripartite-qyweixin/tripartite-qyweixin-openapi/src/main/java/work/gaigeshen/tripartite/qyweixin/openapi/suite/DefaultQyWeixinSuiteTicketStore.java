package work.gaigeshen.tripartite.qyweixin.openapi.suite;

import work.gaigeshen.tripartite.core.client.config.Config;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认的应用票据存储器
 *
 * @author gaigeshen
 */
public class DefaultQyWeixinSuiteTicketStore<C extends Config> implements QyWeixinSuiteTicketStore<C> {

    private final Map<C, QyWeixinSuiteTicket> internalStore = new ConcurrentHashMap<>();

    @Override
    public boolean save(C config, QyWeixinSuiteTicket suiteTicket) {
        ArgumentValidate.notNull(suiteTicket, "suiteTicket cannot be null");
        return Objects.isNull(internalStore.put(config, suiteTicket));
    }

    @Override
    public QyWeixinSuiteTicket find(C config) {
        ArgumentValidate.notNull(config, "config cannot be null");
        return internalStore.get(config);
    }

    @Override
    public Map<C, QyWeixinSuiteTicket> findAll() {
        return new HashMap<>(internalStore);
    }
}
