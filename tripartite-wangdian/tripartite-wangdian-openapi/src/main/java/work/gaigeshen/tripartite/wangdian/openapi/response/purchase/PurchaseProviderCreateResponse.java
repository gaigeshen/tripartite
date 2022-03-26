package work.gaigeshen.tripartite.wangdian.openapi.response.purchase;

import work.gaigeshen.tripartite.wangdian.openapi.response.AbstractWangdianResponse;

/**
 *
 * @author gaigeshen
 */
public class PurchaseProviderCreateResponse extends AbstractWangdianResponse {

  public String provider_no;

  public String provider_name;

  public String contact;

  public String mobile;

  public String address;

  public Integer is_disabled;

  public Integer deleted;

  public String modified;

  public String created;
}
