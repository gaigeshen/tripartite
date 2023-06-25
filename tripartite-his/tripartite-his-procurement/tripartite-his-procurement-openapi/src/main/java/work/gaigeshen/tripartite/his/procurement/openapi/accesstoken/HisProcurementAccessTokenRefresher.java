package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

/**
 * 访问令牌刷新器
 *
 * @author gaigeshen
 */
public interface HisProcurementAccessTokenRefresher {
    /**
     * 刷新访问令牌
     *
     * @param config 配置信息不能为空
     * @param oldAccessToken 旧的访问令牌不能为空
     * @return 新的访问令牌不能为空
     * @throws HisProcurementAccessTokenRefreshException 刷新访问令牌失败
     */
    default HisProcurementAccessToken refresh(HisProcurementConfig config, HisProcurementAccessToken oldAccessToken)
            throws HisProcurementAccessTokenRefreshException {
        throw new HisProcurementAccessTokenRefreshException("Please override this method to refresh access token")
                .setCurrentAccessToken(oldAccessToken)
                .setCanRetry(false);
    }
}
