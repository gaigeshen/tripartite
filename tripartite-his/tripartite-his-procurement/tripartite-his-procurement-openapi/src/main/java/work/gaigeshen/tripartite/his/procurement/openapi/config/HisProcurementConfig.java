package work.gaigeshen.tripartite.his.procurement.openapi.config;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementConfig {

  private final String serverHost;

  private final String accessTokenUri;

  private final String serviceUri;

  private final String account;

  private final String appCode;

  private final String authCode;

  private HisProcurementConfig(Builder builder) {
    this.serverHost = builder.serverHost;
    this.accessTokenUri = builder.accessTokenUri;
    this.serviceUri = builder.serviceUri;
    this.account = builder.account;
    this.appCode = builder.appCode;
    this.authCode = builder.authCode;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getServerHost() {
    return serverHost;
  }

  public String getAccessTokenUri() {
    return accessTokenUri;
  }

  public String getServiceUri() {
    return serviceUri;
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
    return "HisProcurementConfig: " + account;
  }

  /**
   *
   * @author gaigeshen
   */
  public static class Builder {

    private String serverHost;

    private String accessTokenUri;

    private String serviceUri;

    private String account;

    private String appCode;

    private String authCode;

    public void setServerHost(String serverHost) {
      this.serverHost = serverHost;
    }

    public void setAccessTokenUri(String accessTokenUri) {
      this.accessTokenUri = accessTokenUri;
    }

    public void setServiceUri(String serviceUri) {
      this.serviceUri = serviceUri;
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
      if (Objects.isNull(serverHost)) {
        throw new HisProcurementConfigException("'serverHost' not found");
      }
      if (Objects.isNull(accessTokenUri)) {
        throw new HisProcurementConfigException("'accessTokenUri' not found");
      }
      if (Objects.isNull(serviceUri)) {
        throw new HisProcurementConfigException("'serviceUri' not found");
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
