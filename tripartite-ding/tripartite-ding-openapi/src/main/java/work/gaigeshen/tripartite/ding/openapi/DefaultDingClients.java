package work.gaigeshen.tripartite.ding.openapi;

import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 *
 * @author gaigeshen
 */
public class DefaultDingClients implements DingClients {

    private final Map<DingConfig, DingClient> dingClients = new ConcurrentHashMap<>();

    private final DingClientCreator dingClientCreator;

    public DefaultDingClients(Collection<DingClient> dingClients, DingClientCreator dingClientCreator) {
        if (Objects.isNull(dingClients)) {
            throw new IllegalArgumentException("ding clients cannot be null");
        }
        if (Objects.isNull(dingClientCreator)) {
            throw new IllegalArgumentException("ding client creator cannot be null");
        }
        for (DingClient dingClient : dingClients) {
            this.dingClients.put(dingClient.getDingConfig(), dingClient);
        }
        this.dingClientCreator = dingClientCreator;
    }

    @Override
    public DingClient getClient(Predicate<DingConfig> predicate) throws DingClientNotFoundException {
        if (Objects.isNull(predicate)) {
            throw new IllegalArgumentException("predicate cannot be null");
        }
        DingClient dingClient = findDingClient(predicate);
        if (Objects.isNull(dingClient)) {
            throw new DingClientNotFoundException("could not find ding client");
        }
        return dingClient;
    }

    @Override
    public DingClient getClientOrCreate(DingConfig config) {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        return dingClients.computeIfAbsent(config, dingClientCreator::create);
    }

    private DingClient findDingClient(Predicate<DingConfig> predicate) {
        if (Objects.isNull(predicate)) {
            throw new IllegalArgumentException("predicate cannot be null");
        }
        for (Map.Entry<DingConfig, DingClient> entry : dingClients.entrySet()) {
            if (predicate.test(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
