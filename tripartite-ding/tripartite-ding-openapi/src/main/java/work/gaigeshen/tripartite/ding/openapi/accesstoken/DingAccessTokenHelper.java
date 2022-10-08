package work.gaigeshen.tripartite.ding.openapi.accesstoken;

import org.apache.commons.lang3.StringUtils;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.Date;
import java.util.Objects;

/**
 * @author gaigeshen
 */
public class DingAccessTokenHelper {

    private DingAccessTokenHelper() { }

    /**
     * 返回该访问令牌剩余有效时长单位秒
     *
     * @param accessToken 访问令牌不可为空
     * @return 剩余有效时长单位秒
     */
    public static long getRemainingDuration(DingAccessToken accessToken) {
        if (Objects.isNull(accessToken)) {
            throw new IllegalArgumentException("accessToken cannot be null");
        }
        if (isExpired(accessToken)) {
            return 0;
        }
        return accessToken.getExpiresTimestamp() - System.currentTimeMillis() / 1000;
    }

    /**
     * 创建新的访问令牌
     *
     * @param config 配置信息不能为空
     * @param newAccessToken 访问令牌内容不能为空
     * @param expireInSeconds 访问令牌过期时间单位秒
     * @return 新的访问令牌
     */
    public static DingAccessToken createAccessToken(DingConfig config, String newAccessToken, long expireInSeconds) {
        if (Objects.isNull(config) || Objects.isNull(newAccessToken)) {
            throw new IllegalArgumentException("config and new access token value cannot be null");
        }
        DingAccessToken.Builder builder = DingAccessToken.builder();
        builder.setAccessToken(newAccessToken);
        builder.setAppKey(config.getAppKey());
        builder.setExpiresIn(expireInSeconds);
        builder.setExpiresTimestamp(System.currentTimeMillis() / 1000 + expireInSeconds);
        builder.setUpdateTime(new Date());
        return builder.build();
    }

    /**
     * 返回该访问令牌是否已经过期
     *
     * @param accessToken 访问令牌不可为空
     * @return 是否已经过期
     */
    public static boolean isExpired(DingAccessToken accessToken) {
        if (Objects.isNull(accessToken)) {
            throw new IllegalArgumentException("accessToken cannot be null");
        }
        return accessToken.getExpiresTimestamp() <= System.currentTimeMillis() / 1000;
    }

    /**
     * 返回访问令牌是否有效，有效的访问令牌必需包含访问令牌值
     *
     * @param accessToken 访问令牌不可为空
     * @return 是否有效
     */
    public static boolean isValid(DingAccessToken accessToken) {
        if (Objects.isNull(accessToken)) {
            throw new IllegalArgumentException("accessToken cannot be null");
        }
        return !StringUtils.isAnyBlank(accessToken.getAccessToken(), accessToken.getAppKey());
    }
}
