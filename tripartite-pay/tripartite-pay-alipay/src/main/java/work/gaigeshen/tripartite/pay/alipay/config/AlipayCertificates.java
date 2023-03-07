package work.gaigeshen.tripartite.pay.alipay.config;

import java.io.InputStream;
import java.security.cert.X509Certificate;

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
}
