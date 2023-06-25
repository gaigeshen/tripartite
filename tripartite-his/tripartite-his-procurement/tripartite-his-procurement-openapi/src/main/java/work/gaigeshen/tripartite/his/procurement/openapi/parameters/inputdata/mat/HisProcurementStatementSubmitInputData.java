package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author gaigeshen
 */
public class HisProcurementStatementSubmitInputData implements HisProcurementInputData {

    private String medinsCode;

    private String payOrdId;

    public String getMedinsCode() {
        return medinsCode;
    }

    public void setMedinsCode(String medinsCode) {
        this.medinsCode = medinsCode;
    }

    public String getPayOrdId() {
        return payOrdId;
    }

    public void setPayOrdId(String payOrdId) {
        this.payOrdId = payOrdId;
    }
}
