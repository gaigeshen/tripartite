package work.gaigeshen.tripartite.wangdian.openapi.response.trade;

import work.gaigeshen.tripartite.wangdian.openapi.response.AbstractWangdianResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class TradeQueryResponse extends AbstractWangdianResponse {

  public Integer total_count;

  public Collection<Trade> trades;


  public static class Trade {

    public String trade_no;

    public String shop_no;

    public String shop_name;

    public String shop_remark;

    public Integer warehouse_type;

    public String warehouse_no;

    public String src_tids;

    public Integer trade_status;

    public Integer consign_status;

    public Integer trade_type;

    public Integer delivery_term;

    public Integer freeze_reason;

    public Integer refund_status;

    public String trade_time;

    public String pay_time;

    public String customer_name;

    public String customer_no;

    public String pay_account;

    public String buyer_nick;

    public String receiver_name;

    public Integer receiver_country;

    public Integer receiver_province;

    public Integer receiver_city;

    public Integer receiver_district;

    public String receiver_address;

    public String receiver_mobile;

    public Integer bad_reason;

    public String logistics_name;

    public Integer logistics_type;

    public String logistics_no;

    public Integer revert_reason;

    public Integer cancel_reason;

    public String buyer_message;

    public String cs_remark;

    public Integer goods_type_count;

    public BigDecimal goods_count;

    public Integer raw_goods_type_count;

    public BigDecimal raw_goods_count;

    public Integer invoice_type;

    public String invoice_title;

    public String invoice_content;

    public String stockout_no;

    public Integer trade_from;

    public String modified;

    public String created;

    public Collection<Goods> goods_list;
  }


  public static class Goods {

    public String src_oid;

    public String src_tid;

    public Integer refund_status;

    public Integer delivery_term;

    public BigDecimal num;

    public BigDecimal actual_num;

    public BigDecimal refund_num;

    public String goods_no;

    public String goods_name;

    public String spec_no;

    public String spec_name;

    public Integer goods_type;

    public Integer invoice_type;

    public String invoice_content;

    public String remark;

    public String modified;

    public String created;

    public Integer pay_status;

    public String pay_time;
  }
}
