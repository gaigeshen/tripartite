package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

import work.gaigeshen.tripartite.his.procurement.openapi.HisProcurementClientException;

/**
 * @author gaigeshen
 */
public class HisProcurementAccessTokenException extends HisProcurementClientException {

  public HisProcurementAccessTokenException(String message) {
    super(message);
  }

  public HisProcurementAccessTokenException(String message, Throwable cause) {
    super(message, cause);
  }
}
