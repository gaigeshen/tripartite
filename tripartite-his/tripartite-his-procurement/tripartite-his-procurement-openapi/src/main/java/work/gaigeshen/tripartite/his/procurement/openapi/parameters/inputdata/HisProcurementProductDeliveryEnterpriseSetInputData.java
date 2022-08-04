package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementProductDeliveryEnterpriseSetInputData implements HisProcurementInputData {

  public String medinsCode;

  public Collection<ListItem> list;

  public static class ListItem {

    public String hospListId;

    public String delvEntpUscc;

    public String delvEntpName;
  }
}
