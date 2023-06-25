package work.gaigeshen.tripartite.his.procurement.openapi.response.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

/**
 * @author gaigeshen
 */
public class HisProcurementPurchaseOrderCancelResponse extends AbstractHisProcurementResponse {

    private String PurcCode;

    public String getPurcCode() {
        return PurcCode;
    }

    public void setPurcCode(String purcCode) {
        PurcCode = purcCode;
    }
}
