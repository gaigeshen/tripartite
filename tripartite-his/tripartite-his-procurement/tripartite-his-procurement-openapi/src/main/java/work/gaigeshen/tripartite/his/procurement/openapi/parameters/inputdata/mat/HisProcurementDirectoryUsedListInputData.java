package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementDirectoryUsedListInputData implements HisProcurementInputData {

  private String mcsCode;

  private String hospListId;

  private Integer current;

  private Integer size;

  public String getMcsCode() {
    return mcsCode;
  }

  public void setMcsCode(String mcsCode) {
    this.mcsCode = mcsCode;
  }

  public String getHospListId() {
    return hospListId;
  }

  public void setHospListId(String hospListId) {
    this.hospListId = hospListId;
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
