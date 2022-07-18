package work.gaigeshen.tripartite.doudian.openapi.accesstoken;

import java.util.Objects;

/**
 * 访问令牌更新异常
 *
 * @author gaigeshen
 */
public class DoudianAccessTokenUpdateException extends DoudianAccessTokenException {

  private DoudianAccessToken currentAccessToken;

  private boolean canRetry = false;

  public DoudianAccessTokenUpdateException(String message) {
    super(message);
  }

  public DoudianAccessTokenUpdateException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * 设置当前的访问令牌，可以视同为更新失败的时候当前的访问令牌
   *
   * @param currentAccessToken 当前的访问令牌
   * @return 返回当前的异常
   */
  public DoudianAccessTokenUpdateException setCurrentAccessToken(DoudianAccessToken currentAccessToken) {
    this.currentAccessToken = currentAccessToken;
    return this;
  }

  /**
   * 设置是否能够重试
   *
   * @param canRetry 是否能够重试
   * @return 返回当前的异常
   */
  public DoudianAccessTokenUpdateException setCanRetry(boolean canRetry) {
    this.canRetry = canRetry;
    return this;
  }

  /**
   * 返回当前的访问令牌
   *
   * @return 当前的访问令牌
   */
  public DoudianAccessToken getCurrentAccessToken() {
    return currentAccessToken;
  }

  /**
   * 返回是否存在当前的访问令牌
   *
   * @return 是否存在当前的访问令牌
   */
  public boolean hasCurrentAccessToken() {
    return Objects.nonNull(currentAccessToken);
  }

  /**
   * 返回是否能够重试
   *
   * @return 是否能够重试
   */
  public boolean isCanRetry() {
    return canRetry;
  }
}
