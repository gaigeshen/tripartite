package work.gaigeshen.tripartite.his.procurement.openapi.response.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

/**
 * @author gaigeshen
 */
public class HisProcurementStatementCreateResponse extends AbstractHisProcurementResponse {

  private String payOrdId;

  public String getPayOrdId() {
    return payOrdId;
  }

  public void setPayOrdId(String payOrdId) {
    this.payOrdId = payOrdId;
  }
}
