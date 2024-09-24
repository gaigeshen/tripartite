package work.gaigeshen.tripartite.his.procurement.openapi.response.mat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.util.Collection;

/**
 * @author gaigeshen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HisProcurementPurchaseOrderSendResponse extends AbstractHisProcurementResponse {

    private String PurcCode;

    private String purcPlanCode;

    private Collection<OrdDetlIdListItem> ordDetlIdList;

    private Collection<OrdIdListItem> ordIdList;

    @Data
    public static class OrdDetlIdListItem {

        private String ordCode;

        private String ordDetlId;

        private String purcPlanDetId;

        private String ordId;
    }

    @Data
    public static class OrdIdListItem {

        private String ordCode;

        private String ordId;
    }
}
