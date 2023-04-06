package work.gaigeshen.tripartite.his.procurement.openapi;

/**
 * @author gaigeshen
 */
public class HisProcurementClientNotFoundException extends HisProcurementClientException {

    public HisProcurementClientNotFoundException(String message) {
        super(message);
    }

    public HisProcurementClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
