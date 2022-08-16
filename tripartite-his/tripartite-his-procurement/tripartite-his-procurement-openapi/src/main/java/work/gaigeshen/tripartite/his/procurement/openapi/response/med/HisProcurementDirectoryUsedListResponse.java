package work.gaigeshen.tripartite.his.procurement.openapi.response.med;

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

    private String itemCodg;

    private BigDecimal convrat;

    private String medinsListCodg;

    private String prodId;

    private String hospListId;

    private BigDecimal hospPurcPric;

    private String AddDateTime;

    private String admorgCode;

    private String aprvno;

    private String dosform;

    private String prodName;

    private String medinsName;

    private String delventpCode;

    private String itemname;

    private String drugInfoId;

    private String prodBigPac;

    private String prodSpec;

    private String delventpName;

    private String prodCode;

    private String pubonlnRsltId;

    private String pubonlnStas;

    private String prodPac;

    private String UpdateTime;

    private String pacmatl;

    private BigDecimal pubonlnPric;

    private String hospBidprcuItemId;

    private String prodSoucName;

    private String medinsCode;

    private String admorgName;

    public String getItemCodg() {
      return itemCodg;
    }

    public void setItemCodg(String itemCodg) {
      this.itemCodg = itemCodg;
    }

    public BigDecimal getConvrat() {
      return convrat;
    }

    public void setConvrat(BigDecimal convrat) {
      this.convrat = convrat;
    }

    public String getMedinsListCodg() {
      return medinsListCodg;
    }

    public void setMedinsListCodg(String medinsListCodg) {
      this.medinsListCodg = medinsListCodg;
    }

    public String getProdId() {
      return prodId;
    }

    public void setProdId(String prodId) {
      this.prodId = prodId;
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

    public String getAddDateTime() {
      return AddDateTime;
    }

    public void setAddDateTime(String addDateTime) {
      AddDateTime = addDateTime;
    }

    public String getAdmorgCode() {
      return admorgCode;
    }

    public void setAdmorgCode(String admorgCode) {
      this.admorgCode = admorgCode;
    }

    public String getAprvno() {
      return aprvno;
    }

    public void setAprvno(String aprvno) {
      this.aprvno = aprvno;
    }

    public String getDosform() {
      return dosform;
    }

    public void setDosform(String dosform) {
      this.dosform = dosform;
    }

    public String getProdName() {
      return prodName;
    }

    public void setProdName(String prodName) {
      this.prodName = prodName;
    }

    public String getMedinsName() {
      return medinsName;
    }

    public void setMedinsName(String medinsName) {
      this.medinsName = medinsName;
    }

    public String getDelventpCode() {
      return delventpCode;
    }

    public void setDelventpCode(String delventpCode) {
      this.delventpCode = delventpCode;
    }

    public String getItemname() {
      return itemname;
    }

    public void setItemname(String itemname) {
      this.itemname = itemname;
    }

    public String getDrugInfoId() {
      return drugInfoId;
    }

    public void setDrugInfoId(String drugInfoId) {
      this.drugInfoId = drugInfoId;
    }

    public String getProdBigPac() {
      return prodBigPac;
    }

    public void setProdBigPac(String prodBigPac) {
      this.prodBigPac = prodBigPac;
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

    public String getProdCode() {
      return prodCode;
    }

    public void setProdCode(String prodCode) {
      this.prodCode = prodCode;
    }

    public String getPubonlnRsltId() {
      return pubonlnRsltId;
    }

    public void setPubonlnRsltId(String pubonlnRsltId) {
      this.pubonlnRsltId = pubonlnRsltId;
    }

    public String getPubonlnStas() {
      return pubonlnStas;
    }

    public void setPubonlnStas(String pubonlnStas) {
      this.pubonlnStas = pubonlnStas;
    }

    public String getProdPac() {
      return prodPac;
    }

    public void setProdPac(String prodPac) {
      this.prodPac = prodPac;
    }

    public String getUpdateTime() {
      return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
      UpdateTime = updateTime;
    }

    public String getPacmatl() {
      return pacmatl;
    }

    public void setPacmatl(String pacmatl) {
      this.pacmatl = pacmatl;
    }

    public BigDecimal getPubonlnPric() {
      return pubonlnPric;
    }

    public void setPubonlnPric(BigDecimal pubonlnPric) {
      this.pubonlnPric = pubonlnPric;
    }

    public String getHospBidprcuItemId() {
      return hospBidprcuItemId;
    }

    public void setHospBidprcuItemId(String hospBidprcuItemId) {
      this.hospBidprcuItemId = hospBidprcuItemId;
    }

    public String getProdSoucName() {
      return prodSoucName;
    }

    public void setProdSoucName(String prodSoucName) {
      this.prodSoucName = prodSoucName;
    }

    public String getMedinsCode() {
      return medinsCode;
    }

    public void setMedinsCode(String medinsCode) {
      this.medinsCode = medinsCode;
    }

    public String getAdmorgName() {
      return admorgName;
    }

    public void setAdmorgName(String admorgName) {
      this.admorgName = admorgName;
    }
  }
}
