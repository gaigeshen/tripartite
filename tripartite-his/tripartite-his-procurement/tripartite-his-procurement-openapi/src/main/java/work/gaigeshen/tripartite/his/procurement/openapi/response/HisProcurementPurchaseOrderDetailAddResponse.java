package work.gaigeshen.tripartite.his.procurement.openapi.response;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementPurchaseOrderDetailAddResponse extends AbstractHisProcurementResponse {

  public String PurcCode;

  public BigDecimal planSumamt;

  public Collection<ListItem> dataList;

  public static class ListItem {

    public Integer returnCode;

    public String returnMsg;

    public String hospPurcDetlId;

    public String purcPlanDetId;
  }
}
