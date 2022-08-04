package work.gaigeshen.tripartite.his.procurement.openapi.response;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementPurchaseOrderListResponse extends AbstractHisProcurementResponse {

  public Collection<ListItem> dataList;

  public Integer currentPageNumber;

  public Integer totalPageCount;

  public Integer totalRecordCount;

  public static class ListItem {

    public String planDetlMemo;

    public String regcert;

    public String rgtMol;

    public String addTime;

    public String mcsType;

    public String ordDetlId;

    public String purcCnt;

    public String conerTel;

    public String ordId;

    public String subTime;

    public String prodentpName;

    public String purcPlanCode;

    public String wanTime;

    public String conerName;

    public String disYN;

    public String ordCode;

    public String prodName;

    public String addrId;

    public String disCnt;

    public String prodMatl;

    public String delventpCode;

    public String mcsRegno;

    public String disTime;

    public String discode;

    public String itemname;

    public String pacMatl;

    public String proTypeStock;

    public String prodSpec;

    public String delventpName;

    public String pubonlnRsltId;

    public String prodPac;

    public String pubonlnPric;

    public String qlv;

    public String dclaEntpName;

    public String medinsCode;

    public BigDecimal purcpric;

    public String wanCnt;
  }
}
