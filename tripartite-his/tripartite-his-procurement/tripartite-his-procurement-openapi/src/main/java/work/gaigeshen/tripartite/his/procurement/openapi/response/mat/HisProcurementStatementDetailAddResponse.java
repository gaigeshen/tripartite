package work.gaigeshen.tripartite.his.procurement.openapi.response.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementStatementDetailAddResponse extends AbstractHisProcurementResponse {

  private Collection<String> dataList;

  public Collection<String> getDataList() {
    return dataList;
  }

  public void setDataList(Collection<String> dataList) {
    this.dataList = dataList;
  }
}