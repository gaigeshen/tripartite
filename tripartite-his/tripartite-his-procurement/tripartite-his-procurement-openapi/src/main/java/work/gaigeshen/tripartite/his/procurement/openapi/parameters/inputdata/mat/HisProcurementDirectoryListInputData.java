package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author gaigeshen
 */
public class HisProcurementDirectoryListInputData implements HisProcurementInputData {

    private String mcsCode;

    private String mcsInfoId;

    private String strUpTime;

    private String endUpTime;

    private Integer current;

    private Integer size;

    public String getMcsCode() {
        return mcsCode;
    }

    public void setMcsCode(String mcsCode) {
        this.mcsCode = mcsCode;
    }

    public String getMcsInfoId() {
        return mcsInfoId;
    }

    public void setMcsInfoId(String mcsInfoId) {
        this.mcsInfoId = mcsInfoId;
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
