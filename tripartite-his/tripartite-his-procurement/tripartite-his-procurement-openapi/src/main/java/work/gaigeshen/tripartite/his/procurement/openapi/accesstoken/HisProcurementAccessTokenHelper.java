package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

import org.apache.commons.lang3.StringUtils;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

import java.util.Date;
import java.util.Objects;

/**
 * @author gaigeshen
 */
public class HisProcurementAccessTokenHelper {

  /**
   * 访问令牌默认的过期时间单位秒
   */
  public static final int DEFAULT_EXPIRES_IN_SECONDS = 1800;

  private HisProcurementAccessTokenHelper() { }

  /**
   * 创建新的访问令牌
   *
   * @param config 配置信息不能为空
   * @param accessToken 访问令牌内容不能为空
   * @return 新的访问令牌
   */
  public static HisProcurementAccessToken createAccessToken(HisProcurementConfig config, String accessToken) {
    if (Objects.isNull(config) || Objects.isNull(accessToken)) {
      throw new IllegalArgumentException("config and access token value cannot be null");
    }
    return HisProcurementAccessToken.builder()
            .setAccessToken(accessToken).setAccount(config.getAccount())
            .setExpiresIn(DEFAULT_EXPIRES_IN_SECONDS)
            .setExpiresTimestamp(System.currentTimeMillis() / 1000 + DEFAULT_EXPIRES_IN_SECONDS)
            .setUpdateTime(new Date()).build();
  }

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
    return accessToken.getExpiresTimestamp() <= System.currentTimeMillis() / 1000;
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
