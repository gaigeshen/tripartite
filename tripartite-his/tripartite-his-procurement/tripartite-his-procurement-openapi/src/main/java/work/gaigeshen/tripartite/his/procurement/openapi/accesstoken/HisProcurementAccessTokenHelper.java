package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author gaigeshen
 */
public class HisProcurementAccessTokenHelper {

  private HisProcurementAccessTokenHelper() { }

  /**
   * 返回该访问令牌是否已经过期
   *
   * @param accessToken 访问令牌不可为空
   * @return 是否已经过期
   */
  public static boolean isExpired(HisProcurementAccessToken accessToken) {
    if (Objects.isNull(accessToken)) {
      throw new IllegalArgumentException("accessToken cannot be null");
    }
    return accessToken.getExpiresTimestamp() > System.currentTimeMillis() / 1000;
  }

  /**
   * 返回访问令牌是否有效，有效的访问令牌必需包含访问令牌值
   *
   * @param accessToken 访问令牌不可为空
   * @return 是否有效
   */
  public static boolean isValid(HisProcurementAccessToken accessToken) {
    if (Objects.isNull(accessToken)) {
      throw new IllegalArgumentException("accessToken cannot be null");
    }
    return !StringUtils.isAnyBlank(accessToken.getAccessToken(), accessToken.getAccount());
  }
}
