package work.gaigeshen.tripartite.pay.alipay.config;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 支付宝应用私钥
 *
 * @author gaigeshen
 */
public interface AlipayPrivateKey {

  String sign(byte[] content) throws AlipayPrivateKeyException;

  default String sign(String content) throws AlipayPrivateKeyException {
    if (Objects.isNull(content)) {
      throw new IllegalArgumentException("content cannot be null");
    }
    return sign(content.getBytes(StandardCharsets.UTF_8));
  }

  String getCertSerialNumber();
}
