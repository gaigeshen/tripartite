package work.gaigeshen.tripartite.his.procurement.openapi.response.mat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

/**
 * @author gaigeshen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HisProcurementStatementCreateResponse extends AbstractHisProcurementResponse {

    private String payOrdId;
}
