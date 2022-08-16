package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementPurchaseOrderDetailAddInputData implements HisProcurementInputData {

  private String medinsCode;

  private String purcCode;

  private Integer chkStas;

  private Integer addorDelStas;

  private Collection<ListItem> list;

  public String getMedinsCode() {
    return medinsCode;
  }

  public void setMedinsCode(String medinsCode) {
    this.medinsCode = medinsCode;
  }

  public String getPurcCode() {
    return purcCode;
  }

  public void setPurcCode(String purcCode) {
    this.purcCode = purcCode;
  }

  public Integer getChkStas() {
    return chkStas;
  }

  public void setChkStas(Integer chkStas) {
    this.chkStas = chkStas;
  }

  public Integer getAddorDelStas() {
    return addorDelStas;
  }

  public void setAddorDelStas(Integer addorDelStas) {
    this.addorDelStas = addorDelStas;
  }

  public Collection<ListItem> getList() {
    return list;
  }

  public void setList(Collection<ListItem> list) {
    this.list = list;
  }

  public static class ListItem {

    private String hospListId;

    private String delventpCode;

    private String delventpName;

    private BigDecimal purcCnt;

    private String planDetlMemo;

    private String addrId;

    private String hospPurcDetlId;

    private String purcPlanDetId;

    public String getHospListId() {
      return hospListId;
    }

    public void setHospListId(String hospListId) {
      this.hospListId = hospListId;
    }

    public String getDelventpCode() {
      return delventpCode;
    }

    public void setDelventpCode(String delventpCode) {
      this.delventpCode = delventpCode;
    }

    public String getDelventpName() {
      return delventpName;
    }

    public void setDelventpName(String delventpName) {
      this.delventpName = delventpName;
    }

    public BigDecimal getPurcCnt() {
      return purcCnt;
    }

    public void setPurcCnt(BigDecimal purcCnt) {
      this.purcCnt = purcCnt;
    }

    public String getPlanDetlMemo() {
      return planDetlMemo;
    }

    public void setPlanDetlMemo(String planDetlMemo) {
      this.planDetlMemo = planDetlMemo;
    }

    public String getAddrId() {
      return addrId;
    }

    public void setAddrId(String addrId) {
      this.addrId = addrId;
    }

    public String getHospPurcDetlId() {
      return hospPurcDetlId;
    }

    public void setHospPurcDetlId(String hospPurcDetlId) {
      this.hospPurcDetlId = hospPurcDetlId;
    }

    public String getPurcPlanDetId() {
      return purcPlanDetId;
    }

    public void setPurcPlanDetId(String purcPlanDetId) {
      this.purcPlanDetId = purcPlanDetId;
    }
  }
}
