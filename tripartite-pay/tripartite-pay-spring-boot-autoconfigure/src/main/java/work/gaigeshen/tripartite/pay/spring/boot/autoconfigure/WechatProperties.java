package work.gaigeshen.tripartite.pay.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaigeshen
 */
@ConfigurationProperties("spring.wechat")
@Data
public class WechatProperties {

    private List<Client> clients = new ArrayList<>();

    /**
     * @author gaigeshen
     */
    @Data
    public static class Client {

        /**
         * 微信服务主机地址（必填）
         */
        private String serverHost = "https://api.mch.weixin.qq.com";

        /**
         * 异步通知主机（必填）
         */
        private String notifyServerHost;

        /**
         * 应用编号（必填）
         */
        private String appId;

        /**
         * 商户编号（必填）
         */
        private String merchantId;

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
             * 商户密钥（必需）
             */
            private String secretKey = "secret_key";

            /**
             * 应用私钥（必需）
             */
            private String privateKey = "private_key";

            /**
             * 应用公钥证书序列号，这里直接填写证书序列号（必需）
             */
            private String appCertSerialNumber = "app_cert_serial_number";

            /**
             * 微信平台证书（必需）
             */
            private String certificate = "wechat_cert.crt";

            /**
             * 微信平台证书更新间隔时间（必需）
             */
            private Integer certificateUpdatePeriodSeconds = 3600 * 8;

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
