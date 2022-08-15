package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementProductDeliveryEnterpriseSetInputData implements HisProcurementInputData {

  private String medinsCode;

  private Collection<ListItem> list;

  public static class ListItem {

    private String hospListId;

    private String delvEntpUscc;

    private String delvEntpName;

    public String getHospListId() {
      return hospListId;
    }

    public void setHospListId(String hospListId) {
      this.hospListId = hospListId;
    }

    public String getDelvEntpUscc() {
      return delvEntpUscc;
    }

    public void setDelvEntpUscc(String delvEntpUscc) {
      this.delvEntpUscc = delvEntpUscc;
    }

    public String getDelvEntpName() {
      return delvEntpName;
    }

    public void setDelvEntpName(String delvEntpName) {
      this.delvEntpName = delvEntpName;
    }
  }
}
