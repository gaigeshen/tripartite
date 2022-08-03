package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementPurchaseOrderCreateInputData implements HisProcurementInputData {

  public String medinsCode;

  public BigDecimal planSumamt;

  public String addrId;

  public String conerName;

  public String conerTel;

  public String invottl;

  public Integer chkStas;

  public Collection<ListItem> list;

  public static class ListItem {

    public String hospListId;

    public String delventpCode;

    public String delventpName;

    public BigDecimal purcCnt;

    public String planDetlMemo;

    public String addrId;

    public String hospPurcDetlId;
  }
}
