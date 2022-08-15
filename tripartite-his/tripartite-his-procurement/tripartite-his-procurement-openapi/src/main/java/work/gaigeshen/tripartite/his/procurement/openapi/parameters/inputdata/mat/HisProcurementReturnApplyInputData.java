package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.math.BigDecimal;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementReturnApplyInputData implements HisProcurementInputData {

  private String medinsCode;

  private String shpCode;

  private BigDecimal retnCnt;

  private String medinsRetnRea;

  public String getMedinsCode() {
    return medinsCode;
  }

  public void setMedinsCode(String medinsCode) {
    this.medinsCode = medinsCode;
  }

  public String getShpCode() {
    return shpCode;
  }

  public void setShpCode(String shpCode) {
    this.shpCode = shpCode;
  }

  public BigDecimal getRetnCnt() {
    return retnCnt;
  }

  public void setRetnCnt(BigDecimal retnCnt) {
    this.retnCnt = retnCnt;
  }

  public String getMedinsRetnRea() {
    return medinsRetnRea;
  }

  public void setMedinsRetnRea(String medinsRetnRea) {
    this.medinsRetnRea = medinsRetnRea;
  }
}
