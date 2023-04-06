package work.gaigeshen.tripartite.his.procurement.openapi;

/**
 * @author gaigeshen
 */
public class HisProcurementClientException extends RuntimeException {

    public HisProcurementClientException(String message) {
        super(message);
    }

    public HisProcurementClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
