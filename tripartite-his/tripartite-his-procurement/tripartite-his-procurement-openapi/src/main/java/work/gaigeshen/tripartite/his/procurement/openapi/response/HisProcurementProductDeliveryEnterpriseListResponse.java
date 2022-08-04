package work.gaigeshen.tripartite.his.procurement.openapi.response;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementProductDeliveryEnterpriseListResponse extends AbstractHisProcurementResponse {

  public Collection<ListItem> dataList;

  public Integer currentPageNumber;

  public Integer totalPageCount;

  public Integer totalRecordCount;

  public static class ListItem {

    public String delventpName;

    public String admdvsName;

    public String prodCode;

    public String prodEntpName;

    public String delvEntpUscc;

    public String prodEntpUscc;

    public String admdvs;
  }
}
