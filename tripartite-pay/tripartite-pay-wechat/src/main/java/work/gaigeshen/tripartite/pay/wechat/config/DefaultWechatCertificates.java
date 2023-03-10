package work.gaigeshen.tripartite.pay.wechat.config;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认的微信平台证书
 *
 * @author gaigeshen
 */
public class DefaultWechatCertificates implements WechatCertificates {

    private final Map<BigInteger, X509Certificate> certificates = new ConcurrentHashMap<>();

    public DefaultWechatCertificates() { }

    public DefaultWechatCertificates(Collection<X509Certificate> certificates) {
        if (Objects.isNull(certificates)) {
            throw new IllegalArgumentException("certificates cannot be null");
        }
        for (X509Certificate certificate : certificates) {
            this.certificates.put(certificate.getSerialNumber(), certificate);
        }
    }

    public static DefaultWechatCertificates load(String certificateContent) throws WechatCertificateException {
        if (Objects.isNull(certificateContent)) {
            throw new IllegalArgumentException("certificate content cannot be null");
        }
        return load(new ByteArrayInputStream(certificateContent.getBytes(StandardCharsets.UTF_8)));
    }

    public static DefaultWechatCertificates load(InputStream inputStream) throws WechatCertificateException {
        if (Objects.isNull(inputStream)) {
            throw new IllegalArgumentException("certificate input stream cannot be null");
        }
        DefaultWechatCertificates certificates = new DefaultWechatCertificates();
        certificates.loadCertificate(inputStream);
        return certificates;
    }

    public static DefaultWechatCertificates loadClasspath(String classpath) throws WechatCertificateException {
        if (Objects.isNull(classpath)) {
            throw new IllegalArgumentException("classpath cannot be null");
        }
        try (InputStream in = DefaultWechatCertificates.class.getClassLoader().getResourceAsStream(classpath)) {
            if (Objects.isNull(in)) {
                throw new WechatCertificateException("could not read resource: " + classpath);
            }
            return load(in);
        } catch (IOException e) {
            throw new WechatCertificateException("could not load from classpath: " + classpath, e);
        }
    }

    public static DefaultWechatCertificates loadFile(String filename) throws WechatCertificateException {
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
            throw new WechatCertificateException("could not load from file: " + filename, e);
        }
    }

    @Override
    public boolean verify(String serialNumber, String sign, byte[] content) throws WechatCertificateException {
        if (Objects.isNull(serialNumber)) {
            throw new IllegalArgumentException("serial number cannot be null");
        }
        if (Objects.isNull(sign) || Objects.isNull(content)) {
            throw new IllegalArgumentException("sign and content cannot be null");
        }
        X509Certificate certificate = certificates.get(new BigInteger(serialNumber, 16));
        return Objects.nonNull(certificate) && verify(certificate, sign, content);
    }

    @Override
    public boolean verify(X509Certificate certificate, String sign, byte[] content) throws WechatCertificateException {
        if (Objects.isNull(certificate)) {
            throw new IllegalArgumentException("certificate cannot be null");
        }
        if (Objects.isNull(sign) || Objects.isNull(content)) {
            throw new IllegalArgumentException("sign and content cannot be null");
        }
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(certificate);
            signature.update(content);
            return signature.verify(Base64.getDecoder().decode(sign));
        } catch (GeneralSecurityException e) {
            throw new WechatCertificateException("could not verify", e);
        }
    }

    @Override
    public X509Certificate getValidCertificate() throws WechatCertificateException {
        for (X509Certificate certificate : certificates.values()) {
            try {
                certificate.checkValidity();
                return certificate;
            } catch (GeneralSecurityException ignored) {
            }
        }
        throw new WechatCertificateException("no valid certificate");
    }

    @Override
    public String encrypt(X509Certificate certificate, byte[] content) throws WechatCertificateEncryptionException {
        if (Objects.isNull(certificate)) {
            throw new IllegalArgumentException("certificate cannot be null");
        }
        if (Objects.isNull(content)) {
            throw new IllegalArgumentException("content cannot be null");
        }
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, certificate.getPublicKey());
            byte[] bytes = cipher.doFinal(content);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (GeneralSecurityException e) {
            throw new WechatCertificateEncryptionException("could not encrypt", e);
        }
    }

    @Override
    public X509Certificate loadCertificate(X509Certificate certificate) {
        if (Objects.isNull(certificate)) {
            throw new IllegalArgumentException("certificate cannot be null");
        }
        certificates.put(certificate.getSerialNumber(), certificate);
        return certificate;
    }

    @Override
    public X509Certificate loadCertificate(InputStream inputStream) throws WechatCertificateException {
        if (Objects.isNull(inputStream)) {
            throw new IllegalArgumentException("certificate input stream cannot be null");
        }
        try {
            CertificateFactory factory = CertificateFactory.getInstance("X509");
            X509Certificate certificate = (X509Certificate) factory.generateCertificate(inputStream);
            certificate.checkValidity();
            loadCertificate(certificate);
            return certificate;
        } catch (CertificateException e) {
            throw new WechatCertificateException("could not read certificate", e);
        }
    }
}
