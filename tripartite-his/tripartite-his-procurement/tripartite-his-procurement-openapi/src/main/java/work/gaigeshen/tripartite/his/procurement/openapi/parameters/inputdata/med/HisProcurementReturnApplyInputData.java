package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.med;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementReturnApplyInputData implements HisProcurementInputData {

  private String medinsCode;

  private String retnRea;

  private Collection<ListItem> list;

  public Collection<ListItem> getList() {
    return list;
  }

  public void setList(Collection<ListItem> list) {
    this.list = list;
  }

  public String getMedinsCode() {
    return medinsCode;
  }

  public void setMedinsCode(String medinsCode) {
    this.medinsCode = medinsCode;
  }

  public String getRetnRea() {
    return retnRea;
  }

  public void setRetnRea(String retnRea) {
    this.retnRea = retnRea;
  }

  public static class ListItem {

    private String shpId;

    private BigDecimal retnCnt;

    public String getShpId() {
      return shpId;
    }

    public void setShpId(String shpId) {
      this.shpId = shpId;
    }

    public BigDecimal getRetnCnt() {
      return retnCnt;
    }

    public void setRetnCnt(BigDecimal retnCnt) {
      this.retnCnt = retnCnt;
    }
  }
}
