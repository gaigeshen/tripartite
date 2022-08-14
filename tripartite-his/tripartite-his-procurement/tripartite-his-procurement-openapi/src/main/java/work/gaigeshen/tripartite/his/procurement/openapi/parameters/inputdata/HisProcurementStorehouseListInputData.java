package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementStorehouseListInputData implements HisProcurementInputData {

  private String orgCode;

  private Integer mcsFlag;

  private Integer current;

  private Integer size;

  public String getOrgCode() {
    return orgCode;
  }

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }

  public Integer getMcsFlag() {
    return mcsFlag;
  }

  public void setMcsFlag(Integer mcsFlag) {
    this.mcsFlag = mcsFlag;
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
