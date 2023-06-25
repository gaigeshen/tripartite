package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author gaigeshen
 */
public class HisProcurementReturnListInputData implements HisProcurementInputData {

    private String medinsCode;

    private String retnCode;

    private String strUpTime;

    private String endUpTime;

    private Integer current;

    private Integer size;

    public String getMedinsCode() {
        return medinsCode;
    }

    public void setMedinsCode(String medinsCode) {
        this.medinsCode = medinsCode;
    }

    public String getRetnCode() {
        return retnCode;
    }

    public void setRetnCode(String retnCode) {
        this.retnCode = retnCode;
    }

    public String getStrUpTime() {
        return strUpTime;
    }

    public void setStrUpTime(String strUpTime) {
        this.strUpTime = strUpTime;
    }

    public String getEndUpTime() {
        return endUpTime;
    }

    public void setEndUpTime(String endUpTime) {
        this.endUpTime = endUpTime;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
