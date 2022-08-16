package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.med;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementStatementDetailAddInputData implements HisProcurementInputData {

  private String medinsCode;

  private String payOrdId;

  private Collection<ListItem> list;

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

  public Collection<ListItem> getList() {
    return list;
  }

  public void setList(Collection<ListItem> list) {
    this.list = list;
  }

  public static class ListItem {

    private String payyDetlId;

    public String getPayyDetlId() {
      return payyDetlId;
    }

    public void setPayyDetlId(String payyDetlId) {
      this.payyDetlId = payyDetlId;
    }
  }
}
