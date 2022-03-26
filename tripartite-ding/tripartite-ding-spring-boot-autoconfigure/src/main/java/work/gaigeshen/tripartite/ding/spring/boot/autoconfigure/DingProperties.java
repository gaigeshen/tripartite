package work.gaigeshen.tripartite.ding.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 钉钉客户端配置
 *
 * @author gaigeshen
 */
@ConfigurationProperties("ding")
public class DingProperties {

  /**
   * 遗留的接口服务器的主机地址
   */
  private String legacyServerHost;

  /**
   * 新的接口服务器的主机地址
   */
  private String modernServerHost;

  /**
   * 多个客户端的配置
   */
  private Collection<Client> clients = new ArrayList<>();

  /**
   * 多个自定义机器人地址配置
   */
  private Collection<RobotWebhook> robotWebhooks = new ArrayList<>();

  public String getLegacyServerHost() {
    return legacyServerHost;
  }

  public void setLegacyServerHost(String legacyServerHost) {
    this.legacyServerHost = legacyServerHost;
  }

  public String getModernServerHost() {
    return modernServerHost;
  }

  public void setModernServerHost(String modernServerHost) {
    this.modernServerHost = modernServerHost;
  }

  public Collection<Client> getClients() {
    return clients;
  }

  public void setClients(Collection<Client> clients) {
    this.clients = clients;
  }

  public Collection<RobotWebhook> getRobotWebhooks() {
    return robotWebhooks;
  }

  public void setRobotWebhooks(Collection<RobotWebhook> robotWebhooks) {
    this.robotWebhooks = robotWebhooks;
  }

  /**
   * 钉钉客户端应用配置
   *
   * @author gaigeshen
   */
  public static class Client {

    /**
     * 应用编号
     */
    private String appKey;

    /**
     * 应用密钥
     */
    private String appSecret;

    /**
     * 异步通知或者回调通知的密钥
     */
    private String secretKey;

    /**
     * 异步通知或者回调通知的签名令牌
     */
    private String token;

    public String getAppKey() {
      return appKey;
    }

    public void setAppKey(String appKey) {
      this.appKey = appKey;
    }

    public String getAppSecret() {
      return appSecret;
    }

    public void setAppSecret(String appSecret) {
      this.appSecret = appSecret;
    }

    public String getSecretKey() {
      return secretKey;
    }

    public void setSecretKey(String secretKey) {
      this.secretKey = secretKey;
    }

    public String getToken() {
      return token;
    }

    public void setToken(String token) {
      this.token = token;
    }
  }

  /**
   * 自定义机器人地址配置
   *
   * @author gaigeshen
   */
  public static class RobotWebhook {

    /**
     * 可读的名称
     */
    private String name;

    /**
     * 自定义机器人的地址
     */
    private String webhook;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getWebhook() {
      return webhook;
    }

    public void setWebhook(String webhook) {
      this.webhook = webhook;
    }
  }
}
