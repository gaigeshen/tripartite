package work.gaigeshen.tripartite.pay.wechat;

import work.gaigeshen.tripartite.pay.wechat.config.AutoUpdateWechatCertificates;
import work.gaigeshen.tripartite.pay.wechat.config.WechatSecretKey;
import work.gaigeshen.tripartite.pay.wechat.response.WechatResponse;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * 获取微信平台证书内容的默认实现，需要依赖微信支付客户端
 *
 * @author gaigeshen
 */
public class DefaultWechatCertificatesFetcher implements AutoUpdateWechatCertificates.CertificatesFetcher {

  private static final String CERTIFICATES_URI = "/v3/certificates";

  private final WechatClient wechatClient;

  private final WechatSecretKey secretKey;

  /**
   * 创建此对象
   *
   * @param wechatClient 需要微信支付客户端
   */
  public DefaultWechatCertificatesFetcher(WechatClient wechatClient) {
    if (Objects.isNull(wechatClient)) {
      throw new IllegalArgumentException("wechat client cannot be null");
    }
    this.wechatClient = wechatClient;
    this.secretKey = wechatClient.getWechatConfig().getSecretKey();
  }

  @Override
  public Collection<String> fetchCertificates() {

    Collection<String> result = new HashSet<>();

    WechatCertificatesResponse response = wechatClient.execute(WechatCertificatesResponse.class, CERTIFICATES_URI);

    if (Objects.isNull(response.data) || response.data.isEmpty()) {
      return result;
    }
    for (WechatCertificatesResponse.Certificate certificate : response.data) {
      if (Objects.isNull(certificate.encrypt_certificate)) {
        continue;
      }
      String nonce = certificate.encrypt_certificate.nonce;
      String associatedData = certificate.encrypt_certificate.associated_data;
      String ciphertext = certificate.encrypt_certificate.ciphertext;
      if (Objects.isNull(nonce) || Objects.isNull(associatedData) || Objects.isNull(ciphertext)) {
        continue;
      }
      byte[] nonceBytes = nonce.getBytes(StandardCharsets.UTF_8);
      byte[] associatedDataBytes = associatedData.getBytes(StandardCharsets.UTF_8);

      byte[] decrypted = secretKey.decrypt(ciphertext, nonceBytes, associatedDataBytes);
      result.add(new String(decrypted, StandardCharsets.UTF_8));
    }
    return result;
  }

  /**
   *
   * @author gaigeshen
   */
  public static class WechatCertificatesResponse implements WechatResponse {

    public Collection<Certificate> data;

    public static class Certificate {
      public String serial_no;
      public String effective_time;
      public String expire_time;
      public EncryptCertificate encrypt_certificate;
    }

    public static class EncryptCertificate {
      public String algorithm;
      public String nonce;
      public String associated_data;
      public String ciphertext;
    }
  }

}
