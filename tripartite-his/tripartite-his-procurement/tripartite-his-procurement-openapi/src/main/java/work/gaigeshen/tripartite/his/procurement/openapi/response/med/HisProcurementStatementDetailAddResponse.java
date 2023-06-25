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

    public BigDecimal getShppAmt() {
        return shppAmt;
    }

    public void setShppAmt(BigDecimal shppAmt) {
        this.shppAmt = shppAmt;
    }

    public BigDecimal getPayOrderAmount() {
        return payOrderAmount;
    }

    public void setPayOrderAmount(BigDecimal payOrderAmount) {
        this.payOrderAmount = payOrderAmount;
    }

    public BigDecimal getRetnAmt() {
        return retnAmt;
    }

    public void setRetnAmt(BigDecimal retnAmt) {
        this.retnAmt = retnAmt;
    }

    public String getPayOrdId() {
        return payOrdId;
    }

    public void setPayOrdId(String payOrdId) {
        this.payOrdId = payOrdId;
    }
}
