package work.gaigeshen.tripartite.his.procurement.openapi.response;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementReturnListResponse extends AbstractHisProcurementResponse {

  public Integer currentPageNumber;

  public Integer totalPageCount;

  public Integer totalRecordCount;

  public Collection<ListItem> dataList;

  public static class ListItem {

    public String regcert;

    public String retnCode;

    public String rgtMol;

    public String purcCnt;

    public String medinsRetnRea;

    public String prodentpName;

    public String ordCode;

    public String prodName;

    public String prodMatl;

    public String delventpCode;

    public String manuLotnum;

    public String medinsRetnTime;

    public String mcsRegno;

    public String delventpFailRea;

    public String delventpPassTime;

    public String pacMatl;

    public String prodSpec;

    public BigDecimal retnCnt;

    public BigDecimal retnAmt;

    public String delventpName;

    public String delventpFailTime;

    public String retnChkStas;

    public String prodPac;

    public String returnInvoiceId;

    public String pubonlnPric;

    public String dclaEntpName;

    public String purcpric;
  }
}
