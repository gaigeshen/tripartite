package work.gaigeshen.tripartite.his.procurement.openapi.response.med;

import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementPurchaseOrderListResponse extends AbstractHisProcurementResponse {

  private Collection<ListItem> data;

  public Collection<ListItem> getData() {
    return data;
  }

  public void setData(Collection<ListItem> data) {
    this.data = data;
  }

  public static class ListItem {

    private String purcPlanId;

    private String planDetlMemo;

    private String itemname;

    private BigDecimal purcCnt;

    private String pacMatl;

    private String purcPlanName;

    private String purcPlanDetId;

    private BigDecimal highSelLmtpric;

    private String prodSpec;

    private String delventpName;

    private String purcType;

    private String dosform;

    private String prodCode;

    private String hiPayPric;

    private String prodName;

    private String addrId;

    private BigDecimal pubonlnPric;

    private String addr;

    private String purcpric;

    public String getPurcPlanId() {
      return purcPlanId;
    }

    public void setPurcPlanId(String purcPlanId) {
      this.purcPlanId = purcPlanId;
    }

    public String getPlanDetlMemo() {
      return planDetlMemo;
    }

    public void setPlanDetlMemo(String planDetlMemo) {
      this.planDetlMemo = planDetlMemo;
    }

    public String getItemname() {
      return itemname;
    }

    public void setItemname(String itemname) {
      this.itemname = itemname;
    }

    public BigDecimal getPurcCnt() {
      return purcCnt;
    }

    public void setPurcCnt(BigDecimal purcCnt) {
      this.purcCnt = purcCnt;
    }

    public String getPacMatl() {
      return pacMatl;
    }

    public void setPacMatl(String pacMatl) {
      this.pacMatl = pacMatl;
    }

    public String getPurcPlanName() {
      return purcPlanName;
    }

    public void setPurcPlanName(String purcPlanName) {
      this.purcPlanName = purcPlanName;
    }

    public String getPurcPlanDetId() {
      return purcPlanDetId;
    }

    public void setPurcPlanDetId(String purcPlanDetId) {
      this.purcPlanDetId = purcPlanDetId;
    }

    public BigDecimal getHighSelLmtpric() {
      return highSelLmtpric;
    }

    public void setHighSelLmtpric(BigDecimal highSelLmtpric) {
      this.highSelLmtpric = highSelLmtpric;
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

    public String getPurcType() {
      return purcType;
    }

    public void setPurcType(String purcType) {
      this.purcType = purcType;
    }

    public String getDosform() {
      return dosform;
    }

    public void setDosform(String dosform) {
      this.dosform = dosform;
    }

    public String getProdCode() {
      return prodCode;
    }

    public void setProdCode(String prodCode) {
      this.prodCode = prodCode;
    }

    public String getHiPayPric() {
      return hiPayPric;
    }

    public void setHiPayPric(String hiPayPric) {
      this.hiPayPric = hiPayPric;
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

    public BigDecimal getPubonlnPric() {
      return pubonlnPric;
    }

    public void setPubonlnPric(BigDecimal pubonlnPric) {
      this.pubonlnPric = pubonlnPric;
    }

    public String getAddr() {
      return addr;
    }

    public void setAddr(String addr) {
      this.addr = addr;
    }

    public String getPurcpric() {
      return purcpric;
    }

    public void setPurcpric(String purcpric) {
      this.purcpric = purcpric;
    }
  }
}
