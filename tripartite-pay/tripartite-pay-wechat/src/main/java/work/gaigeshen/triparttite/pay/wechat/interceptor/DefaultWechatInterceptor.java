package work.gaigeshen.triparttite.pay.wechat.interceptor;

import org.apache.commons.lang3.RandomStringUtils;
import work.gaigeshen.triparttite.core.header.Headers;
import work.gaigeshen.triparttite.core.interceptor.InterceptingException;
import work.gaigeshen.triparttite.pay.wechat.config.WechatCertificateException;
import work.gaigeshen.triparttite.pay.wechat.config.WechatCertificates;
import work.gaigeshen.triparttite.pay.wechat.config.WechatConfig;
import work.gaigeshen.triparttite.pay.wechat.config.WechatPrivateKey;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 默认的微信拦截器，对每个请求都会执行签名操作，如果当前没有任何有效的微信平台证书则会跳过校验响应内容的操作
 *
 * @author gaigeshen
 */
public class DefaultWechatInterceptor extends AbstractWechatInterceptor {

  private final WechatConfig config;

  public DefaultWechatInterceptor(WechatConfig config) {
    if (Objects.isNull(config)) {
      throw new IllegalArgumentException("wechat config cannot be null");
    }
    this.config = config;
  }

  @Override
  protected void signRequest(Request request) throws InterceptingException {
    String merchantId = config.getMerchantId();
    WechatPrivateKey privateKey = config.getPrivateKey();

    String certSerialNumber = privateKey.getCertSerialNumber();

    String bodyString = new String(request.bodyBytes(), StandardCharsets.UTF_8);

    String[] signature = generateSignature(privateKey, request.method(), request.url(), bodyString);

    String signatureInfo = "mchid=\"" + merchantId + "\",serial_no=\"" + certSerialNumber +
            "\",timestamp=\"" + signature[0] + "\",nonce_str=\"" + signature[1] +
            "\",signature=\"" + signature[2] + "\"";

    request.headers().putValue("Authorization", "WECHATPAY2-SHA256-RSA2048 " + signatureInfo);
  }

  @Override
  protected void validateResponse(Request request, Response response) throws InterceptingException {
    // 当前是否存在有效的微信平台证书
    // 如果不存在则忽略校验
    WechatCertificates certificates = config.getCertificates();
    try {
      certificates.getValidCertificate();
    } catch (WechatCertificateException e) {
      return;
    }
    // 尝试读取需要的响应头
    Headers headers = response.headers();
    String timestamp = readRequiredHeader(headers, "Wechatpay-Timestamp");
    String nonce = readRequiredHeader(headers, "Wechatpay-Nonce");
    String serial = readRequiredHeader(headers, "Wechatpay-Serial");
    String signature = readRequiredHeader(headers, "Wechatpay-Signature");
    // 组装签名字符串
    String signContent = timestamp + "\n" + nonce + "\n" + readBodyString(response) + "\n";
    // 使用微信平台证书验证签名
    if (!certificates.verify(serial, signature, signContent.getBytes(StandardCharsets.UTF_8))) {
      throw new InterceptingException("sign is invalid");
    }
  }

  // 读取必须的请求头或者响应头
  // 读取不到则抛出异常
  private String readRequiredHeader(Headers headers, String headerName) throws InterceptingException {
    if (headers.contains(headerName)) {
      return headers.getValue(headerName);
    } else {
      String headerValue = headers.getValue(headerName.toLowerCase());
      if (Objects.isNull(headerValue)) {
        throw new InterceptingException("missing " + headerName + " header: " + headers);
      }
      return headerValue;
    }
  }

  // 读取响应内容字符串
  // 读取失败抛出异常
  private String readBodyString(Response response) throws InterceptingException {
    String bodyString;
    try {
      bodyString = response.bodyString(StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new InterceptingException("could not read response", e);
    }
    // 为了后续能够再次读取
    response.buffered(bodyString.getBytes(StandardCharsets.UTF_8));
    return bodyString;
  }

  // 给微信请求生成签名
  private String[] generateSignature(WechatPrivateKey privateKey, String method, String url, String bodyString) {
    StringBuilder signContent = new StringBuilder();
    signContent.append(method).append("\n");

    String path = url.substring(url.indexOf("/", url.indexOf("//") + 2));
    signContent.append(path).append("\n");

    String timestamp = Long.toString(System.currentTimeMillis() / 1000);
    signContent.append(timestamp).append("\n");

    String nonce = RandomStringUtils.randomAlphanumeric(16, 33);
    signContent.append(nonce).append("\n");

    signContent.append(bodyString).append("\n");

    String sign = privateKey.sign(signContent.toString().getBytes(StandardCharsets.UTF_8));

    return new String[] { timestamp, nonce, sign };
  }

}
