package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.med;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementPurchaseOrderCancelInputData implements HisProcurementInputData {

  private String medinsCode;

  private String purcCode;

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
}
