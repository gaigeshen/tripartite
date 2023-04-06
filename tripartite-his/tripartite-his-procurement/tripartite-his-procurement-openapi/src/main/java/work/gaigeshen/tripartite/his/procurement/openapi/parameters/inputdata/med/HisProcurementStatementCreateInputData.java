package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.med;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author gaigeshen
 */
public class HisProcurementStatementCreateInputData implements HisProcurementInputData {

    private String medinsCode;

    private String delvEntpCode;

    private String delvEntpName;

    public String getMedinsCode() {
        return medinsCode;
    }

    public void setMedinsCode(String medinsCode) {
        this.medinsCode = medinsCode;
    }

    public String getDelvEntpCode() {
        return delvEntpCode;
    }

    public void setDelvEntpCode(String delvEntpCode) {
        this.delvEntpCode = delvEntpCode;
    }

    public String getDelvEntpName() {
        return delvEntpName;
    }

    public void setDelvEntpName(String delvEntpName) {
        this.delvEntpName = delvEntpName;
    }
}
