package work.gaigeshen.triparttite.wangdian.openapi.response.purchase;

import work.gaigeshen.triparttite.wangdian.openapi.response.AbstractWangdianResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class PurchaseReturnOrderQueryResponse extends AbstractWangdianResponse {

  public Integer total_count;

  public Collection<StockOut> stockout_list;

  public static class StockOut {

    public String order_no;

    public String src_order_no;

    public String return_no;

    public String stockout_no;

    public String outer_no;

    public String pr_api_outer_no;

    public String last_load_purchase_no;

    public String warehouse_no;

    public Integer warehouse_type;

    public String provider_no;

    public String provider_name;

    public Integer status;

    public Integer block_reason;

    public String consign_time;

    public Integer consign_status;

    public Integer order_type;

    public Integer src_order_type;

    public String order_type_name;

    public String subtype;

    public BigDecimal goods_type_count;

    public BigDecimal goods_count;

    public BigDecimal goods_total_amount;

    public BigDecimal goods_total_cost;

    public BigDecimal post_fee;

    public BigDecimal package_fee;

    public String logistics_no;

    public String receiver_name;

    public String receiver_area;

    public String receiver_address;

    public String receiver_mobile;

    public String stockout_reason;

    public String operator_name;

    public String picker_name;

    public String sorter_name;

    public String examiner_name;

    public String consigner;

    public String packager_name;

    public String checkouter_name;

    public String watcher_name;

    public String remark;

    public String modified;

    public String stock_check_time;

    public Collection<Detail> details_list;
  }

  public static class Detail {

    public String goods_no;

    public String goods_name;

    public String spec_no;

    public String spec_name;

    public Integer goods_type;

    public String goods_unit;

    public BigDecimal num;

    public BigDecimal price;

    public BigDecimal goods_count;

    public BigDecimal sell_price;

    public BigDecimal cost_price;

    public BigDecimal total_amount;

    public BigDecimal weight;

    public String brand_no;

    public String brand_name;

    public String batch_no;

    public String batch_remark;

    public Integer src_order_type;

    public Integer is_examined;

    public Integer is_package;

    public Integer is_zero_cost;

    public Integer scan_type;

    public String remark;

    public String modified;

    public String created;

    public Collection<Position> position_list;
  }

  public static class Position {

    public String position_no;

    public BigDecimal position_goods_count;
  }

}
