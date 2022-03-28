package work.gaigeshen.tripartite.pay.alipay;

import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.core.util.json.JsonCodec;
import work.gaigeshen.tripartite.pay.alipay.config.AlipayCertificates;
import work.gaigeshen.tripartite.pay.alipay.config.AlipayConfig;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * 支付宝响应结果拦截器
 *
 * @author gaigeshen
 */
public class AlipayResponseInterceptor extends AbstractInterceptor {

  private final AlipayConfig config;

  public AlipayResponseInterceptor(AlipayConfig config) {
    if (Objects.isNull(config)) {
      throw new IllegalArgumentException("alipay config cannot be null");
    }
    this.config = config;
  }

  @Override
  protected void updateRequest(Request request) throws InterceptingException {
  }

  @Override
  protected void validateResponse(Request request, Response response) throws InterceptingException {
    String rawResponse;
    try {
      rawResponse = response.bodyString(StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new InterceptingException("could not read raw response", e);
    }
    Map<String, Object> decodedResponse = JsonCodec.instance().decodeObject(rawResponse);
    String sign = (String) decodedResponse.get("sign");
    String alipayCertSN = (String) decodedResponse.get("alipay_cert_sn");
    if (Objects.isNull(sign) || Objects.isNull(alipayCertSN)) {
      throw new InterceptingException("sign or alipay cert SN not found: " + rawResponse);
    }
    for (Map.Entry<String, Object> entry : decodedResponse.entrySet()) {
      if (entry.getKey().endsWith("_response")) {
        String alipayResponse = JsonCodec.instance().encode(entry.getValue());
        AlipayCertificates certificates = config.getCertificates();
        if (!certificates.verify(alipayCertSN, sign, alipayResponse.getBytes(StandardCharsets.UTF_8))) {
          throw new InterceptingException("sign is invalid: " + rawResponse);
        }
        response.buffered(alipayResponse.getBytes(StandardCharsets.UTF_8));
        response.headers().putValue("Content-Type", "application/json;charset=utf-8");
        return;
      }
    }
    throw new InterceptingException("response is invalid: " + rawResponse);
  }
}
