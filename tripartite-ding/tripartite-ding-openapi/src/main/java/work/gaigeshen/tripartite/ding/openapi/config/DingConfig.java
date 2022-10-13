package work.gaigeshen.tripartite.ding.openapi.config;

import work.gaigeshen.tripartite.core.client.config.Config;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class DingConfig implements Config {

    private final String serverHost;

    private final String accessTokenUri;

    private final String appKey;

    private final String appSecret;

    private final String secretKey;

    private final String token;

    private DingConfig(Builder builder) {
        this.serverHost = builder.serverHost;
        this.accessTokenUri = builder.accessTokenUri;
        this.appKey = builder.appKey;
        this.appSecret = builder.appSecret;
        this.secretKey = builder.secretKey;
        this.token = builder.token;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String getServerHost() {
        return serverHost;
    }

    public String getAccessTokenUri() {
        return accessTokenUri;
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

        private String serverHost;

        private String accessTokenUri;

        private String appKey;

        private String appSecret;

        private String secretKey;

        private String token;

        public Builder setServerHost(String serverHost) {
            this.serverHost = serverHost;
            return this;
        }

        public Builder setAccessTokenUri(String accessTokenUri) {
            this.accessTokenUri = accessTokenUri;
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
