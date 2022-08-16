package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.med;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementTakeDeliveryInputData implements HisProcurementInputData {

  private String medinsCode;

  private Collection<ListItem> list;

  public String getMedinsCode() {
    return medinsCode;
  }

  public void setMedinsCode(String medinsCode) {
    this.medinsCode = medinsCode;
  }

  public Collection<ListItem> getList() {
    return list;
  }

  public void setList(Collection<ListItem> list) {
    this.list = list;
  }

  public static class ListItem {

    private String shpId;

    private BigDecimal shppCnt;

    public String getShpId() {
      return shpId;
    }

    public void setShpId(String shpId) {
      this.shpId = shpId;
    }

    public BigDecimal getShppCnt() {
      return shppCnt;
    }

    public void setShppCnt(BigDecimal shppCnt) {
      this.shppCnt = shppCnt;
    }
  }
}
