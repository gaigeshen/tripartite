package work.gaigeshen.tripartite.doudian.openapi.accesstoken;

import java.util.Date;

/**
 * 访问令牌
 *
 * @author gaigeshen
 */
public class DoudianAccessToken {

  private final String accessToken;

  private final String refreshToken;

  private final String scope;

  private final String shopId;

  private final String shopName;

  private final long expiresIn; // 有效期时长单位秒

  private final long expiresTimestamp; // 过期时间点单位秒

  private final Date updateTime; // 更新时间

  private DoudianAccessToken(Builder builder) {
    this.accessToken = builder.accessToken;
    this.refreshToken = builder.refreshToken;
    this.scope = builder.scope;
    this.shopId = builder.shopId;
    this.shopName = builder.shopName;
    this.expiresIn = builder.expiresIn;
    this.expiresTimestamp = builder.expiresTimestamp;
    this.updateTime = builder.updateTime;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getAccessToken() {
    return accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public String getScope() {
    return scope;
  }

  public String getShopId() {
    return shopId;
  }

  public String getShopName() {
    return shopName;
  }

  public long getExpiresIn() {
    return expiresIn;
  }

  public long getExpiresTimestamp() {
    return expiresTimestamp;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  @Override
  public String toString() {
    return "AccessToken: " + shopId + "/" + updateTime;
  }

  /**
   * 访问令牌构建器
   *
   * @author gaigeshen
   */
  public static class Builder {

    private String accessToken;

    private String refreshToken;

    private String scope;

    private String shopId;

    private String shopName;

    private long expiresIn;

    private long expiresTimestamp;

    private Date updateTime;

    public Builder setAccessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    public Builder setRefreshToken(String refreshToken) {
      this.refreshToken = refreshToken;
      return this;
    }

    public Builder setScope(String scope) {
      this.scope = scope;
      return this;
    }

    public Builder setShopId(String shopId) {
      this.shopId = shopId;
      return this;
    }

    public Builder setShopName(String shopName) {
      this.shopName = shopName;
      return this;
    }

    public Builder setExpiresIn(long expiresIn) {
      this.expiresIn = expiresIn;
      return this;
    }

    public Builder setExpiresTimestamp(long expiresTimestamp) {
      this.expiresTimestamp = expiresTimestamp;
      return this;
    }

    public Builder setUpdateTime(Date updateTime) {
      this.updateTime = updateTime;
      return this;
    }

    public DoudianAccessToken build() {
      return new DoudianAccessToken(this);
    }
  }
}
