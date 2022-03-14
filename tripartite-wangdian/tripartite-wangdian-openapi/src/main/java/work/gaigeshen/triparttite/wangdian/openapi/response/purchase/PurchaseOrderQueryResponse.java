package work.gaigeshen.triparttite.wangdian.openapi.response.purchase;

import work.gaigeshen.triparttite.wangdian.openapi.response.AbstractWangdianResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class PurchaseOrderQueryResponse extends AbstractWangdianResponse {

  public Integer total_count;

  public Collection<Purchase> purchase_list;


  public static class Purchase {

    public String purchase_no;

    public String purchase_outer_no;

    public String outer_no;

    public String warehouse_name;

    public String warehouse_no;

    public Integer status;

    public String provider_no;

    public String provider_name;

    public String contact;

    public String purchaser_name;

    public String receive_address;

    public Integer logistics_type;

    public String check_time;

    public String remark;

    public String expect_arrive_time;

    public String modified;

    public String created;

    public String creator_name;

    public String api_outer_no;

    public Integer order_type;

    public Integer goods_type_count;

    public BigDecimal goods_arrive_count;

    public Integer revert_reason;

    public Collection<Detail> details_list;
  }


  public static class Detail {

    public String provider_goods_no;

    public String goods_no;

    public String goods_name;

    public String spec_no;

    public String spec_name;

    public BigDecimal num;

    public BigDecimal stockin_num;

    public BigDecimal arrive_num;

    public BigDecimal stopwait_num;

    public BigDecimal arrive_more_num;

    public BigDecimal lack_num;

    public String remark;

    public String modified;

    public String created;
  }
}
