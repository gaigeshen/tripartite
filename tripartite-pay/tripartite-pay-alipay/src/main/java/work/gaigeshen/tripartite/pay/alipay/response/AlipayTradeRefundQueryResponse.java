package work.gaigeshen.tripartite.pay.alipay.response;

import java.math.BigDecimal;

/**
 * @author gaigeshen
 */
public class AlipayTradeRefundQueryResponse extends AbstractAlipayResponse {

    public String out_trade_no;

    public String trade_no;

    public String out_request_no;

    public String refund_reason;

    public BigDecimal total_amount;

    public BigDecimal refund_amount;

    public String refund_status;
}
