package work.gaigeshen.triparttite.pay.alipay;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import work.gaigeshen.triparttite.core.WebExecutionException;
import work.gaigeshen.triparttite.core.interceptor.InterceptingException;
import work.gaigeshen.triparttite.core.interceptor.Interceptor;
import work.gaigeshen.triparttite.pay.alipay.config.AlipayCertificates;
import work.gaigeshen.triparttite.pay.alipay.config.AlipayConfig;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class AlipayInterceptor implements Interceptor {

  private final AlipayConfig config;

  public AlipayInterceptor(AlipayConfig config) {
    if (Objects.isNull(config)) {
      throw new IllegalArgumentException("alipay config cannot be null");
    }
    this.config = config;
  }

  @Override
  public Response intercept(Request request, Chain chain) throws InterceptingException, WebExecutionException {
    Response response = chain.intercept(request);
    String rawResponse;
    try {
      rawResponse = IOUtils.toString(response.bodyStream(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new InterceptingException("could not read raw response", e);
    }
    if (StringUtils.isBlank(rawResponse)) {
      throw new InterceptingException("raw response not found");
    }
    String alipayResponse = extractAlipayResponse(rawResponse);
    if (StringUtils.isBlank(alipayResponse)) {
      throw new InterceptingException("alipay response not found: " + rawResponse);
    }
    String code = StringUtils.substringBetween(alipayResponse, "\"code\":\"", "\"");
    if (StringUtils.isBlank(code)) {
      throw new InterceptingException("response code not found: " + rawResponse);
    }
    if (!"10000".equals(code)) {
      String subCode = StringUtils.substringBetween(alipayResponse, "\"sub_code\":\"", "\"");
      if (StringUtils.isNotBlank(subCode)) {
        String message = StringUtils.substringBetween(alipayResponse, "\"sub_msg\":\"", "\"");
        throw new InterceptingException("[ "+ subCode +" ] - [ "+ message +" ]");
      }
    }
    String alipayCertSN = StringUtils.substringBetween(rawResponse, "\"alipay_cert_sn\":\"", "\"");
    if (StringUtils.isBlank(alipayCertSN)) {
      throw new InterceptingException("alipay cert SN not found: " + rawResponse);
    }
    String sign = StringUtils.substringBetween(rawResponse, "\"sign\":\"", "\"");
    if (StringUtils.isBlank(sign)) {
      throw new InterceptingException("sign not found: " + rawResponse);
    }
    AlipayCertificates certificates = config.getCertificates();
    if (!certificates.verify(alipayCertSN, sign, alipayResponse.getBytes(StandardCharsets.UTF_8))) {
      throw new InterceptingException("sign is invalid: " + rawResponse);
    }
    response.headers().putValue("Content-Type", "application/json;charset=utf-8");
    response.buffered(alipayResponse.getBytes(StandardCharsets.UTF_8));
    return response;
  }

  private String extractAlipayResponse(String rawResponse) {
    String subResponse = StringUtils.substringAfter(rawResponse, "_response\":");
    LinkedList<String> braces = new LinkedList<>();
    boolean inQuotes = false;
    int escapeCount = 0;
    int index = 0;
    for (char ch : subResponse.toCharArray()) {
      if (ch == '"' && escapeCount % 2 == 0) {
        inQuotes = !inQuotes;
      } else if (ch == '{' && !inQuotes) {
        braces.push("{");
      } else if (ch == '}' && !inQuotes) {
        braces.pop();
        if (braces.isEmpty()) {
          return StringUtils.substring(subResponse, 0, index + 1);
        }
      }
      if (ch == '\\') {
        escapeCount++;
      } else {
        escapeCount = 0;
      }
      index++;
    }
    return "";
  }
}
