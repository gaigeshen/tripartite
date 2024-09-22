package work.gaigeshen.tripartite.qyweixin.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业微信配置信息
 *
 * @author gaigeshen
 */
@ConfigurationProperties("spring.qyweixin")
@Data
public class QyWeixinProperties {

    /**
     * 配置信息集合
     */
    private List<Client> clients = new ArrayList<>();

    /**
     * 是否开启服务商模式
     */
    private Boolean provider = false;

    /**
     * 企业微信配置信息
     *
     * @author gaigeshen
     */
    @Data
    public static class Client {

        /**
         * 服务器的地址
         */
        private String serverHost;

        /**
         * 企业编号
         */
        private String corpId;

        /**
         * 应用的凭证密钥
         */
        private String corpSecret;

        /**
         * 服务商的凭证密钥
         */
        private String providerSecret;

        /**
         * 代开发应用模板标识
         */
        private String suiteId;

        /**
         * 代开发应用模板凭证密钥
         */
        private String suiteSecret;

        /**
         * 用于校验回调签名
         */
        private String token;

        /**
         * 用于加解密回调内容
         */
        private String aesKey;

        /**
         * 应用标识
         */
        private Integer agentId;
    }
}
