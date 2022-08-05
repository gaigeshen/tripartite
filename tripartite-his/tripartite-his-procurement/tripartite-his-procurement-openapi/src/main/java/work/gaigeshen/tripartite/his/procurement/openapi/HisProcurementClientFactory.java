package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

/**
 *
 * @author gaigeshen
 */
public interface HisProcurementClientFactory {

  HisProcurementClient create(HisProcurementConfig config);

}
