package work.gaigeshen.tripartite.his.procurement.openapi.config;

import work.gaigeshen.tripartite.his.procurement.openapi.HisProcurementClientException;

/**
 * @author gaigeshen
 */
public class HisProcurementConfigException extends HisProcurementClientException {
  public HisProcurementConfigException(String message) {
    super(message);
  }

  public HisProcurementConfigException(String message, Throwable cause) {
    super(message, cause);
  }
}
