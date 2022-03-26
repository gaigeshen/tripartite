package work.gaigeshen.tripartite.wangdian.openapi.response.purchase;

import work.gaigeshen.tripartite.wangdian.openapi.response.AbstractWangdianResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class PurchaseStockInOrderQueryResponse extends AbstractWangdianResponse {

  public Integer total_count;

  public Collection<StockIn> stockin_list;


  public static class StockIn {

    public String order_no;

    public Integer status;

    public String warehouse_no;

    public String warehouse_name;

    public String stockin_time;

    public String check_time;

    public String created_time;

    public String src_order_no;

    public String remark;

    public String stockin_reason;

    public String order_type_name;

    public Integer logistics_type;

    public String logistics_name;

    public Integer logistics_no;

    public String purchase_no;

    public String provider_no;

    public String provider_name;

    public String outer_no;

    public BigDecimal goods_count;

    public BigDecimal adjust_num;

    public BigDecimal right_nums;

    public String stockin_no;

    public Integer src_order_type;

    public Integer goods_type_count;

    public String modified;

    public String check_operator_name;

    public String operator_name;

    public Collection<Detail> details_list;
  }


  public static class Detail {

    public String spec_no;

    public BigDecimal goods_count;

    public String production_date;

    public Integer validity_days;

    public String expire_date;

    public String remark;

    public BigDecimal adjust_num;

    public BigDecimal right_num;

    public String goods_name;

    public String goods_no;

    public String spec_name;

    public String position_no;

    public Integer src_order_type;

    public BigDecimal num;

    public BigDecimal expect_num;

    public String modified;

    public String created;
  }
}
