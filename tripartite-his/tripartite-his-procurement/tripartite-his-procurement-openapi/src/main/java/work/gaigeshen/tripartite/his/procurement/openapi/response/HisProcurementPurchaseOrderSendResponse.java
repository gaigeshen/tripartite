package work.gaigeshen.tripartite.his.procurement.openapi.response;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementPurchaseOrderSendResponse extends AbstractHisProcurementResponse {

  public String PurcCode;

  public String purcPlanCode;

  public Collection<DetailListItem> ordDetlIdList;

  public static class DetailListItem {

    public String ordCode;

    public String ordDetlId;

    public String purcPlanDetId;

    public String ordId;
  }
}
