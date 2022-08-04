package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata;

import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.math.BigDecimal;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementTakeDeliveryInputData implements HisProcurementInputData {

  public String medinsCode;

  public String shpCode;

  public BigDecimal shppCnt;
}
