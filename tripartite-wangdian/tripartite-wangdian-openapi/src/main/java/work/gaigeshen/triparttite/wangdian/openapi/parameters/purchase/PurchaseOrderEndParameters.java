package work.gaigeshen.triparttite.wangdian.openapi.parameters.purchase;

import work.gaigeshen.triparttite.core.parameter.converter.Parameters;
import work.gaigeshen.triparttite.core.parameter.typed.Parameter;
import work.gaigeshen.triparttite.core.parameter.typed.converter.GsonJsonParameterConverter;
import work.gaigeshen.triparttite.wangdian.openapi.parameters.WangdianParameters;
import work.gaigeshen.triparttite.wangdian.openapi.parameters.WangdianParametersCustomizer;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        customizer = WangdianParametersCustomizer.class
)
public class PurchaseOrderEndParameters implements WangdianParameters {

  @Parameter
  public Integer type;

  @Parameter
  public Integer is_reject_review;

  @Parameter(converter = GsonJsonParameterConverter.class)
  public Collection<PurchaseNo> purchase_no_list;


  public static class PurchaseNo {

    public String purchase_no;

    public Collection<String> spec_no;
  }
}
