package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author gaigeshen
 */
public class HisProcurementDirectoryListInputData implements HisProcurementInputData {

    private String mcsCode;

    private String mcsInfoId;

    private String prodName;

    private String mcsRegno;

    private String pubonlnId;

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

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getMcsRegno() {
        return mcsRegno;
    }

    public void setMcsRegno(String mcsRegno) {
        this.mcsRegno = mcsRegno;
    }

    public String getPubonlnId() {
        return pubonlnId;
    }

    public void setPubonlnId(String pubonlnId) {
        this.pubonlnId = pubonlnId;
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
