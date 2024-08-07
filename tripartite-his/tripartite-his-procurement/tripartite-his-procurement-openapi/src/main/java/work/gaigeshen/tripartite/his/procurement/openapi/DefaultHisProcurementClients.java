package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.core.util.ArgumentValidate;
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
        ArgumentValidate.notNull(hisProcurementClients, "hisProcurementClients cannot be null");
        ArgumentValidate.notNull(hisProcurementClientCreator, "hisProcurementClientCreator cannot be null");
        for (HisProcurementBasicClient hisProcurementClient : hisProcurementClients) {
            this.hisProcurementClients.put(hisProcurementClient.getHisProcurementConfig(), hisProcurementClient);
        }
        this.hisProcurementClientCreator = hisProcurementClientCreator;
    }

    @Override
    public HisProcurementBasicClient getClient(Predicate<HisProcurementConfig> predicate)
            throws HisProcurementClientNotFoundException {
        ArgumentValidate.notNull(predicate, "predicate cannot be null");
        HisProcurementBasicClient hisProcurementClient = findHisProcurementClient(predicate);
        if (Objects.isNull(hisProcurementClient)) {
            throw new HisProcurementClientNotFoundException("could not find his procurement client");
        }
        return hisProcurementClient;
    }

    @Override
    public HisProcurementBasicClient getClientOrCreate(HisProcurementConfig config)
            throws HisProcurementClientCreationException {
        ArgumentValidate.notNull(config, "config cannot be null");
        return hisProcurementClients.computeIfAbsent(config, hisProcurementClientCreator::create);
    }

    private HisProcurementBasicClient findHisProcurementClient(Predicate<HisProcurementConfig> predicate) {
        ArgumentValidate.notNull(predicate, "predicate cannot be null");
        for (Map.Entry<HisProcurementConfig, HisProcurementBasicClient> entry : hisProcurementClients.entrySet()) {
            if (predicate.test(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
