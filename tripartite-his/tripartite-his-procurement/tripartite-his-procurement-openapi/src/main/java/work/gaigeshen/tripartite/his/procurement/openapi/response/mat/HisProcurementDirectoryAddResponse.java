package work.gaigeshen.tripartite.his.procurement.openapi.response.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementDirectoryAddResponse extends AbstractHisProcurementResponse {

  public Collection<ListItem> dataList;

  public Collection<ListItem> getDataList() {
    return dataList;
  }

  public void setDataList(Collection<ListItem> dataList) {
    this.dataList = dataList;
  }

  public static class ListItem {

    private String pubonlnId;

    private String hospListId;

    public String getPubonlnId() {
      return pubonlnId;
    }

    public void setPubonlnId(String pubonlnId) {
      this.pubonlnId = pubonlnId;
    }

    public String getHospListId() {
      return hospListId;
    }

    public void setHospListId(String hospListId) {
      this.hospListId = hospListId;
    }
  }

}
