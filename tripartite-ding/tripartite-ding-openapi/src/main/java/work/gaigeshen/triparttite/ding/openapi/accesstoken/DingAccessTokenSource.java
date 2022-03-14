package work.gaigeshen.triparttite.ding.openapi.accesstoken;

import work.gaigeshen.triparttite.ding.openapi.config.DingConfig;

/**
 * 此接口用于获取新的钉钉访问令牌
 *
 * @author gaigeshen
 */
public interface DingAccessTokenSource {

  /**
   * 获取新的钉钉访问令牌
   *
   * @param config 钉钉配置不能为空
   * @return 返回新的钉钉访问令牌
   * @throws DingAccessTokenSourceException 无法获取新的钉钉访问令牌
   */
  DingAccessToken getAccessToken(DingConfig config) throws DingAccessTokenSourceException;

}
