package work.gaigeshen.tripartite.his.procurement.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaigeshen
 */
@ConfigurationProperties("his.procurement")
public class HisProcurementProperties {

  private List<Client> clients = new ArrayList<>();

  public List<Client> getClients() {
    return clients;
  }

  public void setClients(List<Client> clients) {
    this.clients = clients;
  }

  /**
   *
   * @author gaigeshen
   */
  public static class Client {

    private Integer connectTimeout;

    private Integer readTimeout;

    private String serverHost;

    private String accessTokenUri;

    private String serviceUri;

    private String account;

    private String type;

    private String appCode;

    private String authCode;

    private String secret;

    public Integer getConnectTimeout() {
      return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
      this.connectTimeout = connectTimeout;
    }

    public Integer getReadTimeout() {
      return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
      this.readTimeout = readTimeout;
    }

    public String getServerHost() {
      return serverHost;
    }

    public void setServerHost(String serverHost) {
      this.serverHost = serverHost;
    }

    public String getAccessTokenUri() {
      return accessTokenUri;
    }

    public void setAccessTokenUri(String accessTokenUri) {
      this.accessTokenUri = accessTokenUri;
    }

    public String getServiceUri() {
      return serviceUri;
    }

    public void setServiceUri(String serviceUri) {
      this.serviceUri = serviceUri;
    }

    public String getAccount() {
      return account;
    }

    public void setAccount(String account) {
      this.account = account;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getAppCode() {
      return appCode;
    }

    public void setAppCode(String appCode) {
      this.appCode = appCode;
    }

    public String getAuthCode() {
      return authCode;
    }

    public void setAuthCode(String authCode) {
      this.authCode = authCode;
    }

    public String getSecret() {
      return secret;
    }

    public void setSecret(String secret) {
      this.secret = secret;
    }
  }
}
