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

    private DingConfig(Builder builder) {
        this.serverHost = builder.serverHost;
        this.accessTokenUri = builder.accessTokenUri;
        this.appKey = builder.appKey;
        this.appSecret = builder.appSecret;
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

        public DingConfig build() {
            return new DingConfig(this);
        }
    }
}
