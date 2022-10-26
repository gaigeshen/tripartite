package work.gaigeshen.tripartite.pay.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaigeshen
 */
@ConfigurationProperties("wechat")
public class WechatProperties {

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
     * 微信服务主机地址（必填）
     */
    private String serverHost = "https://api.mch.weixin.qq.com";

    /**
     * 异步通知主机（必填）
     */
    private String notifyServerHost;

    /**
     * 应用编号（必填）
     */
    private String appId;

    /**
     * 商户编号（必填）
     */
    private String merchantId;

    /**
     * 证书配置（有默认值）
     */
    private Certificates certificates = new Certificates();

    public String getServerHost() {
      return serverHost;
    }

    public void setServerHost(String serverHost) {
      this.serverHost = serverHost;
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

    public String getMerchantId() {
      return merchantId;
    }

    public void setMerchantId(String merchantId) {
      this.merchantId = merchantId;
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
       * 商户密钥（必需）
       */
      private String secretKey = "secret_key";

      /**
       * 应用私钥（必需）
       */
      private String privateKey = "private_key";

      /**
       * 应用公钥证书序列号，这里直接填写证书序列号（必需）
       */
      private String appCertSerialNumber = "app_cert_serial_number";

      /**
       * 微信平台证书（必需）
       */
      private String certificate = "wechat_cert.crt";

      /**
       * 微信平台证书更新间隔时间（必需）
       */
      private Integer certificateUpdatePeriodSeconds = 3600 * 8;

      public Type getType() {
        return type;
      }

      public void setType(Type type) {
        this.type = type;
      }

      public String getSecretKey() {
        return secretKey;
      }

      public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
      }

      public String getPrivateKey() {
        return privateKey;
      }

      public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
      }

      public String getAppCertSerialNumber() {
        return appCertSerialNumber;
      }

      public void setAppCertSerialNumber(String appCertSerialNumber) {
        this.appCertSerialNumber = appCertSerialNumber;
      }

      public String getCertificate() {
        return certificate;
      }

      public void setCertificate(String certificate) {
        this.certificate = certificate;
      }

      public Integer getCertificateUpdatePeriodSeconds() {
        return certificateUpdatePeriodSeconds;
      }

      public void setCertificateUpdatePeriodSeconds(Integer certificateUpdatePeriodSeconds) {
        this.certificateUpdatePeriodSeconds = certificateUpdatePeriodSeconds;
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
