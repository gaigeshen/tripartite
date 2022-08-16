package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.med;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementStatementExamineInputData implements HisProcurementInputData {

  private String medinsCode;

  private String payOrdId;

  private String chkOpen;

  private Integer chkStas;

  public String getMedinsCode() {
    return medinsCode;
  }

  public void setMedinsCode(String medinsCode) {
    this.medinsCode = medinsCode;
  }

  public String getPayOrdId() {
    return payOrdId;
  }

  public void setPayOrdId(String payOrdId) {
    this.payOrdId = payOrdId;
  }

  public String getChkOpen() {
    return chkOpen;
  }

  public void setChkOpen(String chkOpen) {
    this.chkOpen = chkOpen;
  }

  public Integer getChkStas() {
    return chkStas;
  }

  public void setChkStas(Integer chkStas) {
    this.chkStas = chkStas;
  }
}
