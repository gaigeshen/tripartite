package work.gaigeshen.tripartite.his.procurement.openapi.response;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementPurchaseOrderSendResponse extends AbstractHisProcurementResponse {

  public String PurcCode;

  public String purcPlanCode;

  public Collection<OrdDetlIdListItem> ordDetlIdList;

  public Collection<OrdIdListItem> ordIdList;

  public static class OrdDetlIdListItem {

    public String ordCode;

    public String ordDetlId;

    public String purcPlanDetId;

    public String ordId;
  }

  public static class OrdIdListItem {

    public String ordCode;

    public String ordId;
  }
}
