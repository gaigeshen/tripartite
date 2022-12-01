package work.gaigeshen.tripartite.ding.openapi.config;

import work.gaigeshen.tripartite.core.client.config.Config;

import java.util.Objects;

/**
 * 钉钉配置信息
 *
 * @author gaigeshen
 */
public class DingConfig implements Config {

    private final String apiServerHost;

    private final String oapiServerHost;

    private final String suiteId;

    private final String appKey;

    private final String appSecret;

    private final String secretKey;

    private final String token;

    private DingConfig(Builder builder) {
        this.apiServerHost = builder.apiServerHost;
        this.oapiServerHost = builder.oapiServerHost;
        this.suiteId = builder.suiteId;
        this.appKey = builder.appKey;
        this.appSecret = builder.appSecret;
        this.secretKey = builder.secretKey;
        this.token = builder.token;
    }

    protected DingConfig(DingConfig copyFrom) {
        this.apiServerHost = copyFrom.apiServerHost;
        this.oapiServerHost = copyFrom.oapiServerHost;
        this.suiteId = copyFrom.suiteId;
        this.appKey = copyFrom.appKey;
        this.appSecret = copyFrom.appSecret;
        this.secretKey = copyFrom.secretKey;
        this.token = copyFrom.token;
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

    public String getSuiteId() {
        return suiteId;
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

        private String suiteId;

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

        public Builder setSuiteId(String suiteId) {
            this.suiteId = suiteId;
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
