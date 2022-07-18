package work.gaigeshen.tripartite.doudian.openapi.accesstoken;

/**
 * 访问令牌管理器，维护所有访问令牌的更新
 *
 * @author gaigeshen
 */
public interface DoudianAccessTokenManager {

  /**
   * 添加新的访问令牌，如果该访问令牌所属的店铺编号已经存在访问令牌，则会将此新的访问令牌替换掉旧的
   *
   * @param accessToken 待添加的访问令牌不能为空
   * @throws DoudianAccessTokenManagerException 无法添加访问令牌
   * @throws DoudianInvalidAccessTokenException 该访问令牌无效
   */
  void addNewAccessToken(DoudianAccessToken accessToken) throws DoudianAccessTokenManagerException, DoudianInvalidAccessTokenException;

  /**
   * 删除访问令牌
   *
   * @param shopId 访问令牌所属店铺编号不能为空
   * @throws DoudianAccessTokenManagerException 无法删除访问令牌
   */
  void deleteAccessToken(String shopId) throws DoudianAccessTokenManagerException;

  /**
   * 查询访问令牌
   *
   * @param shopId 访问令牌所属店铺编号不能为空
   * @return 访问令牌可能为空
   * @throws DoudianAccessTokenManagerException 无法查询访问令牌
   */
  DoudianAccessToken findAccessToken(String shopId) throws DoudianAccessTokenManagerException;

  /**
   * 关闭此访问令牌管理器，此方法被调用之后将会停止所有访问令牌的更新，但应该不影响查询或者删除访问令牌
   *
   * @throws DoudianAccessTokenManagerException 关闭的时候发生异常
   */
  void shutdown() throws DoudianAccessTokenManagerException;
}
