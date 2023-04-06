package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.med;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author gaigeshen
 */
public class HisProcurementReturnListInputData implements HisProcurementInputData {

    private String medinsCode;

    private String delventpCode;

    private String startMedinsRetnTime;

    private String endMedinsRetnTime;

    private Integer retnChkStas;

    private Integer current;

    private Integer size;

    public String getMedinsCode() {
        return medinsCode;
    }

    public void setMedinsCode(String medinsCode) {
        this.medinsCode = medinsCode;
    }

    public String getDelventpCode() {
        return delventpCode;
    }

    public void setDelventpCode(String delventpCode) {
        this.delventpCode = delventpCode;
    }

    public String getStartMedinsRetnTime() {
        return startMedinsRetnTime;
    }

    public void setStartMedinsRetnTime(String startMedinsRetnTime) {
        this.startMedinsRetnTime = startMedinsRetnTime;
    }

    public String getEndMedinsRetnTime() {
        return endMedinsRetnTime;
    }

    public void setEndMedinsRetnTime(String endMedinsRetnTime) {
        this.endMedinsRetnTime = endMedinsRetnTime;
    }

    public Integer getRetnChkStas() {
        return retnChkStas;
    }

    public void setRetnChkStas(Integer retnChkStas) {
        this.retnChkStas = retnChkStas;
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

    public static class ListItem {

        private String shpId;

        public String getShpId() {
            return shpId;
        }

        public void setShpId(String shpId) {
            this.shpId = shpId;
        }
    }
}
