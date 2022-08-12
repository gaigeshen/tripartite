package work.gaigeshen.tripartite.his.procurement.openapi.response;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementProductDeliveryEnterpriseSetResponse extends AbstractHisProcurementResponse {

  private Collection<ListItem> dataList;

  public Collection<ListItem> getDataList() {
    return dataList;
  }

  public void setDataList(Collection<ListItem> dataList) {
    this.dataList = dataList;
  }

  public static class ListItem {

    private String hospListId;

    public String getHospListId() {
      return hospListId;
    }

    public void setHospListId(String hospListId) {
      this.hospListId = hospListId;
    }
  }
}
