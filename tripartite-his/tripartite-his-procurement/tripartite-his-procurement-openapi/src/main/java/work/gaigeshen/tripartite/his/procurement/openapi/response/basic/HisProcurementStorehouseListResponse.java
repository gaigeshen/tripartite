package work.gaigeshen.tripartite.his.procurement.openapi.response.basic;

import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementStorehouseListResponse extends AbstractHisProcurementResponse {

    private Integer currentPageNumber;

    private Integer totalPageCount;

    private Integer totalRecordCount;

    private Collection<ListItem> dataList;

    public Integer getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(Integer currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public Integer getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(Integer totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public Collection<ListItem> getDataList() {
        return dataList;
    }

    public void setDataList(Collection<ListItem> dataList) {
        this.dataList = dataList;
    }

    public static class ListItem {

        private String conerName;

        private String entpCode;

        private String mcsFlag;

        private String defFlag;

        private String prov;

        private String city;

        private String coty;

        private String invottl;

        private String addrId;

        private String addr;

        private String conerTel;

        private String stroomName;

        public String getConerName() {
            return conerName;
        }

        public void setConerName(String conerName) {
            this.conerName = conerName;
        }

        public String getEntpCode() {
            return entpCode;
        }

        public void setEntpCode(String entpCode) {
            this.entpCode = entpCode;
        }

        public String getMcsFlag() {
            return mcsFlag;
        }

        public void setMcsFlag(String mcsFlag) {
            this.mcsFlag = mcsFlag;
        }

        public String getDefFlag() {
            return defFlag;
        }

        public void setDefFlag(String defFlag) {
            this.defFlag = defFlag;
        }

        public String getProv() {
            return prov;
        }

        public void setProv(String prov) {
            this.prov = prov;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCoty() {
            return coty;
        }

        public void setCoty(String coty) {
            this.coty = coty;
        }

        public String getInvottl() {
            return invottl;
        }

        public void setInvottl(String invottl) {
            this.invottl = invottl;
        }

        public String getAddrId() {
            return addrId;
        }

        public void setAddrId(String addrId) {
            this.addrId = addrId;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getConerTel() {
            return conerTel;
        }

        public void setConerTel(String conerTel) {
            this.conerTel = conerTel;
        }

        public String getStroomName() {
            return stroomName;
        }

        public void setStroomName(String stroomName) {
            this.stroomName = stroomName;
        }
    }
}
