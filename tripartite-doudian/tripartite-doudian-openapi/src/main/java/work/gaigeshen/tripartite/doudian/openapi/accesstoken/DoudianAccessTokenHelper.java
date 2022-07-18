package work.gaigeshen.tripartite.doudian.openapi.accesstoken;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 访问令牌帮助类
 *
 * @author gaigeshen
 */
public abstract class DoudianAccessTokenHelper {

  private DoudianAccessTokenHelper() { }

  /**
   * 返回该访问令牌是否已经过期
   *
   * @param accessToken 访问令牌不可为空
   * @return 是否已经过期
   */
  public static boolean isExpired(DoudianAccessToken accessToken) {
    if (Objects.isNull(accessToken)) {
      throw new IllegalArgumentException("accessToken cannot be null");
    }
    return accessToken.getExpiresTimestamp() > System.currentTimeMillis() / 1000;
  }

  /**
   * 返回访问令牌是否有效，有效的访问令牌必需包含访问令牌值、刷新令牌值和店铺编号
   *
   * @param accessToken 访问令牌不可为空
   * @return 是否有效
   */
  public static boolean isValid(DoudianAccessToken accessToken) {
    if (Objects.isNull(accessToken)) {
      throw new IllegalArgumentException("accessToken cannot be null");
    }
    return !StringUtils.isAnyBlank(accessToken.getAccessToken(), accessToken.getRefreshToken(), accessToken.getShopId());
  }
}
