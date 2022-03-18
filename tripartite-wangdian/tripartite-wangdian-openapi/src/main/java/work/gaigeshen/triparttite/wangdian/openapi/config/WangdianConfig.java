package work.gaigeshen.triparttite.wangdian.openapi.config;

import java.util.Objects;

/**
 * 旺店通客户端配置
 *
 * @author gaigeshen
 */
public class WangdianConfig {

  /**
   * 旺店通服务器主机地址
   */
  private final String serverHost;

  /**
   * 卖家编号
   */
  private final String sellerId;

  /**
   * 店铺编号
   */
  private final String shopNo;

  /**
   * 接口账号
   */
  private final String appKey;

  /**
   * 接口密钥
   */
  private final String appSecret;

  private WangdianConfig(Builder builder) {
    this.serverHost = builder.serverHost;
    this.sellerId = builder.sellerId;
    this.shopNo = builder.shopNo;
    this.appKey = builder.appKey;
    this.appSecret = builder.appSecret;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getServerHost() {
    return serverHost;
  }

  public String getSellerId() {
    return sellerId;
  }

  public String getShopNo() {
    return shopNo;
  }

  public String getAppKey() {
    return appKey;
  }

  public String getAppSecret() {
    return appSecret;
  }

  @Override
  public int hashCode() {
    return sellerId.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (Objects.isNull(obj) || getClass() != obj.getClass()) {
      return false;
    }
    return sellerId.equals(((WangdianConfig) obj).sellerId);
  }

  @Override
  public String toString() {
    return "WangdianConfig: " + sellerId;
  }

  /**
   *
   * @author gaigeshen
   */
  public static class Builder {

    private String serverHost;

    private String sellerId;

    private String shopNo;

    private String appKey;

    private String appSecret;

    public void setServerHost(String serverHost) {
      this.serverHost = serverHost;
    }

    public void setSellerId(String sellerId) {
      this.sellerId = sellerId;
    }

    public void setShopNo(String shopNo) {
      this.shopNo = shopNo;
    }

    public void setAppKey(String appKey) {
      this.appKey = appKey;
    }

    public void setAppSecret(String appSecret) {
      this.appSecret = appSecret;
    }

    public WangdianConfig build() {
      if (Objects.isNull(serverHost)) {
        throw new WangdianConfigException("serverHost is required");
      }
      if (Objects.isNull(sellerId)) {
        throw new WangdianConfigException("sellerId is required");
      }
      if (Objects.isNull(shopNo)) {
        throw new WangdianConfigException("shopNo is required");
      }
      if (Objects.isNull(appKey)) {
        throw new WangdianConfigException("appKey is required");
      }
      if (Objects.isNull(appSecret)) {
        throw new WangdianConfigException("appSecret is required");
      }
      return new WangdianConfig(this);
    }
  }
}
