package work.gaigeshen.triparttite.wangdian.openapi.response.goods;

import work.gaigeshen.triparttite.wangdian.openapi.response.AbstractWangdianResponse;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class GoodsQueryResponse extends AbstractWangdianResponse {

  public Integer total_count;

  public Collection<Goods> goods_list;


  public static class Goods {

    public String goods_no;

    public String goods_name;

    public Integer goods_type;

    public Integer spec_count;

    public String class_name;

    public String brand_name;

    public String unit_name;

    public String aux_unit_name;

    public String remark;

    public String prop1;

    public String prop2;

    public String prop3;

    public String prop4;

    public String prop5;

    public String prop6;

    public String modified;

    public String created;

    public Integer deleted;

    public Collection<Spec> spec_list;
  }


  public static class Spec {

    public String spec_no;

    public String spec_name;

    public String img_url;

    public String remark;

    public String prop1;

    public String prop2;

    public String prop3;

    public String prop4;

    public String prop5;

    public String prop6;

    public String modified;

    public String created;

    public Integer deleted;
  }
}
