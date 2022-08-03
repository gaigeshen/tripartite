package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

/**
 * 访问令牌管理器，维护所有访问令牌的更新
 *
 * @author gaigeshen
 */
public interface HisProcurementAccessTokenManager {

  /**
   * 添加新的访问令牌，如果该访问令牌所属的店铺编号已经存在访问令牌，则会将此新的访问令牌替换掉旧的
   *
   * @param accessToken 待添加的访问令牌不能为空
   * @throws HisProcurementAccessTokenManagerException 无法添加访问令牌
   * @throws HisProcurementInvalidAccessTokenException 该访问令牌无效
   */
  void addNewAccessToken(HisProcurementAccessToken accessToken) throws HisProcurementAccessTokenManagerException, HisProcurementInvalidAccessTokenException;

  /**
   * 删除访问令牌
   *
   * @param account 访问令牌所属账号不能为空
   * @throws HisProcurementAccessTokenManagerException 无法删除访问令牌
   */
  void deleteAccessToken(String account) throws HisProcurementAccessTokenManagerException;

  /**
   * 查询访问令牌
   *
   * @param account 访问令牌所属账号不能为空
   * @return 访问令牌可能为空
   * @throws HisProcurementAccessTokenManagerException 无法查询访问令牌
   */
  HisProcurementAccessToken findAccessToken(String account) throws HisProcurementAccessTokenManagerException;

  /**
   * 关闭此访问令牌管理器，此方法被调用之后将会停止所有访问令牌的更新，但应该不影响查询或者删除访问令牌
   *
   * @throws HisProcurementAccessTokenManagerException 关闭的时候发生异常
   */
  void shutdown() throws HisProcurementAccessTokenManagerException;

}
