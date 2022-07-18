package work.gaigeshen.tripartite.doudian.openapi.accesstoken;

/**
 * 访问令牌刷新器
 *
 * @author gaigeshen
 */
public interface DoudianAccessTokenRefresher {
  /**
   * 刷新访问令牌
   *
   * @param oldAccessToken 旧的访问令牌不能为空
   * @return 新的访问令牌不能为空
   * @throws DoudianAccessTokenRefreshException 刷新访问令牌失败
   */
  default DoudianAccessToken refresh(DoudianAccessToken oldAccessToken) throws DoudianAccessTokenRefreshException {
    throw new DoudianAccessTokenRefreshException("Please override this method to refresh access token")
            .setCurrentAccessToken(oldAccessToken)
            .setCanRetry(false);
  }
}
