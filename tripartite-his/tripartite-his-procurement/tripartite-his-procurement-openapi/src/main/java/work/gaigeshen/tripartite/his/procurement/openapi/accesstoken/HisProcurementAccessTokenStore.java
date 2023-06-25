package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

import java.util.Map;

/**
 * 访问令牌存储器
 *
 * @author gaigeshen
 */
public interface HisProcurementAccessTokenStore {

    /**
     * 保存访问令牌，通过返回值确定本次操作的是否为新配置信息的访问令牌
     *
     * @param config 配置信息不能为空
     * @param accessToken 访问令牌不能为空
     * @return 是否为新配置信息的访问令牌
     * @throws HisProcurementAccessTokenStoreException 无法保存或者更新访问令牌
     */
    boolean save(HisProcurementConfig config, HisProcurementAccessToken accessToken) throws HisProcurementAccessTokenStoreException;

    /**
     * 删除访问令牌
     *
     * @param config 配置信息不能为空
     * @throws HisProcurementAccessTokenStoreException 无法删除访问令牌
     */
    void delete(HisProcurementConfig config) throws HisProcurementAccessTokenStoreException;

    /**
     * 查询访问令牌
     *
     * @param config 配置信息不能为空
     * @return 访问令牌可能为空
     * @throws HisProcurementAccessTokenStoreException 无法查询访问令牌
     */
    HisProcurementAccessToken find(HisProcurementConfig config) throws HisProcurementAccessTokenStoreException;

    /**
     * 查询所有的访问令牌
     *
     * @return 所有的访问令牌
     * @throws HisProcurementAccessTokenStoreException 无法查询所有的访问令牌
     */
    Map<HisProcurementConfig, HisProcurementAccessToken> findAll() throws HisProcurementAccessTokenStoreException;
}
