package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

/**
 * 刷新访问令牌异常
 *
 * @author gaigeshen
 */
public class HisProcurementAccessTokenRefreshException extends HisProcurementAccessTokenUpdateException {

    public HisProcurementAccessTokenRefreshException(String message) {
        super(message);
    }

    public HisProcurementAccessTokenRefreshException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HisProcurementAccessTokenRefreshException setCurrentAccessToken(HisProcurementAccessToken currentAccessToken) {
        super.setCurrentAccessToken(currentAccessToken);
        return this;
    }

    @Override
    public HisProcurementAccessTokenRefreshException setCanRetry(boolean canRetry) {
        super.setCanRetry(canRetry);
        return this;
    }
}
