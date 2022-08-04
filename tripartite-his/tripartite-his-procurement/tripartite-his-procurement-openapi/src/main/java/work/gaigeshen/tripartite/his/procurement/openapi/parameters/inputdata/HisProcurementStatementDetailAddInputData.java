package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementStatementDetailAddInputData implements HisProcurementInputData {

  public String medinsCode;

  public String payOrdId;

  public Collection<ListItem> list;

  public static class ListItem {

    public String payyDetlId;

    public String stogTag;
  }
}
