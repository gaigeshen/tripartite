package work.gaigeshen.tripartite.his.procurement.openapi.response.med;

import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementShipmentListResponse extends AbstractHisProcurementResponse {

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

    private BigDecimal shpPric;

    private String outstoTime;

    private BigDecimal shpCnt;

    private String invoicePrimaryIds;

    private String ordDetlId;

    private BigDecimal purcCnt;

    private BigDecimal retnCnt;

    private String cnclTime;

    private String prodId;

    private BigDecimal avlShpCnt;

    private String expyEndtime;

    private String hospListId;

    private String ordId;

    private String admorgCode;

    private String purcPlanCode;

    private String ordCode;

    private String hospItemId;

    private BigDecimal shppCnt;

    private String prodName;

    private String shpStas;

    private String medinsName;

    private String delventpCode;

    private String shpTime;

    private String manuLotnum;

    private String shppTime;

    private String cnclType;

    private String itemname;

    private String shpMemo;

    private BigDecimal purcAmt;

    private BigDecimal rtnbCnt;

    private BigDecimal shpAmt;

    private String shpCode;

    private String invoiceCode;

    private String sendTime;

    private String delventpName;

    private BigDecimal shppAmt;

    private String pubonlnRsltId;

    private String shpId;

    private String delventpCnfmShppTime;

    private String delventpCnfmShppStas;

    private BigDecimal ordSumamt;

    private String invoiceId;

    private String medinsCode;

    private String admorgName;

    private BigDecimal purcpric;

    public String getPlanDetlMemo() {
      return planDetlMemo;
    }

    public void setPlanDetlMemo(String planDetlMemo) {
      this.planDetlMemo = planDetlMemo;
    }

    public BigDecimal getShpPric() {
      return shpPric;
    }

    public void setShpPric(BigDecimal shpPric) {
      this.shpPric = shpPric;
    }

    public String getOutstoTime() {
      return outstoTime;
    }

    public void setOutstoTime(String outstoTime) {
      this.outstoTime = outstoTime;
    }

    public BigDecimal getShpCnt() {
      return shpCnt;
    }

    public void setShpCnt(BigDecimal shpCnt) {
      this.shpCnt = shpCnt;
    }

    public String getInvoicePrimaryIds() {
      return invoicePrimaryIds;
    }

    public void setInvoicePrimaryIds(String invoicePrimaryIds) {
      this.invoicePrimaryIds = invoicePrimaryIds;
    }

    public String getOrdDetlId() {
      return ordDetlId;
    }

    public void setOrdDetlId(String ordDetlId) {
      this.ordDetlId = ordDetlId;
    }

    public BigDecimal getPurcCnt() {
      return purcCnt;
    }

    public void setPurcCnt(BigDecimal purcCnt) {
      this.purcCnt = purcCnt;
    }

    public BigDecimal getRetnCnt() {
      return retnCnt;
    }

    public void setRetnCnt(BigDecimal retnCnt) {
      this.retnCnt = retnCnt;
    }

    public String getCnclTime() {
      return cnclTime;
    }

    public void setCnclTime(String cnclTime) {
      this.cnclTime = cnclTime;
    }

    public String getProdId() {
      return prodId;
    }

    public void setProdId(String prodId) {
      this.prodId = prodId;
    }

    public BigDecimal getAvlShpCnt() {
      return avlShpCnt;
    }

    public void setAvlShpCnt(BigDecimal avlShpCnt) {
      this.avlShpCnt = avlShpCnt;
    }

    public String getExpyEndtime() {
      return expyEndtime;
    }

    public void setExpyEndtime(String expyEndtime) {
      this.expyEndtime = expyEndtime;
    }

    public String getHospListId() {
      return hospListId;
    }

    public void setHospListId(String hospListId) {
      this.hospListId = hospListId;
    }

    public String getOrdId() {
      return ordId;
    }

    public void setOrdId(String ordId) {
      this.ordId = ordId;
    }

    public String getAdmorgCode() {
      return admorgCode;
    }

    public void setAdmorgCode(String admorgCode) {
      this.admorgCode = admorgCode;
    }

    public String getPurcPlanCode() {
      return purcPlanCode;
    }

    public void setPurcPlanCode(String purcPlanCode) {
      this.purcPlanCode = purcPlanCode;
    }

    public String getOrdCode() {
      return ordCode;
    }

    public void setOrdCode(String ordCode) {
      this.ordCode = ordCode;
    }

    public String getHospItemId() {
      return hospItemId;
    }

    public void setHospItemId(String hospItemId) {
      this.hospItemId = hospItemId;
    }

    public BigDecimal getShppCnt() {
      return shppCnt;
    }

    public void setShppCnt(BigDecimal shppCnt) {
      this.shppCnt = shppCnt;
    }

    public String getProdName() {
      return prodName;
    }

    public void setProdName(String prodName) {
      this.prodName = prodName;
    }

    public String getShpStas() {
      return shpStas;
    }

    public void setShpStas(String shpStas) {
      this.shpStas = shpStas;
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

    public String getShpTime() {
      return shpTime;
    }

    public void setShpTime(String shpTime) {
      this.shpTime = shpTime;
    }

    public String getManuLotnum() {
      return manuLotnum;
    }

    public void setManuLotnum(String manuLotnum) {
      this.manuLotnum = manuLotnum;
    }

    public String getShppTime() {
      return shppTime;
    }

    public void setShppTime(String shppTime) {
      this.shppTime = shppTime;
    }

    public String getCnclType() {
      return cnclType;
    }

    public void setCnclType(String cnclType) {
      this.cnclType = cnclType;
    }

    public String getItemname() {
      return itemname;
    }

    public void setItemname(String itemname) {
      this.itemname = itemname;
    }

    public String getShpMemo() {
      return shpMemo;
    }

    public void setShpMemo(String shpMemo) {
      this.shpMemo = shpMemo;
    }

    public BigDecimal getPurcAmt() {
      return purcAmt;
    }

    public void setPurcAmt(BigDecimal purcAmt) {
      this.purcAmt = purcAmt;
    }

    public BigDecimal getRtnbCnt() {
      return rtnbCnt;
    }

    public void setRtnbCnt(BigDecimal rtnbCnt) {
      this.rtnbCnt = rtnbCnt;
    }

    public BigDecimal getShpAmt() {
      return shpAmt;
    }

    public void setShpAmt(BigDecimal shpAmt) {
      this.shpAmt = shpAmt;
    }

    public String getShpCode() {
      return shpCode;
    }

    public void setShpCode(String shpCode) {
      this.shpCode = shpCode;
    }

    public String getInvoiceCode() {
      return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
      this.invoiceCode = invoiceCode;
    }

    public String getSendTime() {
      return sendTime;
    }

    public void setSendTime(String sendTime) {
      this.sendTime = sendTime;
    }

    public String getDelventpName() {
      return delventpName;
    }

    public void setDelventpName(String delventpName) {
      this.delventpName = delventpName;
    }

    public BigDecimal getShppAmt() {
      return shppAmt;
    }

    public void setShppAmt(BigDecimal shppAmt) {
      this.shppAmt = shppAmt;
    }

    public String getPubonlnRsltId() {
      return pubonlnRsltId;
    }

    public void setPubonlnRsltId(String pubonlnRsltId) {
      this.pubonlnRsltId = pubonlnRsltId;
    }

    public String getShpId() {
      return shpId;
    }

    public void setShpId(String shpId) {
      this.shpId = shpId;
    }

    public String getDelventpCnfmShppTime() {
      return delventpCnfmShppTime;
    }

    public void setDelventpCnfmShppTime(String delventpCnfmShppTime) {
      this.delventpCnfmShppTime = delventpCnfmShppTime;
    }

    public String getDelventpCnfmShppStas() {
      return delventpCnfmShppStas;
    }

    public void setDelventpCnfmShppStas(String delventpCnfmShppStas) {
      this.delventpCnfmShppStas = delventpCnfmShppStas;
    }

    public BigDecimal getOrdSumamt() {
      return ordSumamt;
    }

    public void setOrdSumamt(BigDecimal ordSumamt) {
      this.ordSumamt = ordSumamt;
    }

    public String getInvoiceId() {
      return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
      this.invoiceId = invoiceId;
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

    public BigDecimal getPurcpric() {
      return purcpric;
    }

    public void setPurcpric(BigDecimal purcpric) {
      this.purcpric = purcpric;
    }
  }
}
