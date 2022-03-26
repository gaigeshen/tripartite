package work.gaigeshen.tripartite.ding.openapi.accesstoken;

import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

/**
 * 钉钉访问令牌管理器
 *
 * @author gaigeshen
 */
public interface DingAccessTokenManager {

  /**
   * 从此管理器中获取访问令牌
   *
   * @param config 钉钉配置不能为空
   * @param source 不能为空，如果需要获取新的访问令牌则会从此接口中获取
   * @return 钉钉访问令牌
   * @throws DingAccessTokenManagerException 访问令牌管理器异常
   * @throws DingAccessTokenSourceException 获取新的访问令牌的时候发生异常
   */
  DingAccessToken getAccessToken(DingConfig config, DingAccessTokenSource source) throws DingAccessTokenManagerException, DingAccessTokenSourceException;

}
