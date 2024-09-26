package work.gaigeshen.tripartite.core.client.accesstoken;

import org.apache.commons.lang3.StringUtils;
import work.gaigeshen.tripartite.core.client.config.Config;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.Date;
import java.util.Objects;

/**
 * @author gaigeshen
 */
public class AccessTokenHelper {

    private AccessTokenHelper() { }

    /**
     * 返回该访问令牌剩余有效时长单位秒
     *
     * @param accessToken 访问令牌不可为空
     * @return 剩余有效时长单位秒
     */
    public static long getRemainingDuration(AccessToken accessToken) {
        ArgumentValidate.notNull(accessToken, "accessToken cannot be null");
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
    public static AccessToken createAccessToken(Config config, String newAccessToken, long expireInSeconds) {
        ArgumentValidate.notTrue(Objects.isNull(config) || Objects.isNull(newAccessToken),
                "config and new access token value cannot be null");
        AccessToken.Builder builder = AccessToken.builder()
                .setAccessToken(newAccessToken).setExpiresIn(expireInSeconds)
                .setExpiresTimestamp(System.currentTimeMillis() / 1000 + expireInSeconds)
                .setUpdateTime(new Date());
        return builder.build();
    }

    /**
     * 返回该访问令牌是否已经过期
     *
     * @param accessToken 访问令牌不可为空
     * @return 是否已经过期
     */
    public static boolean isExpired(AccessToken accessToken) {
        ArgumentValidate.notNull(accessToken, "accessToken cannot be null");
        return accessToken.getExpiresTimestamp() <= System.currentTimeMillis() / 1000;
    }

    /**
     * 返回访问令牌是否有效，有效的访问令牌必需包含访问令牌值，且未过期的
     *
     * @param accessToken 访问令牌不可为空
     * @return 是否有效
     */
    public static boolean isValid(AccessToken accessToken) {
        ArgumentValidate.notNull(accessToken, "accessToken cannot be null");
        if (isExpired(accessToken)) {
            return false;
        }
        return StringUtils.isNotBlank(accessToken.getAccessToken());
    }
}
