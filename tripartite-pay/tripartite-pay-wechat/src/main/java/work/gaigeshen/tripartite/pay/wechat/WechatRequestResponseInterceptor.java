package work.gaigeshen.tripartite.pay.wechat;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import work.gaigeshen.tripartite.core.header.Headers;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.core.util.TimestampUtils;
import work.gaigeshen.tripartite.pay.wechat.config.WechatCertificateException;
import work.gaigeshen.tripartite.pay.wechat.config.WechatCertificates;
import work.gaigeshen.tripartite.pay.wechat.config.WechatConfig;
import work.gaigeshen.tripartite.pay.wechat.config.WechatPrivateKey;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 微信支付请求和响应结果拦截器，给每个请求添加认证请求头，在必要的时候（存在有效的微信平台证书）校验每个响应结果
 *
 * @author gaigeshen
 */
public class WechatRequestResponseInterceptor extends AbstractInterceptor {

    private final WechatConfig config;

    public WechatRequestResponseInterceptor(WechatConfig config) {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("wechat config cannot be null");
        }
        this.config = config;
    }

    @Override
    protected void updateRequest(Request request) throws InterceptingException {
        String requestUrl = request.url();
        String requestPath = StringUtils.substringAfter(StringUtils.substringAfter(requestUrl, "//"), "/");

        StringBuilder signContent = new StringBuilder();
        signContent.append(request.method()).append("\n").append("/").append(requestPath).append("\n");

        String timestamp = TimestampUtils.unixTimestamp();
        signContent.append(timestamp).append("\n");

        String nonce = RandomStringUtils.randomAlphanumeric(16, 33);
        signContent.append(nonce).append("\n");

        signContent.append(new String(request.bodyBytes(), StandardCharsets.UTF_8)).append("\n");

        WechatPrivateKey privateKey = config.getPrivateKey();

        String signInfo = "mchid=\"" + config.getMerchantId() + "\",serial_no=\"" + privateKey.getCertSerialNumber()
                + "\",timestamp=\"" + timestamp + "\",nonce_str=\"" + nonce
                + "\",signature=\"" + privateKey.sign(signContent.toString().getBytes(StandardCharsets.UTF_8)) + "\"";

        Headers headers = request.headers();
        headers.putValue("User-Agent", "Tripartite/1.0.0");
        headers.putValue("Authorization", "WECHATPAY2-SHA256-RSA2048 " + signInfo);
    }

    @Override
    protected void validateResponse(Request request, Response response) throws InterceptingException {
        // 如果不存在有效的微信平台证书则忽略校验
        WechatCertificates certificates = config.getCertificates();
        try {
            certificates.getValidCertificate();
        } catch (WechatCertificateException e) {
            return;
        }
        // 读取响应数据字符串
        String bodyString;
        try {
            bodyString = response.bodyString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new InterceptingException("could not read response: " + response, e);
        }
        response.buffered(bodyString.getBytes(StandardCharsets.UTF_8));
        // 以下校验响应头
        Headers headers = response.headers();
        String timestamp = headers.getValue("Wechatpay-Timestamp");
        if (Objects.isNull(timestamp)) {
            throw new InterceptingException("missing header [Wechatpay-Timestamp]: " + headers);
        }
        String nonce = headers.getValue("Wechatpay-Nonce");
        if (Objects.isNull(nonce)) {
            throw new InterceptingException("missing header [Wechatpay-Nonce]: " + headers);
        }
        String serial = headers.getValue("Wechatpay-Serial");
        if (Objects.isNull(serial)) {
            throw new InterceptingException("missing header [Wechatpay-Serial]: " + headers);
        }
        String signature = headers.getValue("Wechatpay-Signature");
        if (Objects.isNull(signature)) {
            throw new InterceptingException("missing header [Wechatpay-Signature]: " + headers);
        }
        String signContent = timestamp + "\n" + nonce + "\n" + bodyString + "\n";
        // 使用微信平台证书验证签名
        if (!certificates.verify(serial, signature, signContent.getBytes(StandardCharsets.UTF_8))) {
            throw new InterceptingException("response sign is invalid: " + response);
        }
    }
}
