package work.gaigeshen.tripartite.his.procurement.openapi.response.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

/**
 * @author gaigeshen
 */
public class HisProcurementTakeDeliveryResponse extends AbstractHisProcurementResponse {

    private String shpCode;

    public String getShpCode() {
        return shpCode;
    }

    public void setShpCode(String shpCode) {
        this.shpCode = shpCode;
    }
}
