package work.gaigeshen.triparttite.wangdian.openapi.response.trade;

import work.gaigeshen.triparttite.wangdian.openapi.response.AbstractWangdianResponse;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class LogisticsSyncQueryResponse extends AbstractWangdianResponse {

  public Collection<Trade> trades;


  public static class Trade {

    public Integer rec_id;

    public String shop_no;

    public String tid;

    public String logistics_no;

    public Integer logistics_type;

    public String logistics_name;

    public Integer delivery_term;

    public String consign_time;

    public String is_part_sync;

    public String oids;

    public Integer stockout_id;

    public Integer is_need_sync;

    public Integer sync_status;

    public Integer is_last;

    public String description;

    public String sync_time;

    public String error_code;

    public String error_msg;

    public Integer try_times;

    public String modified;

    public String created;
  }
}
