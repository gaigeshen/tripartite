package work.gaigeshen.triparttite.wangdian.openapi.response.stock;

import work.gaigeshen.triparttite.wangdian.openapi.response.AbstractWangdianResponse;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class StockChangeQueryResponse extends AbstractWangdianResponse {

  public Integer current_count;

  public Collection<StockChange> stock_change_list;


  public static class StockChange {

    public Integer rec_id;

    public Integer sync_stock;

    public String goods_no;

    public String spec_no;

    public Integer erp_spec_type;

    public Integer stock_change_count;
  }

}
