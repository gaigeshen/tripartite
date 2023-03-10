package work.gaigeshen.tripartite.wangdian.openapi.parameters.warehouse;

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
public class WarehouseQueryParameters implements WangdianParameters {

    @Parameter
    public String warehouse_no;

    @Parameter
    public Integer page_no;

    @Parameter
    public Integer page_size;

}
