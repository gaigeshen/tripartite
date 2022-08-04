package work.gaigeshen.tripartite.his.procurement.openapi.response;

import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementStorehouseListResponse extends AbstractHisProcurementResponse {

  public Integer currentPageNumber;

  public Integer totalPageCount;

  public Integer totalRecordCount;

  public Collection<ListItem> dataList;

  public static class ListItem {

    public String conerName;

    public String entpCode;

    public String mcsFlag;

    public String defFlag;

    public String prov;

    public String city;

    public String coty;

    public String invottl;

    public String addrId;

    public String addr;

    public String conerTel;

    public String stroomName;
  }
}
