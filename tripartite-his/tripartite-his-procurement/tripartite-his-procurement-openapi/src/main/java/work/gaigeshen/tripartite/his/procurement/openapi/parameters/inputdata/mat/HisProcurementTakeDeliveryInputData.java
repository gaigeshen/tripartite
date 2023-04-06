package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.math.BigDecimal;

/**
 * @author gaigeshen
 */
public class HisProcurementTakeDeliveryInputData implements HisProcurementInputData {

    private String medinsCode;

    private String shpCode;

    private BigDecimal shppCnt;

    public String getMedinsCode() {
        return medinsCode;
    }

    public void setMedinsCode(String medinsCode) {
        this.medinsCode = medinsCode;
    }

    public String getShpCode() {
        return shpCode;
    }

    public void setShpCode(String shpCode) {
        this.shpCode = shpCode;
    }

    public BigDecimal getShppCnt() {
        return shppCnt;
    }

    public void setShppCnt(BigDecimal shppCnt) {
        this.shppCnt = shppCnt;
    }
}
