package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author gaigeshen
 */
@Getter
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

    @Override
    public String toString() {
        return "AccessToken: " + account + "/" + type;
    }

    /**
     * @author gaigeshen
     */
    @Setter
    public static class Builder {

        private String accessToken;

        private String account;

        private String type;

        private long expiresIn;

        private long expiresTimestamp;

        private Date updateTime;

        public HisProcurementAccessToken build() {
            return new HisProcurementAccessToken(this);
        }
    }
}
