package work.gaigeshen.tripartite.pay.alipay.config;

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
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Objects;

/**
 * 默认的支付宝根证书
 *
 * @author gaigeshen
 */
public class DefaultAlipayRootCertificate implements AlipayRootCertificate {

  static { Security.addProvider(new BouncyCastleProvider()); }

  private final String serialNumber;

  public DefaultAlipayRootCertificate(String serialNumber) {
    if (Objects.isNull(serialNumber)) {
      throw new IllegalArgumentException("serial number cannot be null");
    }
    this.serialNumber = serialNumber;
  }

  public static DefaultAlipayRootCertificate load(InputStream inputStream) throws AlipayCertificateException {
    if (Objects.isNull(inputStream)) {
      throw new IllegalArgumentException("input stream cannot be null");
    }
    Collection<? extends Certificate> certificates;
    try {
      CertificateFactory factory = CertificateFactory.getInstance("X.509", "BC");
      certificates = factory.generateCertificates(inputStream);
    } catch (GeneralSecurityException e) {
      throw new AlipayCertificateException("could not generate certificates", e);
    }
    String rootCertSN = null;
    for (Certificate certificate : certificates) {
      X509Certificate x509Cert = (X509Certificate) certificate;
      if (x509Cert.getSigAlgOID().startsWith("1.2.840.113549.1.1")) {
        String principal = x509Cert.getIssuerX500Principal().getName();
        BigInteger serialNumber = x509Cert.getSerialNumber();
        String certSN = new BigInteger(1, DigestUtils.md5(principal + serialNumber)).toString(16);
        String certSNPadded = StringUtils.leftPad(certSN, 32, "0");
        rootCertSN = Objects.isNull(rootCertSN) ? certSNPadded : rootCertSN + "_" + certSNPadded;
      }
    }
    if (Objects.isNull(rootCertSN)) {
      throw new AlipayCertificateException("could not read serial number");
    }
    return new DefaultAlipayRootCertificate(rootCertSN);
  }

  public static DefaultAlipayRootCertificate load(String rootCertificateContent) throws AlipayCertificateException {
    if (Objects.isNull(rootCertificateContent)) {
      throw new IllegalArgumentException("certificate content cannot be null");
    }
    return load(new ByteArrayInputStream(rootCertificateContent.getBytes(StandardCharsets.UTF_8)));
  }

  public static DefaultAlipayRootCertificate loadClasspath(String classpath) throws AlipayCertificateException {
    if (Objects.isNull(classpath)) {
      throw new IllegalArgumentException("classpath cannot be null");
    }
    try (InputStream in = DefaultAlipayRootCertificate.class.getClassLoader().getResourceAsStream(classpath)) {
      return load(in);
    } catch (IOException e) {
      throw new AlipayCertificateException("could not load from classpath: " + classpath, e);
    }
  }

  public static DefaultAlipayRootCertificate loadFile(String filename) throws AlipayCertificateException {
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
  public String getSerialNumber() {
    return serialNumber;
  }
}
