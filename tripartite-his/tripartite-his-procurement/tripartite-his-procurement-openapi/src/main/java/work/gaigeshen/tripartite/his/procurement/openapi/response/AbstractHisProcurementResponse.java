package work.gaigeshen.tripartite.his.procurement.openapi.response;

import lombok.Data;

/**
 * @author gaigeshen
 */
@Data
public abstract class AbstractHisProcurementResponse implements HisProcurementResponse {

    private Integer returnCode;

    private String returnMsg;
}
