package work.gaigeshen.tripartite.core.client.accesstoken;

import lombok.Getter;

import java.util.Date;
import java.util.Objects;

/**
 * @author gaigeshen
 */
@Getter
public class AccessToken {

    private final String accessToken;

    private final long expiresIn; // 有效期时长单位秒

    private final long expiresTimestamp; // 过期时间点单位秒

    private final Date updateTime; // 更新时间

    private AccessToken(Builder builder) {
        this.accessToken = builder.accessToken;
        this.expiresIn = builder.expiresIn;
        this.expiresTimestamp = builder.expiresTimestamp;
        this.updateTime = builder.updateTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "AccessToken: " + accessToken;
    }

    /**
     * @author gaigeshen
     */
    public static class Builder {

        private String accessToken;

        private long expiresIn;

        private long expiresTimestamp;

        private Date updateTime;

        public Builder setAccessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder setExpiresIn(long expiresIn) {
            this.expiresIn = expiresIn;
            return this;
        }

        public Builder setExpiresTimestamp(long expiresTimestamp) {
            this.expiresTimestamp = expiresTimestamp;
            return this;
        }

        public Builder setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public AccessToken build() {
            if (Objects.isNull(accessToken)) {
                throw new IllegalArgumentException("accessToken");
            }
            if (expiresIn < 0) {
                throw new IllegalArgumentException("expiresIn");
            }
            if (expiresTimestamp < 0) {
                throw new IllegalArgumentException("expiresTimestamp");
            }
            if (Objects.isNull(updateTime)) {
                throw new IllegalArgumentException("updateTime");
            }
            return new AccessToken(this);
        }
    }
}
