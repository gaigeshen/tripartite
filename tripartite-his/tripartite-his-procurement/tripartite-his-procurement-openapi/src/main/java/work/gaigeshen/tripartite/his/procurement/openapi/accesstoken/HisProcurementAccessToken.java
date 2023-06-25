package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

import java.util.Date;

/**
 * @author gaigeshen
 */
public class HisProcurementAccessToken {

    private final String accessToken;

    private final String account;

    private final String type;

    private final long expiresIn; // 有效期时长单位秒

    private final long expiresTimestamp; // 过期时间点单位秒

    private final Date updateTime; // 更新时间

    private HisProcurementAccessToken(Builder builder) {
        this.accessToken = builder.accessToken;
        this.account = builder.account;
        this.type = builder.type;
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

    public String getAccount() {
        return account;
    }

    public String getType() {
        return type;
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
        return "AccessToken: " + account + "/" + type;
    }

    /**
     * @author gaigeshen
     */
    public static class Builder {

        private String accessToken;

        private String account;

        private String type;

        private long expiresIn;

        private long expiresTimestamp;

        private Date updateTime;

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public void setType(String type) {
            this.type = type;
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

        public HisProcurementAccessToken build() {
            return new HisProcurementAccessToken(this);
        }
    }
}
