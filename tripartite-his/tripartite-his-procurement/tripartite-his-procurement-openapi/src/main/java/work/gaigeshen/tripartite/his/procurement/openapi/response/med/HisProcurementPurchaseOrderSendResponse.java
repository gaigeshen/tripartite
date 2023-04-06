package work.gaigeshen.tripartite.his.procurement.openapi.response.med;

import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementPurchaseOrderSendResponse extends AbstractHisProcurementResponse {

    private String PurcCode;

    private String purcPlanCode;

    private Collection<OrdDetlIdListItem> ordDetlIdList;

    private Collection<OrdIdListItem> ordIdList;

    public String getPurcCode() {
        return PurcCode;
    }

    public void setPurcCode(String purcCode) {
        PurcCode = purcCode;
    }

    public String getPurcPlanCode() {
        return purcPlanCode;
    }

    public void setPurcPlanCode(String purcPlanCode) {
        this.purcPlanCode = purcPlanCode;
    }

    public Collection<OrdDetlIdListItem> getOrdDetlIdList() {
        return ordDetlIdList;
    }

    public void setOrdDetlIdList(Collection<OrdDetlIdListItem> ordDetlIdList) {
        this.ordDetlIdList = ordDetlIdList;
    }

    public Collection<OrdIdListItem> getOrdIdList() {
        return ordIdList;
    }

    public void setOrdIdList(Collection<OrdIdListItem> ordIdList) {
        this.ordIdList = ordIdList;
    }

    public static class OrdDetlIdListItem {

        private String ordDetlId;

        private String purcPlanDetId;

        private String ordId;

        public String getOrdDetlId() {
            return ordDetlId;
        }

        public void setOrdDetlId(String ordDetlId) {
            this.ordDetlId = ordDetlId;
        }

        public String getPurcPlanDetId() {
            return purcPlanDetId;
        }

        public void setPurcPlanDetId(String purcPlanDetId) {
            this.purcPlanDetId = purcPlanDetId;
        }

        public String getOrdId() {
            return ordId;
        }

        public void setOrdId(String ordId) {
            this.ordId = ordId;
        }
    }

    public static class OrdIdListItem {

        private String ordId;

        public String getOrdId() {
            return ordId;
        }

        public void setOrdId(String ordId) {
            this.ordId = ordId;
        }
    }
}
