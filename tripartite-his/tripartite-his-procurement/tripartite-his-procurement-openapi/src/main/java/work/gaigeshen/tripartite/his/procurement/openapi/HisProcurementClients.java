package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

import java.util.function.Predicate;

/**
 *
 * @author gaigeshen
 */
public interface HisProcurementClients {

  default HisProcurementBasicClient getClient() throws HisProcurementClientNotFoundException {
    return getClient(cfg -> true);
  }

  HisProcurementBasicClient getClient(Predicate<HisProcurementConfig> predicate)
          throws HisProcurementClientNotFoundException;

  HisProcurementBasicClient getClientOrCreate(HisProcurementConfig config)
          throws HisProcurementClientCreationException;
}
