package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

/**
 * @author gaigeshen
 */
public class HisProcurementInvalidAccessTokenException extends HisProcurementAccessTokenException {

  public HisProcurementInvalidAccessTokenException(String message) {
    super(message);
  }

  public HisProcurementInvalidAccessTokenException(String message, Throwable cause) {
    super(message, cause);
  }
}
