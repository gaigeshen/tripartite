package work.gaigeshen.triparttite.wangdian.openapi.response.stock;

import work.gaigeshen.triparttite.wangdian.openapi.response.AbstractWangdianResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class StockQueryResponse extends AbstractWangdianResponse {

  public Integer total_count;

  public Collection<Stock> stocks;


  public static class Stock {

    public String spec_no;

    public String spec_name;

    public String goods_no;

    public String goods_name;

    public String warehouse_no;

    public String warehouse_name;

    public Integer warehouse_type;

    public BigDecimal stock_num;

    public BigDecimal lock_num;

    public String created;

    public Integer status;

    public Integer unit;

    public String last_pd_time;

    public String last_inout_time;

    public String remark;

    public BigDecimal order_num;

    public BigDecimal sending_num;

    public BigDecimal purchase_num;

    public BigDecimal transfer_num;

    public BigDecimal to_transfer_num;

    public BigDecimal to_purchase_num;

    public BigDecimal purchase_arrive_num;

    public BigDecimal return_num;

    public BigDecimal refund_num;

    public String img_url;

    public String modified;
  }
}
