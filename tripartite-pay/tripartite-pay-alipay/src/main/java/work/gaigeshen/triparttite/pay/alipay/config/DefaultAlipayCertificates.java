package work.gaigeshen.triparttite.pay.alipay.config;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认的支付宝证书集合
 *
 * @author gaigeshen
 */
public class DefaultAlipayCertificates implements AlipayCertificates {

  private final Map<String, X509Certificate> certificates = new ConcurrentHashMap<>();

  static { Security.addProvider(new BouncyCastleProvider()); }

  public DefaultAlipayCertificates() { }

  public DefaultAlipayCertificates(Collection<X509Certificate> certificates) {
    if (Objects.isNull(certificates)) {
      throw new IllegalArgumentException("certificates cannot be null");
    }
    for (X509Certificate certificate : certificates) {
      loadCertificate(certificate);
    }
  }

  public static DefaultAlipayCertificates load(String certificateContent) throws AlipayCertificateException {
    if (Objects.isNull(certificateContent)) {
      throw new IllegalArgumentException("certificate content cannot be null");
    }
    return load(new ByteArrayInputStream(certificateContent.getBytes(StandardCharsets.UTF_8)));
  }

  public static DefaultAlipayCertificates load(InputStream inputStream) throws AlipayCertificateException {
    if (Objects.isNull(inputStream)) {
      throw new IllegalArgumentException("certificate input stream cannot be null");
    }
    DefaultAlipayCertificates certificates = new DefaultAlipayCertificates();
    certificates.loadCertificate(inputStream);
    return certificates;
  }

  public static DefaultAlipayCertificates loadClasspath(String classpath) throws AlipayCertificateException {
    if (Objects.isNull(classpath)) {
      throw new IllegalArgumentException("classpath cannot be null");
    }
    try (InputStream in = DefaultAlipayCertificates.class.getClassLoader().getResourceAsStream(classpath)) {
      return load(in);
    } catch (IOException e) {
      throw new AlipayCertificateException("could not load from classpath: " + classpath, e);
    }
  }

  public static DefaultAlipayCertificates loadFile(String filename) throws AlipayCertificateException {
    if (Objects.isNull(filename)) {
      throw new IllegalArgumentException("filename cannot be null");
    }
    Path path = Paths.get(filename);
    if (!Files.isReadable(path)) {
      throw new IllegalArgumentException("file not readable: " + filename);
    }
    try (InputStream inputStream = Files.newInputStream(path)) {
      return load(inputStream);
    } catch (IOException e) {
      throw new AlipayCertificateException("could not load from file: " + filename, e);
    }
  }

  @Override
  public boolean verify(String serialNumber, String sign, byte[] content) throws AlipayCertificateException {
    if (Objects.isNull(serialNumber)) {
      throw new IllegalArgumentException("serial number cannot be null");
    }
    if (Objects.isNull(sign) || Objects.isNull(content)) {
      throw new IllegalArgumentException("sign and content cannot be null");
    }
    X509Certificate certificate = certificates.get(serialNumber);
    if (Objects.isNull(certificate)) {
      return false;
    }
    try {
      Signature signature = Signature.getInstance("SHA256withRSA");
      signature.initVerify(certificate);
      signature.update(content);
      return signature.verify(Base64.getDecoder().decode(sign));
    } catch (GeneralSecurityException e) {
      throw new AlipayCertificateException("could not verify", e);
    }
  }

  @Override
  public String getValidSerialNumber() throws AlipayCertificateException {
    for (Map.Entry<String, X509Certificate> entry : certificates.entrySet()) {
      try {
        entry.getValue().checkValidity();
        return entry.getKey();
      } catch (GeneralSecurityException ignored) {
      }
    }
    throw new AlipayCertificateException("no valid certificate");
  }

  @Override
  public X509Certificate getValidCertificate() throws AlipayCertificateException {
    for (X509Certificate certificate : certificates.values()) {
      try {
        certificate.checkValidity();
        return certificate;
      } catch (GeneralSecurityException ignored) {
      }
    }
    throw new AlipayCertificateException("no valid certificate");
  }

  @Override
  public X509Certificate loadCertificate(X509Certificate certificate) {
    if (Objects.isNull(certificate)) {
      throw new IllegalArgumentException("certificate cannot be null");
    }
    String principal = certificate.getIssuerX500Principal().getName();
    BigInteger serialNumber = certificate.getSerialNumber();
    String certSN = new BigInteger(1, DigestUtils.md5(principal + serialNumber)).toString(16);
    certificates.put(StringUtils.leftPad(certSN, 32, "0"), certificate);
    return certificate;
  }

  @Override
  public X509Certificate loadCertificate(InputStream inputStream) throws AlipayCertificateException {
    if (Objects.isNull(inputStream)) {
      throw new IllegalArgumentException("certificate input stream cannot be null");
    }
    try {
      CertificateFactory factory = CertificateFactory.getInstance("X.509", "BC");
      X509Certificate certificate = (X509Certificate) factory.generateCertificate(inputStream);
      certificate.checkValidity();
      loadCertificate(certificate);
      return certificate;
    } catch (GeneralSecurityException e) {
      throw new AlipayCertificateException("could not read certificate", e);
    }
  }
}
