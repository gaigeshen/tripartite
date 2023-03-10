package work.gaigeshen.tripartite.wangdian.openapi.response.refund;

import work.gaigeshen.tripartite.wangdian.openapi.response.AbstractWangdianResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
public class StockInRefundQueryResponse extends AbstractWangdianResponse {

    public Integer total_count;

    public Collection<StockIn> stockin_list;


    public static class StockIn {

        public Integer process_status;

        public Integer status;

        public Integer order_type;

        public Integer src_order_type;

        public String order_type_name;

        public String order_no;

        public String src_order_no;

        public String tid;

        public String refund_no;

        public String stockin_no;

        public String trade_no;

        public String trade_type;

        public String shop_no;

        public String shop_name;

        public String shop_remark;

        public String warehouse_no;

        public String warehouse_name;

        public String check_operator_name;

        public String refund_operator_name;

        public String refund_remark;

        public String stockin_operator_name;

        public String stockin_reason;

        public String stockin_time;

        public String created_time;

        public String check_time;

        public BigDecimal goods_type_count;

        public BigDecimal adjust_num;

        public BigDecimal goods_count;

        public BigDecimal goods_amount;

        public BigDecimal actual_refund_amount;

        public BigDecimal total_price;

        public BigDecimal discount;

        public BigDecimal tax_amount;

        public BigDecimal post_fee;

        public BigDecimal other_fee;

        public BigDecimal adjust_price;

        public BigDecimal right_price;

        public Integer logistics_type;

        public String logistics_name;

        public String logistics_code;

        public String customer_no;

        public String customer_name;

        public String nick_name;

        public String remark;

        public String modified;

        public Collection<Detail> details_list;
    }

    public static class Detail {

        public String goods_no;

        public String goods_name;

        public String spec_no;

        public String spec_name;

        public String goods_unit;

        public BigDecimal num;

        public BigDecimal num2;

        public BigDecimal goods_count;

        public BigDecimal discount;

        public BigDecimal cost_price;

        public BigDecimal src_price;

        public BigDecimal tax_price;

        public BigDecimal tax_amount;

        public BigDecimal tax;

        public BigDecimal total_cost;

        public BigDecimal adjust_num;

        public BigDecimal adjust_price;

        public BigDecimal right_num;

        public BigDecimal right_price;

        public BigDecimal right_cost;

        public String brand_no;

        public String brand_name;

        public String tid;

        public String oid;

        public Integer src_order_type;

        public String remark;

        public String modified;

        public String created;
    }
}
