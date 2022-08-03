package work.gaigeshen.tripartite.his.procurement.openapi.config;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementConfig {

  private final String serverUrl;

  private final String account;

  private final String appCode;

  private final String authCode;

  private HisProcurementConfig(Builder builder) {
    this.serverUrl = builder.serverUrl;
    this.account = builder.account;
    this.appCode = builder.appCode;
    this.authCode = builder.authCode;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getServerUrl() {
    return serverUrl;
  }

  public String getAccount() {
    return account;
  }

  public String getAppCode() {
    return appCode;
  }

  public String getAuthCode() {
    return authCode;
  }

  @Override
  public int hashCode() {
    return account.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (Objects.isNull(obj) || getClass() != obj.getClass()) {
      return false;
    }
    return account.equals(((HisProcurementConfig) obj).account);
  }

  @Override
  public String toString() {
    return "HisProcurementConfig: " + account + "/" + appCode;
  }

  /**
   *
   * @author gaigeshen
   */
  public static class Builder {

    private String serverUrl;

    private String account;

    private String appCode;

    private String authCode;

    public void setServerUrl(String serverUrl) {
      this.serverUrl = serverUrl;
    }

    public void setAccount(String account) {
      this.account = account;
    }

    public void setAppCode(String appCode) {
      this.appCode = appCode;
    }

    public void setAuthCode(String authCode) {
      this.authCode = authCode;
    }

    public HisProcurementConfig build() {
      if (Objects.isNull(serverUrl)) {
        throw new HisProcurementConfigException("'serverUrl' not found");
      }
      if (Objects.isNull(account)) {
        throw new HisProcurementConfigException("'account' not found");
      }
      if (Objects.isNull(appCode)) {
        throw new HisProcurementConfigException("'appCode' not found");
      }
      if (Objects.isNull(authCode)) {
        throw new HisProcurementConfigException("'authCode' not found");
      }
      return new HisProcurementConfig(this);
    }
  }
}
