package work.gaigeshen.tripartite.his.procurement.openapi.response;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementDirectoryListResponse extends AbstractHisProcurementResponse {

  public Collection<ListItem> dataList;

  public Integer currentPageNumber;

  public Integer totalPageCount;

  public Integer totalRecordCount;

  public static class ListItem {

    public String mcsCode;

    public String prodName;

    public String mcsRegno;

    public String mcsRegcertName;

    public String prodSpec;

    public String mcsMol;

    public String zclassCode;

    public String zclassName;

    public String primDirectory;

    public String secondDirectory;

    public String prodentpCode;

    public String prxyEntpCode;

    public String prodentpName;

    public BigDecimal pubonlnPric;

    public String mcsType;

    public String origin;

    public String unt;

    public String pubonlnType;

    public String tenditmId;

    public String tenditmName;
  }
}
