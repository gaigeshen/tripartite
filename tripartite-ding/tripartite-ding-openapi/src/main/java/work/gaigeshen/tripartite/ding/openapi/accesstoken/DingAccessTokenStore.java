package work.gaigeshen.tripartite.ding.openapi.accesstoken;

import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.Map;

/**
 * 访问令牌存储器
 *
 * @author gaigeshen
 */
public interface DingAccessTokenStore {

    /**
     * 保存访问令牌，通过返回值确定本次操作的是否为新配置信息的访问令牌
     *
     * @param config 配置信息不能为空
     * @param accessToken 访问令牌不能为空
     * @return 是否为新配置信息的访问令牌
     * @throws DingAccessTokenStoreException 无法保存或者更新访问令牌
     */
    boolean save(DingConfig config, DingAccessToken accessToken) throws DingAccessTokenStoreException;

    /**
     * 删除访问令牌
     *
     * @param config 配置信息不能为空
     * @throws DingAccessTokenStoreException 无法删除访问令牌
     */
    void delete(DingConfig config) throws DingAccessTokenStoreException;

    /**
     * 查询访问令牌
     *
     * @param config 配置信息不能为空
     * @return 访问令牌可能为空
     * @throws DingAccessTokenStoreException 无法查询访问令牌
     */
    DingAccessToken find(DingConfig config) throws DingAccessTokenStoreException;

    /**
     * 查询所有的访问令牌
     *
     * @return 所有的访问令牌
     * @throws DingAccessTokenStoreException 无法查询所有的访问令牌
     */
    Map<DingConfig, DingAccessToken> findAll() throws DingAccessTokenStoreException;
}
