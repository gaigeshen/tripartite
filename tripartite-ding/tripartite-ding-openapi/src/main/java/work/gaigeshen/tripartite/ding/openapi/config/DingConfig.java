package work.gaigeshen.tripartite.ding.openapi.config;

import java.util.Objects;

/**
 * 钉钉配置
 *
 * @author gaigeshen
 */
public class DingConfig {

  private final String appKey;

  private final String appSecret;

  private final String secretKey;

  private final String token;

  private DingConfig(Builder builder) {
    this.appKey = builder.appKey;
    this.appSecret = builder.appSecret;
    this.secretKey = builder.secretKey;
    this.token = builder.token;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getAppKey() {
    return appKey;
  }

  public String getAppSecret() {
    return appSecret;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public String getToken() {
    return token;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DingConfig that = (DingConfig) o;
    return Objects.equals(appKey, that.appKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appKey);
  }

  @Override
  public String toString() {
    return "DingConfig: " + appKey;
  }

  /**
   *
   * @author gaigeshen
   */
  public static class Builder {

    private String appKey;

    private String appSecret;

    private String secretKey;

    private String token;

    public void setAppKey(String appKey) {
      this.appKey = appKey;
    }

    public void setAppSecret(String appSecret) {
      this.appSecret = appSecret;
    }

    public void setSecretKey(String secretKey) {
      this.secretKey = secretKey;
    }

    public void setToken(String token) {
      this.token = token;
    }

    public DingConfig build() {
      if (Objects.isNull(appKey)) {
        throw new DingConfigException("missing [appKey]");
      }
      if (Objects.isNull(appSecret)) {
        throw new DingConfigException("missing [appSecret]");
      }
      if (Objects.isNull(secretKey)) {
        setSecretKey("");
      }
      if (Objects.isNull(token)) {
        setToken("");
      }
      return new DingConfig(this);
    }
  }

}
