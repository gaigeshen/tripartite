package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.med;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementOrderListInputData implements HisProcurementInputData {

    private String medinsCode;

    private String purcCode;

    private String delventpCode;

    private String crteTimeUn;

    private String crteTimeEn;

    private Integer current;

    private Integer size;

    private Collection<ListItem> list;

    public String getMedinsCode() {
        return medinsCode;
    }

    public void setMedinsCode(String medinsCode) {
        this.medinsCode = medinsCode;
    }

    public String getPurcCode() {
        return purcCode;
    }

    public void setPurcCode(String purcCode) {
        this.purcCode = purcCode;
    }

    public String getDelventpCode() {
        return delventpCode;
    }

    public void setDelventpCode(String delventpCode) {
        this.delventpCode = delventpCode;
    }

    public String getCrteTimeUn() {
        return crteTimeUn;
    }

    public void setCrteTimeUn(String crteTimeUn) {
        this.crteTimeUn = crteTimeUn;
    }

    public String getCrteTimeEn() {
        return crteTimeEn;
    }

    public void setCrteTimeEn(String crteTimeEn) {
        this.crteTimeEn = crteTimeEn;
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
