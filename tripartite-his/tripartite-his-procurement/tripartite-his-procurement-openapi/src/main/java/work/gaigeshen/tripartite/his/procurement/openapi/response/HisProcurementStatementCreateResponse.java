package work.gaigeshen.tripartite.his.procurement.openapi.response;

/**
 * @author gaigeshen
 */
public class HisProcurementStatementCreateResponse extends AbstractHisProcurementResponse {

  private String pryOrdId;

  public String getPryOrdId() {
    return pryOrdId;
  }

  public void setPryOrdId(String pryOrdId) {
    this.pryOrdId = pryOrdId;
  }
}
