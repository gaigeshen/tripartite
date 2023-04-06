package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.med;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementShipmentListInputData implements HisProcurementInputData {

    private String medinsCode;

    private String delventpCode;

    private String shpUpdateStartTime;

    private String shpUpdateEndTime;

    private String shpTime;

    private Integer current;

    private Integer size;

    private Collection<ListItem> list;

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

    public String getShpUpdateStartTime() {
        return shpUpdateStartTime;
    }

    public void setShpUpdateStartTime(String shpUpdateStartTime) {
        this.shpUpdateStartTime = shpUpdateStartTime;
    }

    public String getShpUpdateEndTime() {
        return shpUpdateEndTime;
    }

    public void setShpUpdateEndTime(String shpUpdateEndTime) {
        this.shpUpdateEndTime = shpUpdateEndTime;
    }

    public String getShpTime() {
        return shpTime;
    }

    public void setShpTime(String shpTime) {
        this.shpTime = shpTime;
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

    public Collection<ListItem> getList() {
        return list;
    }

    public void setList(Collection<ListItem> list) {
        this.list = list;
    }

    public static class ListItem {

        private String ordDetlId;

        public String getOrdDetlId() {
            return ordDetlId;
        }

        public void setOrdDetlId(String ordDetlId) {
            this.ordDetlId = ordDetlId;
        }
    }
}
