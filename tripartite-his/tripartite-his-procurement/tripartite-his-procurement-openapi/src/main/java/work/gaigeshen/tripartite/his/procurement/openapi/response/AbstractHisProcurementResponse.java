package work.gaigeshen.tripartite.his.procurement.openapi.response;

/**
 *
 * @author gaigeshen
 */
public abstract class AbstractHisProcurementResponse implements HisProcurementResponse {

  private Integer returnCode;

  private String returnMsg;

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
}
