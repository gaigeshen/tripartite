package work.gaigeshen.tripartite.ding.openapi.accesstoken;

import java.util.Date;
import java.util.Objects;

/**
 * 钉钉访问令牌
 *
 * @author gaigeshen
 */
public class DingAccessToken {

  private final String token;

  private final long expiresInSeconds;

  private final long createTime; // seconds

  public DingAccessToken(String token, long expiresInSeconds) {
    if (Objects.isNull(token)) {
      throw new IllegalArgumentException("token cannot be null");
    }
    if (expiresInSeconds <= 0) {
      throw new IllegalArgumentException("expiresInSeconds is invalid");
    }
    this.token = token;
    this.expiresInSeconds = expiresInSeconds;
    this.createTime = System.currentTimeMillis() / 1000;
  }

  public String getToken() {
    return token;
  }

  public long getExpiresInSeconds() {
    return expiresInSeconds;
  }

  public Date getCreateTime() {
    return new Date(createTime * 1000);
  }

  public Date getExpiresTime() {
    return new Date((createTime + expiresInSeconds) * 1000);
  }

  public boolean isExpired() {
    return getExpiresTime().before(new Date());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DingAccessToken that = (DingAccessToken) o;
    return Objects.equals(token, that.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token);
  }

  @Override
  public String toString() {
    return "AccessToken: " + token + "/" + getExpiresTime();
  }
}
