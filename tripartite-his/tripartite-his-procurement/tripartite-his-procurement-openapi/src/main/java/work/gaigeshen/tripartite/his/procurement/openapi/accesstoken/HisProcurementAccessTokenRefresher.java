package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

/**
 * 访问令牌刷新器
 *
 * @author gaigeshen
 */
public interface HisProcurementAccessTokenRefresher {
  /**
   * 刷新访问令牌
   *
   * @param oldAccessToken 旧的访问令牌不能为空
   * @return 新的访问令牌不能为空
   * @throws HisProcurementAccessTokenRefreshException 刷新访问令牌失败
   */
  default HisProcurementAccessToken refresh(HisProcurementAccessToken oldAccessToken)
          throws HisProcurementAccessTokenRefreshException {
    throw new HisProcurementAccessTokenRefreshException("Please override this method to refresh access token")
            .setCurrentAccessToken(oldAccessToken)
            .setCanRetry(false);
  }
}
