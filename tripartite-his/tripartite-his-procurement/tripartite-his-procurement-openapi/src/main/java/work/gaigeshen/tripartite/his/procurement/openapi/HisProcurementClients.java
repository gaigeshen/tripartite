package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *
 * @author gaigeshen
 */
public interface HisProcurementClients {

  default HisProcurementConfig getConfig(String account) throws HisProcurementClientNotFoundException {
    if (Objects.isNull(account)) {
      throw new IllegalArgumentException("account cannot be null");
    }
    return getConfig(hc -> Objects.equals(hc.getAccount(), account));
  }

  default HisProcurementConfig getConfig(Predicate<HisProcurementConfig> predicate) throws HisProcurementClientNotFoundException {
    return getClient(predicate).getHisProcurementConfig();
  }

  default HisProcurementClient getClient(String account) throws HisProcurementClientNotFoundException {
    return getClient(ac -> Objects.equals(ac.getAccount(), account));
  }

  HisProcurementClient getClient(Predicate<HisProcurementConfig> predicate) throws HisProcurementClientNotFoundException;

  HisProcurementClient getClient() throws HisProcurementClientNotFoundException;

  default HisProcurementClient getClientOrCreate(Supplier<HisProcurementConfig> configSupplier) {
    if (Objects.isNull(configSupplier)) {
      throw new IllegalArgumentException("config supplier cannot be null");
    }
    return getClientOrCreate(configSupplier.get());
  }

  HisProcurementClient getClientOrCreate(HisProcurementConfig config);
}
