package work.gaigeshen.tripartite.his.procurement.openapi.response.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementDirectoryListResponse extends AbstractHisProcurementResponse {

    private Collection<ListItem> dataList;

    private Integer currentPageNumber;

    private Integer totalPageCount;

    private Integer totalRecordCount;

    public Collection<ListItem> getDataList() {
        return dataList;
    }

    public void setDataList(Collection<ListItem> dataList) {
        this.dataList = dataList;
    }

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

    public static class ListItem {

        private String mcsCode;

        private String prodName;

        private String mcsRegno;

        private String mcsRegcertName;

        private String prodSpec;

        private String mcsMol;

        private String zclassCode;

        private String zclassName;

        private String primDirectory;

        private String secondDirectory;

        private String prodentpCode;

        private String prxyEntpCode;

        private String prodentpName;

        private BigDecimal pubonlnPric;

        private String mcsType;

        private String origin;

        private String unt;

        private String pubonlnType;

        private String tenditmId;

        private String tenditmName;

        private String pubonlnId;

        private String mcsInfoId;

        public String getMcsCode() {
            return mcsCode;
        }

        public void setMcsCode(String mcsCode) {
            this.mcsCode = mcsCode;
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

        public String getMcsRegcertName() {
            return mcsRegcertName;
        }

        public void setMcsRegcertName(String mcsRegcertName) {
            this.mcsRegcertName = mcsRegcertName;
        }

        public String getProdSpec() {
            return prodSpec;
        }

        public void setProdSpec(String prodSpec) {
            this.prodSpec = prodSpec;
        }

        public String getMcsMol() {
            return mcsMol;
        }

        public void setMcsMol(String mcsMol) {
            this.mcsMol = mcsMol;
        }

        public String getZclassCode() {
            return zclassCode;
        }

        public void setZclassCode(String zclassCode) {
            this.zclassCode = zclassCode;
        }

        public String getZclassName() {
            return zclassName;
        }

        public void setZclassName(String zclassName) {
            this.zclassName = zclassName;
        }

        public String getPrimDirectory() {
            return primDirectory;
        }

        public void setPrimDirectory(String primDirectory) {
            this.primDirectory = primDirectory;
        }

        public String getSecondDirectory() {
            return secondDirectory;
        }

        public void setSecondDirectory(String secondDirectory) {
            this.secondDirectory = secondDirectory;
        }

        public String getProdentpCode() {
            return prodentpCode;
        }

        public void setProdentpCode(String prodentpCode) {
            this.prodentpCode = prodentpCode;
        }

        public String getPrxyEntpCode() {
            return prxyEntpCode;
        }

        public void setPrxyEntpCode(String prxyEntpCode) {
            this.prxyEntpCode = prxyEntpCode;
        }

        public String getProdentpName() {
            return prodentpName;
        }

        public void setProdentpName(String prodentpName) {
            this.prodentpName = prodentpName;
        }

        public BigDecimal getPubonlnPric() {
            return pubonlnPric;
        }

        public void setPubonlnPric(BigDecimal pubonlnPric) {
            this.pubonlnPric = pubonlnPric;
        }

        public String getMcsType() {
            return mcsType;
        }

        public void setMcsType(String mcsType) {
            this.mcsType = mcsType;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getUnt() {
            return unt;
        }

        public void setUnt(String unt) {
            this.unt = unt;
        }

        public String getPubonlnType() {
            return pubonlnType;
        }

        public void setPubonlnType(String pubonlnType) {
            this.pubonlnType = pubonlnType;
        }

        public String getTenditmId() {
            return tenditmId;
        }

        public void setTenditmId(String tenditmId) {
            this.tenditmId = tenditmId;
        }

        public String getTenditmName() {
            return tenditmName;
        }

        public void setTenditmName(String tenditmName) {
            this.tenditmName = tenditmName;
        }

        public String getPubonlnId() {
            return pubonlnId;
        }

        public void setPubonlnId(String pubonlnId) {
            this.pubonlnId = pubonlnId;
        }

        public String getMcsInfoId() {
            return mcsInfoId;
        }

        public void setMcsInfoId(String mcsInfoId) {
            this.mcsInfoId = mcsInfoId;
        }
    }
}
