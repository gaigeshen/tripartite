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

    private QyWeixinConfig(Builder builder) {
        this.serverHost = builder.serverHost;
        this.corpId = builder.corpId;
        this.corpSecret = builder.corpSecret;
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
        return Objects.equals(corpId, that.corpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corpId);
    }

    @Override
    public String toString() {
        return "QyWexinConfig: " + corpId;
    }

    /**
     * @author gaigeshen
     */
    public static class Builder {

        private String serverHost;

        private String corpId;

        private String corpSecret;

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

        public QyWeixinConfig build() {
            return new QyWeixinConfig(this);
        }
    }
}
