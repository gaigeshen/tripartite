package work.gaigeshen.tripartite.his.procurement.openapi.response.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementReturnListResponse extends AbstractHisProcurementResponse {

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

    private String regcert;

    private String retnCode;

    private String rgtMol;

    private String purcCnt;

    private String medinsRetnRea;

    private String prodentpName;

    private String ordCode;

    private String prodName;

    private String prodMatl;

    private String delventpCode;

    private String manuLotnum;

    private String medinsRetnTime;

    private String mcsRegno;

    private String delventpFailRea;

    private String delventpPassTime;

    private String pacMatl;

    private String prodSpec;

    private BigDecimal retnCnt;

    private BigDecimal retnAmt;

    private String delventpName;

    private String delventpFailTime;

    private String retnChkStas;

    private String prodPac;

    private String returnInvoiceId;

    private String pubonlnPric;

    private String dclaEntpName;

    private String purcpric;

    public String getRegcert() {
      return regcert;
    }

    public void setRegcert(String regcert) {
      this.regcert = regcert;
    }

    public String getRetnCode() {
      return retnCode;
    }

    public void setRetnCode(String retnCode) {
      this.retnCode = retnCode;
    }

    public String getRgtMol() {
      return rgtMol;
    }

    public void setRgtMol(String rgtMol) {
      this.rgtMol = rgtMol;
    }

    public String getPurcCnt() {
      return purcCnt;
    }

    public void setPurcCnt(String purcCnt) {
      this.purcCnt = purcCnt;
    }

    public String getMedinsRetnRea() {
      return medinsRetnRea;
    }

    public void setMedinsRetnRea(String medinsRetnRea) {
      this.medinsRetnRea = medinsRetnRea;
    }

    public String getProdentpName() {
      return prodentpName;
    }

    public void setProdentpName(String prodentpName) {
      this.prodentpName = prodentpName;
    }

    public String getOrdCode() {
      return ordCode;
    }

    public void setOrdCode(String ordCode) {
      this.ordCode = ordCode;
    }

    public String getProdName() {
      return prodName;
    }

    public void setProdName(String prodName) {
      this.prodName = prodName;
    }

    public String getProdMatl() {
      return prodMatl;
    }

    public void setProdMatl(String prodMatl) {
      this.prodMatl = prodMatl;
    }

    public String getDelventpCode() {
      return delventpCode;
    }

    public void setDelventpCode(String delventpCode) {
      this.delventpCode = delventpCode;
    }

    public String getManuLotnum() {
      return manuLotnum;
    }

    public void setManuLotnum(String manuLotnum) {
      this.manuLotnum = manuLotnum;
    }

    public String getMedinsRetnTime() {
      return medinsRetnTime;
    }

    public void setMedinsRetnTime(String medinsRetnTime) {
      this.medinsRetnTime = medinsRetnTime;
    }

    public String getMcsRegno() {
      return mcsRegno;
    }

    public void setMcsRegno(String mcsRegno) {
      this.mcsRegno = mcsRegno;
    }

    public String getDelventpFailRea() {
      return delventpFailRea;
    }

    public void setDelventpFailRea(String delventpFailRea) {
      this.delventpFailRea = delventpFailRea;
    }

    public String getDelventpPassTime() {
      return delventpPassTime;
    }

    public void setDelventpPassTime(String delventpPassTime) {
      this.delventpPassTime = delventpPassTime;
    }

    public String getPacMatl() {
      return pacMatl;
    }

    public void setPacMatl(String pacMatl) {
      this.pacMatl = pacMatl;
    }

    public String getProdSpec() {
      return prodSpec;
    }

    public void setProdSpec(String prodSpec) {
      this.prodSpec = prodSpec;
    }

    public BigDecimal getRetnCnt() {
      return retnCnt;
    }

    public void setRetnCnt(BigDecimal retnCnt) {
      this.retnCnt = retnCnt;
    }

    public BigDecimal getRetnAmt() {
      return retnAmt;
    }

    public void setRetnAmt(BigDecimal retnAmt) {
      this.retnAmt = retnAmt;
    }

    public String getDelventpName() {
      return delventpName;
    }

    public void setDelventpName(String delventpName) {
      this.delventpName = delventpName;
    }

    public String getDelventpFailTime() {
      return delventpFailTime;
    }

    public void setDelventpFailTime(String delventpFailTime) {
      this.delventpFailTime = delventpFailTime;
    }

    public String getRetnChkStas() {
      return retnChkStas;
    }

    public void setRetnChkStas(String retnChkStas) {
      this.retnChkStas = retnChkStas;
    }

    public String getProdPac() {
      return prodPac;
    }

    public void setProdPac(String prodPac) {
      this.prodPac = prodPac;
    }

    public String getReturnInvoiceId() {
      return returnInvoiceId;
    }

    public void setReturnInvoiceId(String returnInvoiceId) {
      this.returnInvoiceId = returnInvoiceId;
    }

    public String getPubonlnPric() {
      return pubonlnPric;
    }

    public void setPubonlnPric(String pubonlnPric) {
      this.pubonlnPric = pubonlnPric;
    }

    public String getDclaEntpName() {
      return dclaEntpName;
    }

    public void setDclaEntpName(String dclaEntpName) {
      this.dclaEntpName = dclaEntpName;
    }

    public String getPurcpric() {
      return purcpric;
    }

    public void setPurcpric(String purcpric) {
      this.purcpric = purcpric;
    }
  }
}
