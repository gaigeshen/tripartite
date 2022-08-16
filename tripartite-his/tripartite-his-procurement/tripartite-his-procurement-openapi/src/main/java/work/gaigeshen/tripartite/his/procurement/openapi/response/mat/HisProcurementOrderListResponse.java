package work.gaigeshen.tripartite.his.procurement.openapi.response.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementOrderListResponse extends AbstractHisProcurementResponse {

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

    private String planDetlMemo;

    private String regcert;

    private String rgtMol;

    private String addTime;

    private String mcsType;

    private String ordDetlId;

    private String purcCnt;

    private String conerTel;

    private String ordId;

    private String subTime;

    private String prodentpName;

    private String purcPlanCode;

    private String wanTime;

    private String conerName;

    private String disYN;

    private String ordCode;

    private String prodName;

    private String addrId;

    private String disCnt;

    private String prodMatl;

    private String delventpCode;

    private String mcsRegno;

    private String disTime;

    private String discode;

    private String itemname;

    private String pacMatl;

    private String proTypeStock;

    private String prodSpec;

    private String delventpName;

    private String pubonlnRsltId;

    private String prodPac;

    private String pubonlnPric;

    private String qlv;

    private String dclaEntpName;

    private String medinsCode;

    private BigDecimal purcpric;

    private String wanCnt;

    public String getPlanDetlMemo() {
      return planDetlMemo;
    }

    public void setPlanDetlMemo(String planDetlMemo) {
      this.planDetlMemo = planDetlMemo;
    }

    public String getRegcert() {
      return regcert;
    }

    public void setRegcert(String regcert) {
      this.regcert = regcert;
    }

    public String getRgtMol() {
      return rgtMol;
    }

    public void setRgtMol(String rgtMol) {
      this.rgtMol = rgtMol;
    }

    public String getAddTime() {
      return addTime;
    }

    public void setAddTime(String addTime) {
      this.addTime = addTime;
    }

    public String getMcsType() {
      return mcsType;
    }

    public void setMcsType(String mcsType) {
      this.mcsType = mcsType;
    }

    public String getOrdDetlId() {
      return ordDetlId;
    }

    public void setOrdDetlId(String ordDetlId) {
      this.ordDetlId = ordDetlId;
    }

    public String getPurcCnt() {
      return purcCnt;
    }

    public void setPurcCnt(String purcCnt) {
      this.purcCnt = purcCnt;
    }

    public String getConerTel() {
      return conerTel;
    }

    public void setConerTel(String conerTel) {
      this.conerTel = conerTel;
    }

    public String getOrdId() {
      return ordId;
    }

    public void setOrdId(String ordId) {
      this.ordId = ordId;
    }

    public String getSubTime() {
      return subTime;
    }

    public void setSubTime(String subTime) {
      this.subTime = subTime;
    }

    public String getProdentpName() {
      return prodentpName;
    }

    public void setProdentpName(String prodentpName) {
      this.prodentpName = prodentpName;
    }

    public String getPurcPlanCode() {
      return purcPlanCode;
    }

    public void setPurcPlanCode(String purcPlanCode) {
      this.purcPlanCode = purcPlanCode;
    }

    public String getWanTime() {
      return wanTime;
    }

    public void setWanTime(String wanTime) {
      this.wanTime = wanTime;
    }

    public String getConerName() {
      return conerName;
    }

    public void setConerName(String conerName) {
      this.conerName = conerName;
    }

    public String getDisYN() {
      return disYN;
    }

    public void setDisYN(String disYN) {
      this.disYN = disYN;
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

    public String getAddrId() {
      return addrId;
    }

    public void setAddrId(String addrId) {
      this.addrId = addrId;
    }

    public String getDisCnt() {
      return disCnt;
    }

    public void setDisCnt(String disCnt) {
      this.disCnt = disCnt;
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

    public String getMcsRegno() {
      return mcsRegno;
    }

    public void setMcsRegno(String mcsRegno) {
      this.mcsRegno = mcsRegno;
    }

    public String getDisTime() {
      return disTime;
    }

    public void setDisTime(String disTime) {
      this.disTime = disTime;
    }

    public String getDiscode() {
      return discode;
    }

    public void setDiscode(String discode) {
      this.discode = discode;
    }

    public String getItemname() {
      return itemname;
    }

    public void setItemname(String itemname) {
      this.itemname = itemname;
    }

    public String getPacMatl() {
      return pacMatl;
    }

    public void setPacMatl(String pacMatl) {
      this.pacMatl = pacMatl;
    }

    public String getProTypeStock() {
      return proTypeStock;
    }

    public void setProTypeStock(String proTypeStock) {
      this.proTypeStock = proTypeStock;
    }

    public String getProdSpec() {
      return prodSpec;
    }

    public void setProdSpec(String prodSpec) {
      this.prodSpec = prodSpec;
    }

    public String getDelventpName() {
      return delventpName;
    }

    public void setDelventpName(String delventpName) {
      this.delventpName = delventpName;
    }

    public String getPubonlnRsltId() {
      return pubonlnRsltId;
    }

    public void setPubonlnRsltId(String pubonlnRsltId) {
      this.pubonlnRsltId = pubonlnRsltId;
    }

    public String getProdPac() {
      return prodPac;
    }

    public void setProdPac(String prodPac) {
      this.prodPac = prodPac;
    }

    public String getPubonlnPric() {
      return pubonlnPric;
    }

    public void setPubonlnPric(String pubonlnPric) {
      this.pubonlnPric = pubonlnPric;
    }

    public String getQlv() {
      return qlv;
    }

    public void setQlv(String qlv) {
      this.qlv = qlv;
    }

    public String getDclaEntpName() {
      return dclaEntpName;
    }

    public void setDclaEntpName(String dclaEntpName) {
      this.dclaEntpName = dclaEntpName;
    }

    public String getMedinsCode() {
      return medinsCode;
    }

    public void setMedinsCode(String medinsCode) {
      this.medinsCode = medinsCode;
    }

    public BigDecimal getPurcpric() {
      return purcpric;
    }

    public void setPurcpric(BigDecimal purcpric) {
      this.purcpric = purcpric;
    }

    public String getWanCnt() {
      return wanCnt;
    }

    public void setWanCnt(String wanCnt) {
      this.wanCnt = wanCnt;
    }
  }
}
