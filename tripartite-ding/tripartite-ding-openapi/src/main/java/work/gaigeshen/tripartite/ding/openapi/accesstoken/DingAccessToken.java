package work.gaigeshen.tripartite.ding.openapi.accesstoken;

import java.util.Date;

/**
 * @author gaigeshen
 */
public class DingAccessToken {

    private final String accessToken;

    private final String appKey;

    private final long expiresIn; // 有效期时长单位秒

    private final long expiresTimestamp; // 过期时间点单位秒

    private final Date updateTime; // 更新时间

    private DingAccessToken(Builder builder) {
        this.accessToken = builder.accessToken;
        this.appKey = builder.appKey;
        this.expiresIn = builder.expiresIn;
        this.expiresTimestamp = builder.expiresTimestamp;
        this.updateTime = builder.updateTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getAppKey() {
        return appKey;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public long getExpiresTimestamp() {
        return expiresTimestamp;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        return "AccessToken: " + appKey;
    }

    /**
     * @author gaigeshen
     */
    public static class Builder {

        private String accessToken;

        private String appKey;

        private long expiresIn;

        private long expiresTimestamp;

        private Date updateTime;

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public void setAppKey(String appKey) {
            this.appKey = appKey;
        }

        public void setExpiresIn(long expiresIn) {
            this.expiresIn = expiresIn;
        }

        public void setExpiresTimestamp(long expiresTimestamp) {
            this.expiresTimestamp = expiresTimestamp;
        }

        public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
        }

        public DingAccessToken build() {
            return new DingAccessToken(this);
        }
    }
}
