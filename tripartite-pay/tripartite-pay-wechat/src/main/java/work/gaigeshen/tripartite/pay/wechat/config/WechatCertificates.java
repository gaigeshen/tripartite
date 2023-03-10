package work.gaigeshen.tripartite.pay.wechat.config;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.Objects;

/**
 * 微信平台证书可用于微信响应数据验签和提交敏感数据加密
 *
 * @author gaigeshen
 */
public interface WechatCertificates {

    boolean verify(String serialNumber, String sign, byte[] content) throws WechatCertificateException;

    boolean verify(X509Certificate certificate, String sign, byte[] content) throws WechatCertificateException;

    X509Certificate getValidCertificate() throws WechatCertificateException;

    String encrypt(X509Certificate certificate, byte[] content) throws WechatCertificateEncryptionException;

    default String encrypt(byte[] content) throws WechatCertificateException {
        if (Objects.isNull(content)) {
            throw new IllegalArgumentException("content cannot be null");
        }
        return encrypt(getValidCertificate(), content);
    }

    X509Certificate loadCertificate(X509Certificate certificate);

    X509Certificate loadCertificate(InputStream inputStream) throws WechatCertificateException;

    default X509Certificate loadCertificate(String certificateContent, Charset charset) throws WechatCertificateException {
        if (Objects.isNull(certificateContent)) {
            throw new IllegalArgumentException("certificate content cannot be null");
        }
        if (Objects.isNull(charset)) {
            throw new IllegalArgumentException("charset cannot be null");
        }
        return loadCertificate(new ByteArrayInputStream(certificateContent.getBytes(charset)));
    }

    default X509Certificate loadCertificate(String certificateContent) throws WechatCertificateException {
        if (Objects.isNull(certificateContent)) {
            throw new IllegalArgumentException("certificate content cannot be null");
        }
        return loadCertificate(certificateContent, StandardCharsets.UTF_8);
    }
}
