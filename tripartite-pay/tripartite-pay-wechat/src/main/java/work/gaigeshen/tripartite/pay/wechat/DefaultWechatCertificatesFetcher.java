package work.gaigeshen.tripartite.pay.wechat;

import com.fasterxml.jackson.databind.JsonNode;
import work.gaigeshen.tripartite.core.response.converter.StringResponseConverter;
import work.gaigeshen.tripartite.core.util.JacksonUtils;
import work.gaigeshen.tripartite.pay.wechat.config.AutoUpdateWechatCertificates;
import work.gaigeshen.tripartite.pay.wechat.config.WechatSecretKey;

import java.nio.charset.StandardCharsets;
import java.util.*;

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

    String response = wechatClient.execute(StringResponseConverter.INSTANCE, CERTIFICATES_URI);

    JsonNode dataJsonNode = JacksonUtils.toJsonNode(response).get("data");

    JsonNode certificatesJsonNode = dataJsonNode.get("data");
    if (Objects.isNull(certificatesJsonNode)) {
      return Collections.emptySet();
    }
    Collection<String> result = new HashSet<>();
    for (JsonNode certificateJsonNode : certificatesJsonNode) {
      JsonNode encryptCertificateJsonNode = certificateJsonNode.get("encrypt_certificate");
      if (Objects.isNull(encryptCertificateJsonNode)) {
        continue;
      }
      JsonNode nonce = encryptCertificateJsonNode.get("nonce");
      JsonNode associatedData = encryptCertificateJsonNode.get("associated_data");
      JsonNode ciphertext = encryptCertificateJsonNode.get("ciphertext");
      if (Objects.isNull(nonce) || Objects.isNull(associatedData) || Objects.isNull(ciphertext)) {
        continue;
      }
      if (!nonce.isTextual() || !associatedData.isTextual() || !ciphertext.isTextual()) {
        continue;
      }
      byte[] nonceBytes = nonce.textValue().getBytes(StandardCharsets.UTF_8);
      byte[] associatedDataBytes = associatedData.textValue().getBytes(StandardCharsets.UTF_8);

      byte[] decrypted = secretKey.decrypt(ciphertext.textValue(), nonceBytes, associatedDataBytes);
      result.add(new String(decrypted, StandardCharsets.UTF_8));
    }
    return result;
  }
}
