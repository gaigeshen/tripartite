package work.gaigeshen.tripartite.his.procurement.openapi.response;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
public class HisProcurementPurchaseOrderCreateResponse extends AbstractHisProcurementResponse {

  private String PurcCode;

  private BigDecimal planSumamt;

  private Collection<ListItem> dataList;

  public String getPurcCode() {
    return PurcCode;
  }

  public void setPurcCode(String purcCode) {
    PurcCode = purcCode;
  }

  public BigDecimal getPlanSumamt() {
    return planSumamt;
  }

  public void setPlanSumamt(BigDecimal planSumamt) {
    this.planSumamt = planSumamt;
  }

  public Collection<ListItem> getDataList() {
    return dataList;
  }

  public void setDataList(Collection<ListItem> dataList) {
    this.dataList = dataList;
  }

  public static class ListItem {

    private Integer returnCode;

    private String returnMsg;

    private String hospPurcDetlId;

    private String purcPlanDetId;

    public Integer getReturnCode() {
      return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
      this.returnCode = returnCode;
    }

    public String getReturnMsg() {
      return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
      this.returnMsg = returnMsg;
    }

    public String getHospPurcDetlId() {
      return hospPurcDetlId;
    }

    public void setHospPurcDetlId(String hospPurcDetlId) {
      this.hospPurcDetlId = hospPurcDetlId;
    }

    public String getPurcPlanDetId() {
      return purcPlanDetId;
    }

    public void setPurcPlanDetId(String purcPlanDetId) {
      this.purcPlanDetId = purcPlanDetId;
    }
  }
}
