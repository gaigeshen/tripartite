package work.gaigeshen.tripartite.his.procurement.openapi.response.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementDirectoryUsedListResponse extends AbstractHisProcurementResponse {

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

        private String zclassName;

        private String mcsCode;

        private String mcsRegno;

        private String regcert;

        private String minpacuntName;

        private String mcsMol;

        private String prxyEntpCode;

        private String prxyEntpName;

        private String mcsSpec;

        private String hospListId;

        private BigDecimal hospPurcPric;

        private String prodentpCode;

        private String prodentpName;

        private String delventpName;

        private String mcsInfoId;

        private String pubonlnRsltId;

        private String secondDirectory;

        private String primDirectory;

        private String prodName;

        private BigDecimal pubonlnPric;

        private String delventpCode;

        private String updtTime;

        public String getZclassName() {
            return zclassName;
        }

        public void setZclassName(String zclassName) {
            this.zclassName = zclassName;
        }

        public String getMcsCode() {
            return mcsCode;
        }

        public void setMcsCode(String mcsCode) {
            this.mcsCode = mcsCode;
        }

        public String getMcsRegno() {
            return mcsRegno;
        }

        public void setMcsRegno(String mcsRegno) {
            this.mcsRegno = mcsRegno;
        }

        public String getRegcert() {
            return regcert;
        }

        public void setRegcert(String regcert) {
            this.regcert = regcert;
        }

        public String getMinpacuntName() {
            return minpacuntName;
        }

        public void setMinpacuntName(String minpacuntName) {
            this.minpacuntName = minpacuntName;
        }

        public String getMcsMol() {
            return mcsMol;
        }

        public void setMcsMol(String mcsMol) {
            this.mcsMol = mcsMol;
        }

        public String getPrxyEntpCode() {
            return prxyEntpCode;
        }

        public void setPrxyEntpCode(String prxyEntpCode) {
            this.prxyEntpCode = prxyEntpCode;
        }

        public String getPrxyEntpName() {
            return prxyEntpName;
        }

        public void setPrxyEntpName(String prxyEntpName) {
            this.prxyEntpName = prxyEntpName;
        }

        public String getMcsSpec() {
            return mcsSpec;
        }

        public void setMcsSpec(String mcsSpec) {
            this.mcsSpec = mcsSpec;
        }

        public String getHospListId() {
            return hospListId;
        }

        public void setHospListId(String hospListId) {
            this.hospListId = hospListId;
        }

        public BigDecimal getHospPurcPric() {
            return hospPurcPric;
        }

        public void setHospPurcPric(BigDecimal hospPurcPric) {
            this.hospPurcPric = hospPurcPric;
        }

        public String getProdentpCode() {
            return prodentpCode;
        }

        public void setProdentpCode(String prodentpCode) {
            this.prodentpCode = prodentpCode;
        }

        public String getProdentpName() {
            return prodentpName;
        }

        public void setProdentpName(String prodentpName) {
            this.prodentpName = prodentpName;
        }

        public String getDelventpName() {
            return delventpName;
        }

        public void setDelventpName(String delventpName) {
            this.delventpName = delventpName;
        }

        public String getMcsInfoId() {
            return mcsInfoId;
        }

        public void setMcsInfoId(String mcsInfoId) {
            this.mcsInfoId = mcsInfoId;
        }

        public String getPubonlnRsltId() {
            return pubonlnRsltId;
        }

        public void setPubonlnRsltId(String pubonlnRsltId) {
            this.pubonlnRsltId = pubonlnRsltId;
        }

        public String getSecondDirectory() {
            return secondDirectory;
        }

        public void setSecondDirectory(String secondDirectory) {
            this.secondDirectory = secondDirectory;
        }

        public String getPrimDirectory() {
            return primDirectory;
        }

        public void setPrimDirectory(String primDirectory) {
            this.primDirectory = primDirectory;
        }

        public String getProdName() {
            return prodName;
        }

        public void setProdName(String prodName) {
            this.prodName = prodName;
        }

        public BigDecimal getPubonlnPric() {
            return pubonlnPric;
        }

        public void setPubonlnPric(BigDecimal pubonlnPric) {
            this.pubonlnPric = pubonlnPric;
        }

        public String getDelventpCode() {
            return delventpCode;
        }

        public void setDelventpCode(String delventpCode) {
            this.delventpCode = delventpCode;
        }

        public String getUpdtTime() {
            return updtTime;
        }

        public void setUpdtTime(String updtTime) {
            this.updtTime = updtTime;
        }
    }
}
