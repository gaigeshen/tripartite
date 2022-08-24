package work.gaigeshen.tripartite.his.procurement.openapi.response.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementStatementDetailAddResponse extends AbstractHisProcurementResponse {

  private Collection<ListItem> dataList;

  public Collection<ListItem> getDataList() {
    return dataList;
  }

  public void setDataList(Collection<ListItem> dataList) {
    this.dataList = dataList;
  }

  public static class ListItem {

    private Integer returnCode;

    private String returnMsg;

    private String payyDetlId;

    public Integer getReturnCode() {
      return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
      this.returnCode = returnCode;
    }

    public String getReturnMsg() {
      return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
      this.returnMsg = returnMsg;
    }

    public String getPayyDetlId() {
      return payyDetlId;
    }

    public void setPayyDetlId(String payyDetlId) {
      this.payyDetlId = payyDetlId;
    }
  }
}
