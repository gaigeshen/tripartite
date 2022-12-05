package work.gaigeshen.tripartite.ding.openapi.config;

import work.gaigeshen.tripartite.core.client.config.Config;

import java.util.Objects;

/**
 * 钉钉配置信息
 *
 * @author gaigeshen
 */
public class DingConfig implements Config {

    /**
     * 新版服务器的地址
     */
    private final String apiServerHost;

    /**
     * 旧版服务器的地址
     */
    private final String oapiServerHost;

    /**
     * 授权企业编号（用于定制应用）
     */
    private final String authCorpId;

    /**
     * 应用相关属性（调用接口的时候可能会用到）
     */
    private final String agentId;

    /**
     * 应用编号（或者定制应用编号）
     */
    private final String appKey;

    /**
     * 应用密钥（或者定制应用密钥）
     */
    private final String appSecret;

    /**
     * 异步通知或者回调通知的密钥（定制应用不可用）
     */
    private final String secretKey;

    /**
     * 异步通知或者回调通知的签名令牌（定制应用不可用）
     */
    private final String token;

    private DingConfig(Builder builder) {
        this.apiServerHost = builder.apiServerHost;
        this.oapiServerHost = builder.oapiServerHost;
        this.authCorpId = builder.authCorpId;
        this.agentId = builder.agentId;
        this.appKey = builder.appKey;
        this.appSecret = builder.appSecret;
        this.secretKey = builder.secretKey;
        this.token = builder.token;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getApiServerHost() {
        return apiServerHost;
    }

    public String getOapiServerHost() {
        return oapiServerHost;
    }

    public String getAuthCorpId() {
        return authCorpId;
    }

    public String getAgentId() {
        return agentId;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DingConfig that = (DingConfig) o;
        return Objects.equals(appKey, that.appKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appKey);
    }

    @Override
    public String toString() {
        return "DingConfig: " + appKey;
    }

    /**
     * @author gaigeshen
     */
    public static class Builder {

        private String apiServerHost;

        private String oapiServerHost;

        private String authCorpId;

        private String agentId;

        private String appKey;

        private String appSecret;

        private String secretKey;

        private String token;

        public Builder setApiServerHost(String apiServerHost) {
            this.apiServerHost = apiServerHost;
            return this;
        }

        public Builder setOapiServerHost(String oapiServerHost) {
            this.oapiServerHost = oapiServerHost;
            return this;
        }

        public Builder setAuthCorpId(String authCorpId) {
            this.authCorpId = authCorpId;
            return this;
        }

        public Builder setAgentId(String agentId) {
            this.agentId = agentId;
            return this;
        }

        public Builder setAppKey(String appKey) {
            this.appKey = appKey;
            return this;
        }

        public Builder setAppSecret(String appSecret) {
            this.appSecret = appSecret;
            return this;
        }

        public Builder setSecretKey(String secretKey) {
            this.secretKey = secretKey;
            return this;
        }

        public Builder setToken(String token) {
            this.token = token;
            return this;
        }

        public DingConfig build() {
            return new DingConfig(this);
        }
    }
}
