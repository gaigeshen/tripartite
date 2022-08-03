package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

import java.util.List;
import java.util.Objects;

/**
 * 访问令牌存储器
 *
 * @author gaigeshen
 */
public interface HisProcurementAccessTokenStore {

  /**
   * 保存访问令牌，通过返回值确定本次操作的是否为新账号的访问令牌
   *
   * @param accessToken 访问令牌不能为空
   * @return 是否为新账号的访问令牌
   * @throws HisProcurementAccessTokenException 无法保存或者更新访问令牌
   */
  boolean save(HisProcurementAccessToken accessToken) throws HisProcurementAccessTokenException;

  /**
   * 删除访问令牌
   *
   * @param account 账号不能为空
   * @throws HisProcurementAccessTokenException 无法删除访问令牌
   */
  void deleteByAccount(String account) throws HisProcurementAccessTokenException;

  /**
   * 查询访问令牌
   *
   * @param account 账号不能为空
   * @return 访问令牌可能为空
   * @throws HisProcurementAccessTokenException 无法查询访问令牌
   */
  HisProcurementAccessToken findByAccount(String account) throws HisProcurementAccessTokenException;

  /**
   * 查询访问令牌
   *
   * @param account 账号不能为空
   * @param required 返回的访问令牌是否必须，如果是必须的且未查询到访问令牌则会抛出异常
   * @return 访问令牌可能为空，如果返回的令牌不是必须的话
   * @throws HisProcurementAccessTokenException 无法查询访问令牌
   * @throws HisProcurementAccessTokenNotFoundException 未查询到访问令牌，如果返回的访问令牌是必须的话
   */
  default HisProcurementAccessToken findByAccount(String account, boolean required) throws HisProcurementAccessTokenException, HisProcurementAccessTokenNotFoundException {
    HisProcurementAccessToken accessToken = findByAccount(account);
    if (required && Objects.isNull(accessToken)) {
      throw new HisProcurementAccessTokenNotFoundException("Could not find access token with account: " + account);
    }
    return accessToken;
  }

  /**
   * 查询所有的访问令牌
   *
   * @return 所有的访问令牌
   * @throws HisProcurementAccessTokenException 无法查询所有的访问令牌
   */
  List<HisProcurementAccessToken> findAll() throws HisProcurementAccessTokenException;
}
