package work.gaigeshen.triparttite.wangdian.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 旺店通客户端配置
 *
 * @author gaigeshen
 */
@ConfigurationProperties("wangdian")
public class WangdianProperties {

  /**
   * 旺店通服务器主机地址（必填）
   */
  private String serverHost;

  /**
   * 旺店通卖家编号（必填）
   */
  private String sellerId;

  /**
   * 特定场景下的默认店铺编号（必填）
   */
  private String shopNo;

  /**
   * 旺店通接口账号（必填）
   */
  private String appKey;

  /**
   * 旺店通接口密钥（必填）
   */
  private String appSecret;

  public String getServerHost() {
    return serverHost;
  }

  public void setServerHost(String serverHost) {
    this.serverHost = serverHost;
  }

  public String getSellerId() {
    return sellerId;
  }

  public void setSellerId(String sellerId) {
    this.sellerId = sellerId;
  }

  public String getShopNo() {
    return shopNo;
  }

  public void setShopNo(String shopNo) {
    this.shopNo = shopNo;
  }

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
}
