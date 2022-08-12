package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementPurchaseOrderCreateInputData implements HisProcurementInputData {

  private String medinsCode;

  private BigDecimal planSumamt;

  private String addrId;

  private String conerName;

  private String conerTel;

  private String invottl;

  private Integer chkStas;

  private Collection<ListItem> list;

  public String getMedinsCode() {
    return medinsCode;
  }

  public void setMedinsCode(String medinsCode) {
    this.medinsCode = medinsCode;
  }

  public BigDecimal getPlanSumamt() {
    return planSumamt;
  }

  public void setPlanSumamt(BigDecimal planSumamt) {
    this.planSumamt = planSumamt;
  }

  public String getAddrId() {
    return addrId;
  }

  public void setAddrId(String addrId) {
    this.addrId = addrId;
  }

  public String getConerName() {
    return conerName;
  }

  public void setConerName(String conerName) {
    this.conerName = conerName;
  }

  public String getConerTel() {
    return conerTel;
  }

  public void setConerTel(String conerTel) {
    this.conerTel = conerTel;
  }

  public String getInvottl() {
    return invottl;
  }

  public void setInvottl(String invottl) {
    this.invottl = invottl;
  }

  public Integer getChkStas() {
    return chkStas;
  }

  public void setChkStas(Integer chkStas) {
    this.chkStas = chkStas;
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
  }
}
