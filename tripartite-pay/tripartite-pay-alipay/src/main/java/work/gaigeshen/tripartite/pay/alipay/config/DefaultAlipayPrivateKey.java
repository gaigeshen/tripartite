package work.gaigeshen.tripartite.pay.alipay.config;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
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
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

/**
 * 默认的支付宝应用私钥
 *
 * @author gaigeshen
 */
public class DefaultAlipayPrivateKey implements AlipayPrivateKey {

  static { Security.addProvider(new BouncyCastleProvider()); }

  private final PrivateKey privateKey;

  private final String certSerialNumber;

  public DefaultAlipayPrivateKey(PrivateKey privateKey, String certSerialNumber) {
    if (Objects.isNull(privateKey)) {
      throw new IllegalArgumentException("private key cannot be null");
    }
    if (Objects.isNull(certSerialNumber)) {
      throw new IllegalArgumentException("certificate serial number cannot be null");
    }
    this.privateKey = privateKey;
    this.certSerialNumber = certSerialNumber;
  }

  public static DefaultAlipayPrivateKey load(String privateKeyContent, String certContent) throws AlipayPrivateKeyException, AlipayCertificateException {
    if (Objects.isNull(privateKeyContent)) {
      throw new IllegalArgumentException("private key content cannot be null");
    }
    if (Objects.isNull(certContent)) {
      throw new IllegalArgumentException("certificate content cannot be null");
    }
    String certSerialNumber = getCertSerialNumber(new ByteArrayInputStream(certContent.getBytes(StandardCharsets.UTF_8)));
    return new DefaultAlipayPrivateKey(genPrivateKey(privateKeyContent), certSerialNumber);
  }

  public static DefaultAlipayPrivateKey loadClasspath(String privateKeyClasspath, String certClasspath) throws AlipayPrivateKeyException, AlipayCertificateException {
    if (Objects.isNull(privateKeyClasspath)) {
      throw new IllegalArgumentException("private key classpath cannot be null");
    }
    if (Objects.isNull(certClasspath)) {
      throw new IllegalArgumentException("certificate classpath cannot be null");
    }
    String privateKeyContent, certContent;
    try (InputStream in = DefaultAlipayPrivateKey.class.getClassLoader().getResourceAsStream(privateKeyClasspath)) {
      if (Objects.isNull(in)) {
        throw new AlipayPrivateKeyException("could not read resource: " + privateKeyClasspath);
      }
      privateKeyContent = IOUtils.toString(in, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new AlipayPrivateKeyException(e.getMessage(), e);
    }
    try (InputStream in = DefaultAlipayPrivateKey.class.getClassLoader().getResourceAsStream(certClasspath)) {
      if (Objects.isNull(in)) {
        throw new AlipayCertificateException("could not read resource: " + certClasspath);
      }
      certContent = IOUtils.toString(in, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new AlipayCertificateException(e.getMessage(), e);
    }
    return load(privateKeyContent, certContent);
  }

  public static DefaultAlipayPrivateKey loadFile(String privateKeyFilename, String certFilename) throws AlipayPrivateKeyException, AlipayCertificateException {
    if (Objects.isNull(privateKeyFilename)) {
      throw new IllegalArgumentException("private key filename cannot be null");
    }
    if (Objects.isNull(certFilename)) {
      throw new IllegalArgumentException("certificate filename cannot be null");
    }
    Path privateKeyPath = Paths.get(privateKeyFilename);
    if (!Files.isReadable(privateKeyPath)) {
      throw new IllegalArgumentException("file not readable: " + privateKeyFilename);
    }
    Path certPath = Paths.get(certFilename);
    if (!Files.isReadable(certPath)) {
      throw new IllegalArgumentException("file not readable: " + certFilename);
    }
    try {
      String privateKeyContent = Files.readString(privateKeyPath);
      String certContent = Files.readString(certPath);
      return load(privateKeyContent, certContent);
    } catch (IOException e) {
      throw new AlipayCertificateException(e.getMessage(), e);
    }
  }

  private static PrivateKey genPrivateKey(String privateKeyContent) throws AlipayPrivateKeyException {
    if (Objects.isNull(privateKeyContent)) {
      throw new IllegalArgumentException("private key content cannot be null");
    }
    try {
      KeyFactory factory = KeyFactory.getInstance("RSA");
      KeySpec ks = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
      return factory.generatePrivate(ks);
    }
    catch (NoSuchAlgorithmException e) {
      throw new AlipayPrivateKeyException("'RSA' is not supported", e);
    }
    catch (InvalidKeySpecException e) {
      throw new AlipayPrivateKeyException("could not generate private key", e);
    }
  }

  private static String getCertSerialNumber(InputStream inputStream) throws AlipayCertificateException {
    if (Objects.isNull(inputStream)) {
      throw new IllegalArgumentException("certificate input stream cannot be null");
    }
    X509Certificate certificate;
    try {
      CertificateFactory factory = CertificateFactory.getInstance("X.509", "BC");
      certificate = (X509Certificate) factory.generateCertificate(inputStream);
    } catch (GeneralSecurityException e) {
      throw new AlipayCertificateException("could not generate certificate", e);
    }
    String principal = certificate.getIssuerX500Principal().getName();
    BigInteger serialNumber = certificate.getSerialNumber();
    String certSN = new BigInteger(1, DigestUtils.md5(principal + serialNumber)).toString(16);
    return StringUtils.leftPad(certSN, 32, "0");
  }

  @Override
  public String sign(byte[] content) throws AlipayPrivateKeyException {
    if (Objects.isNull(content)) {
      throw new IllegalArgumentException("content cannot be null");
    }
    try {
      Signature signature = Signature.getInstance("SHA256withRSA");
      signature.initSign(privateKey);
      signature.update(content);
      byte[] signResult = signature.sign();
      return Base64.getEncoder().encodeToString(signResult);
    }
    catch (InvalidKeyException e) {
      throw new AlipayPrivateKeyException("private key is invalid", e);
    }
    catch (GeneralSecurityException e) {
      throw new AlipayPrivateKeySigningException("failed to calculate signature", e);
    }
  }

  @Override
  public String getCertSerialNumber() {
    return certSerialNumber;
  }
}
