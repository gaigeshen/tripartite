package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementPurchaseOrderDetailAddInputData implements HisProcurementInputData {

  public String medinsCode;

  public String purcCode;

  public Integer chkStas;

  public Integer addorDelStas;

  public Collection<ListItem> list;

  public static class ListItem {

    public String hospListId;

    public String delventpCode;

    public String delventpName;

    public BigDecimal purcCnt;

    public String planDetlMemo;

    public String addrId;

    public String hospPurcDetlId;

    public String purcPlanDetId;
  }
}
