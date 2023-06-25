package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * @author gaigeshen
 */
public class DefaultHisProcurementClients implements HisProcurementClients {

    private final Map<HisProcurementConfig, HisProcurementBasicClient> hisProcurementClients = new ConcurrentHashMap<>();

    private final HisProcurementClientCreator hisProcurementClientCreator;

    public DefaultHisProcurementClients(
            Collection<HisProcurementBasicClient> hisProcurementClients,
            HisProcurementClientCreator hisProcurementClientCreator) {
        if (Objects.isNull(hisProcurementClients)) {
            throw new IllegalArgumentException("his procurement clients cannot be null");
        }
        if (Objects.isNull(hisProcurementClientCreator)) {
            throw new IllegalArgumentException("his procurement client creator cannot be null");
        }
        for (HisProcurementBasicClient hisProcurementClient : hisProcurementClients) {
            this.hisProcurementClients.put(hisProcurementClient.getHisProcurementConfig(), hisProcurementClient);
        }
        this.hisProcurementClientCreator = hisProcurementClientCreator;
    }

    @Override
    public HisProcurementBasicClient getClient(Predicate<HisProcurementConfig> predicate)
            throws HisProcurementClientNotFoundException {
        if (Objects.isNull(predicate)) {
            throw new IllegalArgumentException("predicate cannot be null");
        }
        HisProcurementBasicClient hisProcurementClient = findHisProcurementClient(predicate);
        if (Objects.isNull(hisProcurementClient)) {
            throw new HisProcurementClientNotFoundException("could not find his procurement client");
        }
        return hisProcurementClient;
    }

    @Override
    public HisProcurementBasicClient getClientOrCreate(HisProcurementConfig config)
            throws HisProcurementClientCreationException {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        return hisProcurementClients.computeIfAbsent(config, hisProcurementClientCreator::create);
    }

    private HisProcurementBasicClient findHisProcurementClient(Predicate<HisProcurementConfig> predicate) {
        if (Objects.isNull(predicate)) {
            throw new IllegalArgumentException("predicate cannot be null");
        }
        for (Map.Entry<HisProcurementConfig, HisProcurementBasicClient> entry : hisProcurementClients.entrySet()) {
            if (predicate.test(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
