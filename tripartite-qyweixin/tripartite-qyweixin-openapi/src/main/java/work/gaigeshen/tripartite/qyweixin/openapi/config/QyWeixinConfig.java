package work.gaigeshen.tripartite.qyweixin.openapi.config;

import lombok.Getter;
import work.gaigeshen.tripartite.core.client.config.Config;

/**
 * 企业微信配置信息
 *
 * @author gaigeshen
 */
@Getter
public class QyWeixinConfig implements Config {

    /**
     * 服务器的地址
     */
    private final String serverHost;

    /**
     * 企业编号
     */
    private final String corpId;

    /**
     * 应用的凭证密钥
     */
    private final String corpSecret;

    /**
     * 服务商的凭证密钥
     */
    private final String providerSecret;

    /**
     * 代开发应用模板标识
     */
    private final String suiteId;

    /**
     * 代开发应用模板凭证密钥
     */
    private final String suiteSecret;

    /**
     * 用于校验回调签名
     */
    private final String token;

    /**
     * 用于加解密回调内容
     */
    private final String aesKey;

    /**
     * 应用标识
     */
    private final Integer agentId;

    private QyWeixinConfig(Builder builder) {
        this.serverHost = builder.serverHost;
        this.corpId = builder.corpId;
        this.corpSecret = builder.corpSecret;
        this.providerSecret = builder.providerSecret;
        this.suiteId = builder.suiteId;
        this.suiteSecret = builder.suiteSecret;
        this.token = builder.token;
        this.aesKey = builder.aesKey;
        this.agentId = builder.agentId;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "QyWeixinConfig: " + corpId;
    }

    /**
     * @author gaigeshen
     */
    public static class Builder {

        private String serverHost;

        private String corpId;

        private String corpSecret;

        private String providerSecret;

        private String suiteId;

        private String suiteSecret;

        private String token;

        private String aesKey;

        private Integer agentId;

        public Builder setServerHost(String serverHost) {
            this.serverHost = serverHost;
            return this;
        }

        public Builder setCorpId(String corpId) {
            this.corpId = corpId;
            return this;
        }

        public Builder setCorpSecret(String corpSecret) {
            this.corpSecret = corpSecret;
            return this;
        }

        public Builder setProviderSecret(String providerSecret) {
            this.providerSecret = providerSecret;
            return this;
        }

        public Builder setSuiteId(String suiteId) {
            this.suiteId = suiteId;
            return this;
        }

        public Builder setSuiteSecret(String suiteSecret) {
            this.suiteSecret = suiteSecret;
            return this;
        }

        public Builder setToken(String token) {
            this.token = token;
            return this;
        }

        public Builder setAesKey(String aesKey) {
            this.aesKey = aesKey;
            return this;
        }

        public Builder setAgentId(Integer agentId) {
            this.agentId = agentId;
            return this;
        }

        public QyWeixinConfig build() {
            return new QyWeixinConfig(this);
        }
    }
}
