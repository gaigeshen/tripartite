package work.gaigeshen.tripartite.his.procurement.openapi.response;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementDirectoryUsedListResponse extends AbstractHisProcurementResponse {

  public Collection<ListItem> dataList;

  public Integer currentPageNumber;

  public Integer totalPageCount;

  public Integer totalRecordCount;

  public static class ListItem {

    public String zclassName;

    public String mcsCode;

    public String mcsRegno;

    public String regcert;

    public String minpacuntName;

    public String mcsMol;

    public String prxyEntpCode;

    public String prxyEntpName;

    public String mcsSpec;

    public String hospListId;

    public BigDecimal hospPurcPric;

    public String prodentpCode;

    public String prodentpName;

    public String delventpName;

    public String mcsInfoId;

    public String pubonlnRsltId;

    public String secondDirectory;

    public String primDirectory;

    public String prodName;

    public BigDecimal pubonlnPric;

    public String delventpCode;
  }
}
