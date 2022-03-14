package work.gaigeshen.triparttite.wangdian.openapi.response.purchase;

import work.gaigeshen.triparttite.wangdian.openapi.response.AbstractWangdianResponse;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class PurchaseProviderQueryResponse extends AbstractWangdianResponse {

  public Integer total_count;

  public Collection<Provider> provider_list;


  public static class Provider {

    public String provider_no;

    public String provider_name;

    public String contact;

    public String mobile;

    public String address;

    public String remark;

    public Integer is_disabled;

    public Integer deleted;

    public String modified;

    public String created;
  }
}
