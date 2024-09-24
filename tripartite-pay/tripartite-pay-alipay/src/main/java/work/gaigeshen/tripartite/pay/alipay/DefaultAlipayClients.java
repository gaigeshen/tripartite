package work.gaigeshen.tripartite.pay.alipay;

import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.pay.alipay.config.AlipayConfig;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * 多支付宝客户端的默认实现
 *
 * @author gaigeshen
 */
public class DefaultAlipayClients implements AlipayClients {

    private final Map<AlipayConfig, AlipayClient> alipayClients = new ConcurrentHashMap<>();

    public DefaultAlipayClients() { }

    public DefaultAlipayClients(Collection<AlipayClient> alipayClients) {
        ArgumentValidate.notNull(alipayClients, "alipayClients cannot be null");
        for (AlipayClient alipayClient : alipayClients) {
            this.alipayClients.put(alipayClient.getAlipayConfig(), alipayClient);
        }
    }

    @Override
    public AlipayClient getClient(Predicate<AlipayConfig> predicate) throws AlipayClientNotFoundException {
        ArgumentValidate.notNull(predicate, "predicate cannot be null");
        AlipayClient alipayClient = findAlipayClient(predicate);
        if (Objects.isNull(alipayClient)) {
            throw new AlipayClientNotFoundException("could not find alipay client");
        }
        return alipayClient;
    }

    @Override
    public AlipayClient getClient() throws AlipayClientNotFoundException {
        AlipayClient alipayClient = findAlipayClient(cfg -> true);
        if (Objects.isNull(alipayClient)) {
            throw new AlipayClientNotFoundException("could not find alipay client");
        }
        return alipayClient;
    }

    private AlipayClient findAlipayClient(Predicate<AlipayConfig> predicate) {
        ArgumentValidate.notNull(predicate, "predicate cannot be null");
        for (Map.Entry<AlipayConfig, AlipayClient> entry : alipayClients.entrySet()) {
            if (predicate.test(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
