package work.gaigeshen.tripartite.pay.wechat.config;

import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.util.Objects;

/**
 * 微信平台证书装饰
 *
 * @author gaigeshen
 */
public abstract class WechatCertificatesDecorator implements WechatCertificates {

  private final WechatCertificates certificates;

  public WechatCertificatesDecorator(WechatCertificates certificates) {
    if (Objects.isNull(certificates)) {
      throw new IllegalArgumentException("wechat certificates cannot be null");
    }
    this.certificates = certificates;
  }

  @Override
  public boolean verify(String serialNumber, String sign, byte[] content) throws WechatCertificateException {
    return certificates.verify(serialNumber, sign, content);
  }

  @Override
  public boolean verify(X509Certificate certificate, String sign, byte[] content) throws WechatCertificateException {
    return certificates.verify(certificate, sign, content);
  }

  @Override
  public X509Certificate getValidCertificate() throws WechatCertificateException {
    return certificates.getValidCertificate();
  }

  @Override
  public String encrypt(X509Certificate certificate, byte[] content) throws WechatCertificateEncryptionException {
    return certificates.encrypt(certificate, content);
  }

  @Override
  public X509Certificate loadCertificate(X509Certificate certificate) {
    return certificates.loadCertificate(certificate);
  }

  @Override
  public X509Certificate loadCertificate(InputStream inputStream) throws WechatCertificateException {
    return certificates.loadCertificate(inputStream);
  }
}
