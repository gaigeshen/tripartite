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
public class PurchaseOrderQueryParameters implements WangdianParameters {

    @Parameter
    public String purchase_no;

    @Parameter
    public String warehouse_no;

    @Parameter
    public String outer_no;

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
