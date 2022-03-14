package work.gaigeshen.triparttite.pay.wechat.parameters.basic;

import work.gaigeshen.triparttite.pay.wechat.parameters.WechatParameters;

/**
 *
 * @author gaigeshen
 */
public class WechatMiniAppOrderParameters implements WechatParameters {

  public String description;

  public String out_trade_no;

  public String time_expire;

  public String attach;

  public String goods_tag;

  public Amount amount;

  public Payer payer;

  public Detail detail;

  public SceneInfo scene_info;

  public SettleInfo settle_info;

  public static class Amount {

    public Integer total;

    public String currency;
  }

  public static class Payer {

    public String openid;
  }

  public static class Detail {

    public Integer cost_price;

    public String invoice_id;

    public GoodsDetail[] goods_detail;
  }

  public static class GoodsDetail {

    public String merchant_goods_id;

    public String goods_name;

    public Integer quantity;

    public Integer unit_price;
  }

  public static class SceneInfo {

    public String payer_client_ip;

    public String device_id;

    public StoreInfo store_info;
  }

  public static class StoreInfo {

    public String id;

    public String name;

    public String area_code;

    public String address;
  }

  public static class SettleInfo {

    public Boolean profit_sharing;
  }
}
