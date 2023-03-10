package work.gaigeshen.tripartite.pay.alipay.response;

/**
 * @author gaigeshen
 */
public abstract class AbstractAlipayResponse implements AlipayResponse {

    public String code;

    public String msg;

    public String sub_code;

    public String sub_msg;
}
