package work.gaigeshen.tripartite.wangdian.openapi.parameters.goods;

import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;
import work.gaigeshen.tripartite.core.parameter.typed.converter.DefaultJsonParameterConverter;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParametersCustomizer;
import work.gaigeshen.tripartite.wangdian.openapi.parameters.WangdianParameters;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        customizer = WangdianParametersCustomizer.class
)
public class GoodsPushParameters implements WangdianParameters {

  @Parameter(converter = DefaultJsonParameterConverter.class)
  public Collection<Goods> goods_list;


  public static class Goods {

    public String goods_no;

    public Integer goods_type;

    public String goods_name;

    public String class_name;

    public String brand_name;

    public String unit_name;

    public String aux_unit_name;

    public String remark;

    public String props1;

    public String props2;

    public String props3;

    public String props4;

    public String props5;

    public String props6;

    public Collection<Spec> spec_list;
  }

  public static class Spec {

    public String spec_no;

    public String spec_name;

    public String img_url;

    public String prop1;

    public String prop2;

    public String prop3;

    public String prop4;

    public String prop5;

    public String prop6;
  }
}
