package work.gaigeshen.tripartite.pay.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 支付宝客户端配置
 *
 * @author gaigeshen
 */
@ConfigurationProperties("alipay")
public class AlipayProperties {

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

    /**
     * 支付宝服务器地址（必填）
     */
    private String serverUrl;

    /**
     * 异步通知主机（必填）
     */
    private String notifyServerHost;

    /**
     * 应用编号（必填）
     */
    private String appId;

    /**
     * 证书配置（有默认值）
     */
    private Certificates certificates = new Certificates();

    public String getServerUrl() {
      return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
      this.serverUrl = serverUrl;
    }

    public String getNotifyServerHost() {
      return notifyServerHost;
    }

    public void setNotifyServerHost(String notifyServerHost) {
      this.notifyServerHost = notifyServerHost;
    }

    public String getAppId() {
      return appId;
    }

    public void setAppId(String appId) {
      this.appId = appId;
    }

    public Certificates getCertificates() {
      return certificates;
    }

    public void setCertificates(Certificates certificates) {
      this.certificates = certificates;
    }

    /**
     * 证书配置（有默认值）
     *
     * @author gaigeshen
     */
    public static class Certificates {

      /**
       * 证书文件类型（必需）
       */
      private Type type = Type.CLASSPATH;

      /**
       * 应用私钥（必需）
       */
      private String appPrivateKey = "private_key";

      /**
       * 应用公钥证书（必需）
       */
      private String appCertificate = "app_cert.crt";

      /**
       * 支付宝根证书（必需）
       */
      private String rootCertificate = "alipay_root_cert.crt";

      /**
       * 支付宝公钥证书（必需）
       */
      private String certificate = "alipay_cert.crt";


      public Type getType() {
        return type;
      }

      public void setType(Type type) {
        this.type = type;
      }

      public String getAppPrivateKey() {
        return appPrivateKey;
      }

      public void setAppPrivateKey(String appPrivateKey) {
        this.appPrivateKey = appPrivateKey;
      }

      public String getAppCertificate() {
        return appCertificate;
      }

      public void setAppCertificate(String appCertificate) {
        this.appCertificate = appCertificate;
      }

      public String getRootCertificate() {
        return rootCertificate;
      }

      public void setRootCertificate(String rootCertificate) {
        this.rootCertificate = rootCertificate;
      }

      public String getCertificate() {
        return certificate;
      }

      public void setCertificate(String certificate) {
        this.certificate = certificate;
      }

      /**
       * 证书文件类型
       *
       * @author gaigeshen
       */
      public enum Type {
        /**
         * 文本内容
         */
        CONTENT,
        /**
         * 类路径
         */
        CLASSPATH,
        /**
         * 文件路径
         */
        FILE
      }
    }

  }
}
