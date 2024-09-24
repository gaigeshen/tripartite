package work.gaigeshen.tripartite.pay.alipay.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * 支付宝客户端配置
 *
 * @author gaigeshen
 */
@Getter
public class AlipayConfig {

    private final String serverUrl;

    private final String notifyUrl;

    private final String appId;

    private final AlipayPrivateKey privateKey;

    private final AlipayRootCertificate rootCertificate;

    private final AlipayCertificates certificates;

    private AlipayConfig(Builder builder) {
        this.serverUrl = builder.serverUrl;
        this.notifyUrl = builder.notifyUrl;
        this.appId = builder.appId;
        this.privateKey = builder.privateKey;
        this.rootCertificate = builder.rootCertificate;
        this.certificates = builder.certificates;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int hashCode() {
        return appId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (Objects.isNull(obj) || getClass() != obj.getClass()) {
            return false;
        }
        return appId.equals(((AlipayConfig) obj).appId);
    }

    @Override
    public String toString() {
        return "AlipayConfig: " + appId;
    }

    /**
     * @author gaigeshen
     */
    @Setter
    public static class Builder {

        private String serverUrl;

        private String notifyUrl;

        private String appId;

        private AlipayPrivateKey privateKey;

        private AlipayRootCertificate rootCertificate;

        private AlipayCertificates certificates;

        public void setPrivateKeyContent(String privateKeyContent, String certContent) throws AlipayPrivateKeyException, AlipayCertificateException {
            setPrivateKey(DefaultAlipayPrivateKey.load(privateKeyContent, certContent));
        }

        public void setRootCertificateContent(String rootCertificateContent) throws AlipayCertificateException {
            setRootCertificate(DefaultAlipayRootCertificate.load(rootCertificateContent));
        }

        public void setCertificateContent(String certificateContent) throws AlipayCertificateException {
            setCertificates(DefaultAlipayCertificates.load(certificateContent));
        }

        public void setPrivateKeyClasspath(String privateKeyClasspath, String certClasspath) throws AlipayPrivateKeyException, AlipayCertificateException {
            setPrivateKey(DefaultAlipayPrivateKey.loadClasspath(privateKeyClasspath, certClasspath));
        }

        public void setRootCertificateClasspath(String rootCertificateClasspath) throws AlipayCertificateException {
            setRootCertificate(DefaultAlipayRootCertificate.loadClasspath(rootCertificateClasspath));
        }

        public void setCertificateClasspath(String certificateClasspath) throws AlipayCertificateException {
            setCertificates(DefaultAlipayCertificates.loadClasspath(certificateClasspath));
        }

        public void setPrivateKeyFile(String privateKeyFilename, String certFilename) throws AlipayPrivateKeyException, AlipayCertificateException {
            setPrivateKey(DefaultAlipayPrivateKey.loadFile(privateKeyFilename, certFilename));
        }

        public void setRootCertificateFile(String rootCertificateFilename) throws AlipayCertificateException {
            setRootCertificate(DefaultAlipayRootCertificate.loadFile(rootCertificateFilename));
        }

        public void setCertificateFile(String certificateFilename) throws AlipayCertificateException {
            setCertificates(DefaultAlipayCertificates.loadFile(certificateFilename));
        }

        public AlipayConfig build() throws AlipayConfigException {
            if (Objects.isNull(serverUrl)) {
                throw new AlipayConfigException("'serverUrl' not found");
            }
            if (Objects.isNull(notifyUrl)) {
                throw new AlipayConfigException("'notifyUrl' not found");
            }
            if (Objects.isNull(appId)) {
                throw new AlipayConfigException("'appId' not found");
            }
            if (Objects.isNull(privateKey)) {
                throw new AlipayConfigException("'privateKey' not found");
            }
            if (Objects.isNull(rootCertificate)) {
                throw new AlipayConfigException("'rootCertificate' not found");
            }
            if (Objects.isNull(certificates)) {
                throw new AlipayConfigException("'certificates' not found");
            }
            return new AlipayConfig(this);
        }
    }
}
