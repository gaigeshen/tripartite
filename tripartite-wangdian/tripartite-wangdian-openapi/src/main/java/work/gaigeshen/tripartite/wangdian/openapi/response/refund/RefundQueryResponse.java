package work.gaigeshen.tripartite.wangdian.openapi.response.refund;

import work.gaigeshen.tripartite.wangdian.openapi.response.AbstractWangdianResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
public class RefundQueryResponse extends AbstractWangdianResponse {

  public Integer total_count;

  public Collection<Refund> refunds;


  public static class Refund {

    public String refund_no;

    public String api_outer_no;

    public String src_no;

    public String tid;

    public String sales_tid;

    public String type;

    public String shop_no;

    public String shop_name;

    public Integer process_status;

    public Integer status;

    public Integer from_type;

    public Integer guarantee_mode;

    public Integer cs_status;

    public String pay_account;

    public String pay_no;

    public BigDecimal goods_amount;

    public BigDecimal return_goods_count;

    public BigDecimal refund_amount;

    public BigDecimal direct_refund_amount;

    public BigDecimal guarante_refund_amount;

    public BigDecimal actual_refund_amount;

    public BigDecimal exchange_amount;

    public BigDecimal post_amount;

    public BigDecimal other_amount;

    public BigDecimal paid;

    public String buyer_nick;

    public String receiver_name;

    public String receiver_address;

    public String return_name;

    public String return_mobile;

    public String return_address;

    public String return_logistics_name;

    public String return_logistics_no;

    public String swap_receiver;

    public String swap_mobile;

    public String swap_area;

    public String swap_warehouse_no;

    public String swap_address;

    public String swap_logistics_type;

    public String swap_logistics_name;

    public String swap_trade_no;

    public String warehouse_no;

    public String warehouse_type;

    public String refund_time;

    public String refund_reason;

    public String customer_no;

    public String customer_name;

    public String creator_name;

    public Integer advance_status;

    public Integer is_goods_received;

    public Integer consign_mode;

    public String remark;

    public String finish_time;

    public String modified;

    public String created;

    public Collection<RefundOrder> refund_order_list;

    public Collection<RefundOutGoods> refund_out_goods_list;
  }

  public static class RefundOrder {

    public String oid;

    public String tid;

    public String sales_tid;

    public String src_no;

    public Integer process_status;

    public BigDecimal order_num;

    public BigDecimal refund_num;

    public BigDecimal stockin_num;

    public BigDecimal price;

    public BigDecimal cost_price;

    public BigDecimal original_price;

    public BigDecimal discount;

    public BigDecimal paid;

    public BigDecimal refund_order_amount;

    public BigDecimal total_amount;

    public BigDecimal stockin_amount;

    public String goods_no;

    public String spec_no;

    public String goods_name;

    public String spec_name;

    public String remark;

    public BigDecimal modified;

    public BigDecimal created;

  }

  public static class RefundOutGoods {

    public Integer target_type;

    public String goods_name;

    public String spec_name;

    public String merchant_no;

    public BigDecimal num;

    public String oid;

    public String remark;

    public String modified;

    public String created;
  }
}
