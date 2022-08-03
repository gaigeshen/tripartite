package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementDirectoryAddInputData implements HisProcurementInputData {

  public Collection<ListItem> list;

  public static class ListItem {

    public String pubonlnId;
  }
}
