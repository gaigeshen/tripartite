package work.gaigeshen.tripartite.qyweixin.openapi.config;

import lombok.Getter;
import work.gaigeshen.tripartite.core.client.config.Config;

import java.util.Objects;

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
     * 应用标识
     */
    private final Integer agentId;

    /**
     * 用于校验回调签名
     */
    private final String token;

    /**
     * 用于加解密回调内容
     */
    private final String aesKey;

    private QyWeixinConfig(Builder builder) {
        this.serverHost = builder.serverHost;
        this.corpId = builder.corpId;
        this.corpSecret = builder.corpSecret;
        this.agentId = builder.agentId;
        this.token = builder.token;
        this.aesKey = builder.aesKey;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QyWeixinConfig that = (QyWeixinConfig) o;
        return Objects.equals(corpId, that.corpId) && Objects.equals(agentId, that.agentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corpId, agentId);
    }

    @Override
    public String toString() {
        return "QyWeixinConfig: " + corpId + ", " + agentId;
    }

    /**
     * @author gaigeshen
     */
    public static class Builder {

        private String serverHost;

        private String corpId;

        private String corpSecret;

        private Integer agentId;

        private String token;

        private String aesKey;

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

        public Builder setAgentId(Integer agentId) {
            this.agentId = agentId;
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

        public QyWeixinConfig build() {
            return new QyWeixinConfig(this);
        }
    }
}
