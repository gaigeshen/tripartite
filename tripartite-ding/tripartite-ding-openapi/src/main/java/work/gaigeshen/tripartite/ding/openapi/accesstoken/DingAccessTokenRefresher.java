package work.gaigeshen.tripartite.ding.openapi.accesstoken;

import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

/**
 * 访问令牌刷新器
 *
 * @author gaigeshen
 */
public interface DingAccessTokenRefresher {
    /**
     * 刷新访问令牌
     *
     * @param config 配置信息不能为空
     * @param oldAccessToken 旧的访问令牌不能为空
     * @return 新的访问令牌不能为空
     * @throws DingAccessTokenRefreshException 刷新访问令牌失败
     */
    default DingAccessToken refresh(DingConfig config, DingAccessToken oldAccessToken) throws DingAccessTokenRefreshException {
        throw new DingAccessTokenRefreshException("Please override this method to refresh access token")
                .setCurrentAccessToken(oldAccessToken)
                .setCanRetry(false);
    }
}
