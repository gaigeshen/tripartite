package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 *
 * @author gaigeshen
 */
public class DefaultHisProcurementClients implements HisProcurementClients {

  private final Map<HisProcurementConfig, HisProcurementClient> hisProcurementClients = new ConcurrentHashMap<>();

  public DefaultHisProcurementClients() { }

  public DefaultHisProcurementClients(Collection<HisProcurementClient> hisProcurementClients) {
    if (Objects.isNull(hisProcurementClients)) {
      throw new IllegalArgumentException("his procurement clients cannot be null");
    }
    for (HisProcurementClient hisProcurementClient : hisProcurementClients) {
      this.hisProcurementClients.put(hisProcurementClient.getHisProcurementConfig(), hisProcurementClient);
    }
  }

  @Override
  public HisProcurementClient getClient(Predicate<HisProcurementConfig> predicate) throws HisProcurementClientNotFoundException {
    if (Objects.isNull(predicate)) {
      throw new IllegalArgumentException("predicate cannot be null");
    }
    HisProcurementClient hisProcurementClient  = findHisProcurementClient(predicate);
    if (Objects.isNull(hisProcurementClient)) {
      throw new HisProcurementClientNotFoundException("could not his procurement client");
    }
    return hisProcurementClient;
  }

  @Override
  public HisProcurementClient getClient() throws HisProcurementClientNotFoundException {
    HisProcurementClient hisProcurementClient = findHisProcurementClient(cfg -> true);
    if (Objects.isNull(hisProcurementClient)) {
      throw new HisProcurementClientNotFoundException("could not find his procurement client");
    }
    return hisProcurementClient;
  }

  private HisProcurementClient findHisProcurementClient(Predicate<HisProcurementConfig> predicate) {
    if (Objects.isNull(predicate)) {
      throw new IllegalArgumentException("predicate cannot be null");
    }
    for (Map.Entry<HisProcurementConfig, HisProcurementClient> entry : hisProcurementClients.entrySet()) {
      if (predicate.test(entry.getKey())) {
        return entry.getValue();
      }
    }
    return null;
  }
}
