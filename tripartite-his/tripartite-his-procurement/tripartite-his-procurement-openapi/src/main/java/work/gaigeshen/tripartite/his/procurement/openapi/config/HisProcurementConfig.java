package work.gaigeshen.tripartite.his.procurement.openapi.config;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementConfig {

  public static final String CONFIG_TYPE_MAT = "mat";

  public static final String CONFIG_TYPE_MED = "med";

  private final String serverHost;

  private final String accessTokenUri;

  private final String serviceUri;

  private final String account;

  private final String type;

  private final String appCode;

  private final String authCode;

  private HisProcurementConfig(Builder builder) {
    this.serverHost = builder.serverHost;
    this.accessTokenUri = builder.accessTokenUri;
    this.serviceUri = builder.serviceUri;
    this.account = builder.account;
    this.type = builder.type;
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

  public String getType() {
    return type;
  }

  public String getAppCode() {
    return appCode;
  }

  public String getAuthCode() {
    return authCode;
  }

  @Override
  public int hashCode() {
    return Objects.hash(account, type);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (Objects.isNull(obj) || getClass() != obj.getClass()) {
      return false;
    }
    HisProcurementConfig other = (HisProcurementConfig) obj;
    return account.equals(other.account) && type.equals(other.type);
  }

  @Override
  public String toString() {
    return "HisProcurementConfig: " + account + "/" + type;
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

    private String type;

    private String appCode;

    private String authCode;

    public Builder setServerHost(String serverHost) {
      this.serverHost = serverHost;
      return this;
    }

    public Builder setAccessTokenUri(String accessTokenUri) {
      this.accessTokenUri = accessTokenUri;
      return this;
    }

    public Builder setServiceUri(String serviceUri) {
      this.serviceUri = serviceUri;
      return this;
    }

    public Builder setAccount(String account) {
      this.account = account;
      return this;
    }

    public Builder setType(String type) {
      this.type = type;
      return this;
    }

    public Builder setAppCode(String appCode) {
      this.appCode = appCode;
      return this;
    }

    public Builder setAuthCode(String authCode) {
      this.authCode = authCode;
      return this;
    }

    public HisProcurementConfig build() {
      return new HisProcurementConfig(this);
    }
  }
}
