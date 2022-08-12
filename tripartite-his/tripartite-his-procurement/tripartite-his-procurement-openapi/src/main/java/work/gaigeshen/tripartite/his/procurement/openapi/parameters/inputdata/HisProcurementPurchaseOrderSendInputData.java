package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementPurchaseOrderSendInputData implements HisProcurementInputData {

  private String medinsCode;

  private String purcCode;

  private Integer chkStas;

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
}
