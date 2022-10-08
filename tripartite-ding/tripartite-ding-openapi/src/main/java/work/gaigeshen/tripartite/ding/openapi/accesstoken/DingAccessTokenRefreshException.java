package work.gaigeshen.tripartite.ding.openapi.accesstoken;

/**
 * 刷新访问令牌异常
 *
 * @author gaigeshen
 */
public class DingAccessTokenRefreshException extends DingAccessTokenUpdateException {

    public DingAccessTokenRefreshException(String message) {
        super(message);
    }

    public DingAccessTokenRefreshException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public DingAccessTokenRefreshException setCurrentAccessToken(DingAccessToken currentAccessToken) {
        super.setCurrentAccessToken(currentAccessToken);
        return this;
    }

    @Override
    public DingAccessTokenRefreshException setCanRetry(boolean canRetry) {
        super.setCanRetry(canRetry);
        return this;
    }
}
