package work.gaigeshen.tripartite.pay.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 支付宝客户端配置
 *
 * @author gaigeshen
 */
@ConfigurationProperties("spring.alipay")
@Data
public class AlipayProperties {

    private List<Client> clients = new ArrayList<>();

    /**
     * @author gaigeshen
     */
    @Data
    public static class Client {

        /**
         * 支付宝服务器地址（必填）
         */
        private String serverUrl;

        /**
         * 异步通知主机（必填）
         */
        private String notifyServerHost;

        /**
         * 应用编号（必填）
         */
        private String appId;

        /**
         * 证书配置（有默认值）
         */
        private Certificates certificates = new Certificates();

        /**
         * 证书配置（有默认值）
         *
         * @author gaigeshen
         */
        @Data
        public static class Certificates {

            /**
             * 证书文件类型（必需）
             */
            private Type type = Type.CLASSPATH;

            /**
             * 应用私钥（必需）
             */
            private String appPrivateKey = "private_key";

            /**
             * 应用公钥证书（必需）
             */
            private String appCertificate = "app_cert.crt";

            /**
             * 支付宝根证书（必需）
             */
            private String rootCertificate = "alipay_root_cert.crt";

            /**
             * 支付宝公钥证书（必需）
             */
            private String certificate = "alipay_cert.crt";

            /**
             * 证书文件类型
             *
             * @author gaigeshen
             */
            public enum Type {
                /**
                 * 文本内容
                 */
                CONTENT,
                /**
                 * 类路径
                 */
                CLASSPATH,
                /**
                 * 文件路径
                 */
                FILE
            }
        }

    }
}
