package work.gaigeshen.tripartite.his.procurement.openapi.response.med;

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

    private String shppEndTime;

    private String ordDetlId;

    private BigDecimal purcCnt;

    private String memo;

    private String cnclTime;

    private String hospListId;

    private String conerTel;

    private String ordId;

    private String stroomName;

    private String purcPlanCode;

    private String conerName;

    private String ordCode;

    private String shppStas;

    private String invottl;

    private String prodName;

    private String shpStas;

    private String medinsName;

    private String delventpCode;

    private String addr;

    private String shpTime;

    private String itemname;

    private String responseTime;

    private String ordDetlStas;

    private String responseStatus;

    private String sendTime;

    private String delventpName;

    private String delventpCnfmShppTime;

    private String shpEndTime;

    private BigDecimal ordSumamt;

    private String docmker;

    private String medinsCode;

    private BigDecimal purcpric;

    private String responseRemake;

    public String getShppEndTime() {
      return shppEndTime;
    }

    public void setShppEndTime(String shppEndTime) {
      this.shppEndTime = shppEndTime;
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

    public String getMemo() {
      return memo;
    }

    public void setMemo(String memo) {
      this.memo = memo;
    }

    public String getCnclTime() {
      return cnclTime;
    }

    public void setCnclTime(String cnclTime) {
      this.cnclTime = cnclTime;
    }

    public String getHospListId() {
      return hospListId;
    }

    public void setHospListId(String hospListId) {
      this.hospListId = hospListId;
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

    public String getStroomName() {
      return stroomName;
    }

    public void setStroomName(String stroomName) {
      this.stroomName = stroomName;
    }

    public String getPurcPlanCode() {
      return purcPlanCode;
    }

    public void setPurcPlanCode(String purcPlanCode) {
      this.purcPlanCode = purcPlanCode;
    }

    public String getConerName() {
      return conerName;
    }

    public void setConerName(String conerName) {
      this.conerName = conerName;
    }

    public String getOrdCode() {
      return ordCode;
    }

    public void setOrdCode(String ordCode) {
      this.ordCode = ordCode;
    }

    public String getShppStas() {
      return shppStas;
    }

    public void setShppStas(String shppStas) {
      this.shppStas = shppStas;
    }

    public String getInvottl() {
      return invottl;
    }

    public void setInvottl(String invottl) {
      this.invottl = invottl;
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

    public String getAddr() {
      return addr;
    }

    public void setAddr(String addr) {
      this.addr = addr;
    }

    public String getShpTime() {
      return shpTime;
    }

    public void setShpTime(String shpTime) {
      this.shpTime = shpTime;
    }

    public String getItemname() {
      return itemname;
    }

    public void setItemname(String itemname) {
      this.itemname = itemname;
    }

    public String getResponseTime() {
      return responseTime;
    }

    public void setResponseTime(String responseTime) {
      this.responseTime = responseTime;
    }

    public String getOrdDetlStas() {
      return ordDetlStas;
    }

    public void setOrdDetlStas(String ordDetlStas) {
      this.ordDetlStas = ordDetlStas;
    }

    public String getResponseStatus() {
      return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
      this.responseStatus = responseStatus;
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

    public String getDelventpCnfmShppTime() {
      return delventpCnfmShppTime;
    }

    public void setDelventpCnfmShppTime(String delventpCnfmShppTime) {
      this.delventpCnfmShppTime = delventpCnfmShppTime;
    }

    public String getShpEndTime() {
      return shpEndTime;
    }

    public void setShpEndTime(String shpEndTime) {
      this.shpEndTime = shpEndTime;
    }

    public BigDecimal getOrdSumamt() {
      return ordSumamt;
    }

    public void setOrdSumamt(BigDecimal ordSumamt) {
      this.ordSumamt = ordSumamt;
    }

    public String getDocmker() {
      return docmker;
    }

    public void setDocmker(String docmker) {
      this.docmker = docmker;
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

    public String getResponseRemake() {
      return responseRemake;
    }

    public void setResponseRemake(String responseRemake) {
      this.responseRemake = responseRemake;
    }
  }
}
