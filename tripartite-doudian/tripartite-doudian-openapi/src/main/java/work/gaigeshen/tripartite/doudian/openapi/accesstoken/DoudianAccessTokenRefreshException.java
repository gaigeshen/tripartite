package work.gaigeshen.tripartite.doudian.openapi.accesstoken;

/**
 * 刷新访问令牌异常
 *
 * @author gaigeshen
 */
public class DoudianAccessTokenRefreshException extends DoudianAccessTokenUpdateException {
  public DoudianAccessTokenRefreshException(String message) {
    super(message);
  }

  public DoudianAccessTokenRefreshException(String message, Throwable cause) {
    super(message, cause);
  }

  @Override
  public DoudianAccessTokenRefreshException setCurrentAccessToken(DoudianAccessToken currentAccessToken) {
    super.setCurrentAccessToken(currentAccessToken);
    return this;
  }

  @Override
  public DoudianAccessTokenRefreshException setCanRetry(boolean canRetry) {
    super.setCanRetry(canRetry);
    return this;
  }
}
