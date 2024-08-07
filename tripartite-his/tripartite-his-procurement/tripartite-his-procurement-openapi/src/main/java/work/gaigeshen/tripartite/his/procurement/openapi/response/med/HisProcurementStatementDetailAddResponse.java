package work.gaigeshen.tripartite.his.procurement.openapi.response.med;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.math.BigDecimal;

/**
 * @author gaigeshen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HisProcurementStatementDetailAddResponse extends AbstractHisProcurementResponse {

    private BigDecimal shppAmt;

    private BigDecimal payOrderAmount;

    private BigDecimal retnAmt;

    private String payOrdId;
}
