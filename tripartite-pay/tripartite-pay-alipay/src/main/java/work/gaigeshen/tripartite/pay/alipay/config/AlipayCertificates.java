package work.gaigeshen.tripartite.pay.alipay.config;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.Objects;

/**
 * 支付宝证书集合
 *
 * @author gaigeshen
 */
public interface AlipayCertificates {

  boolean verify(String serialNumber, String sign, byte[] content) throws AlipayCertificateException;

  String getValidSerialNumber() throws AlipayCertificateException;

  X509Certificate getValidCertificate() throws AlipayCertificateException;

  X509Certificate loadCertificate(X509Certificate certificate);

  X509Certificate loadCertificate(InputStream inputStream) throws AlipayCertificateException;

  default X509Certificate loadCertificate(String certificateContent, Charset charset) throws AlipayCertificateException {
    if (Objects.isNull(certificateContent)) {
      throw new IllegalArgumentException("certificate content cannot be null");
    }
    if (Objects.isNull(charset)) {
      throw new IllegalArgumentException("charset cannot be null");
    }
    return loadCertificate(new ByteArrayInputStream(certificateContent.getBytes(charset)));
  }

  default X509Certificate loadCertificate(String certificateContent) throws AlipayCertificateException {
    if (Objects.isNull(certificateContent)) {
      throw new IllegalArgumentException("certificate content cannot be null");
    }
    return loadCertificate(certificateContent, StandardCharsets.UTF_8);
  }
}
