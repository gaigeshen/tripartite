package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

import java.util.Date;

/**
 * @author gaigeshen
 */
public class HisProcurementAccessToken {

  private final String accessToken;

  private final String account;

  private final long expiresIn; // 有效期时长单位秒

  private final long expiresTimestamp; // 过期时间点单位秒

  private final Date updateTime; // 更新时间

  private HisProcurementAccessToken(Builder builder) {
    this.accessToken = builder.accessToken;
    this.account = builder.account;
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

  public String getAccount() {
    return account;
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
    return "AccessToken: " + account + "/" + updateTime;
  }

  /**
   *
   * @author gaigeshen
   */
  public static class Builder {

    private String accessToken;

    private String account;

    private long expiresIn;

    private long expiresTimestamp;

    private Date updateTime;

    public Builder setAccessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    public Builder setAccount(String account) {
      this.account = account;
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

    public HisProcurementAccessToken build() {
      return new HisProcurementAccessToken(this);
    }
  }
}
