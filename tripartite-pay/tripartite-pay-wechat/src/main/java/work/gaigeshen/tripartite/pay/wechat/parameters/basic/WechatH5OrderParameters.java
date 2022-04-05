package work.gaigeshen.tripartite.pay.wechat.parameters.basic;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.pay.wechat.parameters.WechatParameters;
import work.gaigeshen.tripartite.pay.wechat.parameters.WechatParametersCustomizer;

/**
 *
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class,
        customizer = WechatParametersCustomizer.class
)
public class WechatH5OrderParameters implements WechatParameters {

  public String description;

  public String out_trade_no;

  public String time_expire;

  public String attach;

  public String goods_tag;

  public Amount amount;

  public Detail detail;

  public SceneInfo scene_info;

  public SettleInfo settle_info;

  public static class Amount {

    public Integer total;

    public String currency;
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

    public H5Info h5_info;
  }

  public static class StoreInfo {

    public String id;

    public String name;

    public String area_code;

    public String address;
  }

  public static class H5Info {

    public String type;

    public String app_name;

    public String app_url;

    public String bundle_id;

    public String package_name;
  }

  public static class SettleInfo {

    public Boolean profit_sharing;
  }
}
