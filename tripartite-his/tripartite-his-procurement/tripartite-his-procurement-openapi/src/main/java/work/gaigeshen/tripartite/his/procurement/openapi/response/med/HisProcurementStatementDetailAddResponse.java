package work.gaigeshen.tripartite.his.procurement.openapi.response.med;

import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.math.BigDecimal;

/**
 * @author gaigeshen
 */
public class HisProcurementStatementDetailAddResponse extends AbstractHisProcurementResponse {

  private BigDecimal shppAmt;

  private BigDecimal payOrderAmount;

  private BigDecimal retnAmt;

  private String payOrdId;
}
