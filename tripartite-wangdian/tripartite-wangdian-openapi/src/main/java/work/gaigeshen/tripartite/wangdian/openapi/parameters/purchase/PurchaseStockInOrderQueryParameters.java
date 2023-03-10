package work.gaigeshen.tripartite.wangdian.openapi.parameters.purchase;

import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParameters;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParametersCustomizer;

/**
 * @author gaigeshen
 */
@Parameters(
        customizer = WangdianParametersCustomizer.class
)
public class PurchaseStockInOrderQueryParameters implements WangdianParameters {

    @Parameter
    public String src_order_no;

    @Parameter
    public String stockin_no;

    @Parameter
    public String stockin_outer_no;

    @Parameter
    public String warehouse_no;

    @Parameter
    public Integer status;

    @Parameter
    public String start_time;

    @Parameter
    public String end_time;

    @Parameter
    public Integer page_no;

    @Parameter
    public Integer page_size;

}
