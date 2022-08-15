package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementDirectoryAddInputData implements HisProcurementInputData {

  private Collection<ListItem> list;

  public Collection<ListItem> getList() {
    return list;
  }

  public void setList(Collection<ListItem> list) {
    this.list = list;
  }

  public static class ListItem {

    private String pubonlnId;

    public String getPubonlnId() {
      return pubonlnId;
    }

    public void setPubonlnId(String pubonlnId) {
      this.pubonlnId = pubonlnId;
    }
  }
}
