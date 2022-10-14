package work.gaigeshen.tripartite.core.client.accesstoken;

import work.gaigeshen.tripartite.core.client.config.Config;

/**
 * 访问令牌刷新器
 *
 * @author gaigeshen
 */
public interface AccessTokenRefresher<C extends Config> {

    /**
     * 刷新访问令牌
     *
     * @param config 配置信息不能为空
     * @param oldAccessToken 旧的访问令牌不能为空
     * @return 新的访问令牌不能为空
     * @throws AccessTokenRefreshException 刷新访问令牌失败
     */
    AccessToken refresh(C config, AccessToken oldAccessToken) throws AccessTokenRefreshException;
}
