package work.gaigeshen.tripartite.his.procurement.openapi.response;

/**
 * @author gaigeshen
 */
public class HisProcurementPurchaseOrderCancelResponse extends AbstractHisProcurementResponse {

  private String PurcCode;

  public String getPurcCode() {
    return PurcCode;
  }

  public void setPurcCode(String purcCode) {
    PurcCode = purcCode;
  }
}
