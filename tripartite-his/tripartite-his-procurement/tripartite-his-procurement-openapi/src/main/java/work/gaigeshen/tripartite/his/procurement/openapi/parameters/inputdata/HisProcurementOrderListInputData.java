package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementOrderListInputData implements HisProcurementInputData {

  private String medinsCode;

  private String ordId;

  private String ordDetlId;

  private Integer current;

  private Integer size;

  public String getMedinsCode() {
    return medinsCode;
  }

  public void setMedinsCode(String medinsCode) {
    this.medinsCode = medinsCode;
  }

  public String getOrdId() {
    return ordId;
  }

  public void setOrdId(String ordId) {
    this.ordId = ordId;
  }

  public String getOrdDetlId() {
    return ordDetlId;
  }

  public void setOrdDetlId(String ordDetlId) {
    this.ordDetlId = ordDetlId;
  }

  public Integer getCurrent() {
    return current;
  }

  public void setCurrent(Integer current) {
    this.current = current;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }
}
