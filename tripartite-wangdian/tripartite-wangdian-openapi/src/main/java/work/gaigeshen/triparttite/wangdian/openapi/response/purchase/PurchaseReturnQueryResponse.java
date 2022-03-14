package work.gaigeshen.triparttite.wangdian.openapi.response.purchase;

import work.gaigeshen.triparttite.wangdian.openapi.response.AbstractWangdianResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
public class PurchaseReturnQueryResponse extends AbstractWangdianResponse {

  public Integer total_count;

  public Collection<Purchase> purchase_list;


  public static class Purchase {

    public String return_no;

    public String provider_no;

    public String provider_name;

    public Integer status;

    public Integer type;

    public String warehouse_no;

    public String warehouse_name;

    public String purchaser_name;

    public String creator_name;

    public String logistics_type;

    public BigDecimal other_fee;

    public BigDecimal post_fee;

    public BigDecimal goods_fee;

    public BigDecimal goods_count;

    public Integer goods_type_count;

    public BigDecimal goods_out_count;

    public String remark;

    public String created;

    public String modified;

    public String check_time;

    public String check_operator_name;

    public String api_outer_no;

    public String outer_no;

    public String contact;

    public String receive_address;

    public String province;

    public String city;

    public String district;

    public Collection<Detail> details_list;
  }

  public static class Detail {

    public String goods_no;

    public String spec_no;

    public String goods_name;

    public String spec_name;

    public BigDecimal num;

    public BigDecimal num2;

    public BigDecimal price;

    public BigDecimal discount;

    public BigDecimal amount;

    public BigDecimal tax;

    public BigDecimal tax_amount;

    public BigDecimal out_num;

    public BigDecimal out_amount;

    public BigDecimal tax_price;

    public String modified;

    public String created;

    public String remark;
  }
}
