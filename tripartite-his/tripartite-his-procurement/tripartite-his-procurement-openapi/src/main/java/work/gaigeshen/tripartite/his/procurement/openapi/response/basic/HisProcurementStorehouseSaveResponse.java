package work.gaigeshen.tripartite.his.procurement.openapi.response.basic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

/**
 * @author gaigeshen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HisProcurementStorehouseSaveResponse extends AbstractHisProcurementResponse {

    private String addrId;
}
