package work.gaigeshen.tripartite.wangdian.openapi.parameters.purchase;

import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;
import work.gaigeshen.tripartite.core.parameter.typed.converter.DefaultJsonParameterConverter;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParameters;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParametersCustomizer;

import java.util.Collection;

/**
 * @author gaigeshen
 */
@Parameters(
        customizer = WangdianParametersCustomizer.class
)
public class PurchaseReturnEndParameters implements WangdianParameters {

    @Parameter
    public Integer type;

    @Parameter
    public Integer is_reject_review;

    @Parameter(converter = DefaultJsonParameterConverter.class)
    public Collection<PurchaseReturn> purchase_return_no_list;


    public static class PurchaseReturn {

        public String purchase_return_no;
    }
}
