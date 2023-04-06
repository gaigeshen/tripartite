package work.gaigeshen.tripartite.his.procurement.openapi.response.basic;

import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

/**
 * @author gaigeshen
 */
public class HisProcurementStorehouseSaveResponse extends AbstractHisProcurementResponse {

    private String addrId;

    public String getAddrId() {
        return addrId;
    }

    public void setAddrId(String addrId) {
        this.addrId = addrId;
    }
}
