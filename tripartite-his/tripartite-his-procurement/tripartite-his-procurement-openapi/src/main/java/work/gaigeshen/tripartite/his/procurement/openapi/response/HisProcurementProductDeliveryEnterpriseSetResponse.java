package work.gaigeshen.tripartite.his.procurement.openapi.response;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementProductDeliveryEnterpriseSetResponse extends AbstractHisProcurementResponse {

  public Collection<ListItem> dataList;

  public static class ListItem {

    public String hospListId;
  }
}
