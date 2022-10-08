package work.gaigeshen.tripartite.ding.openapi.accesstoken;

import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

/**
 * 访问令牌更新监听器
 *
 * @author gaigeshen
 */
public interface DingAccessTokenUpdateListener {

    /**
     * 在访问令牌更新成功之后被执行，默认不做任何事情，此方法不要抛出任何异常
     *
     * @param config 配置信息不能为空
     * @param oldAccessToken 旧的访问令牌不能为空
     * @param newAccessToken 新的访问令牌不能为空，且必须有效
     */
    default void handleUpdated(DingConfig config, DingAccessToken oldAccessToken, DingAccessToken newAccessToken) {
    }

    /**
     * 在访问令牌更新失败之后被执行，默认不做任何事情，此方法不要抛出任何异常
     *
     * @param config 配置信息不能为空
     * @param exception 异常不能为空
     */
    default void handleFailed(DingConfig config, DingAccessTokenUpdateException exception) {
    }

}
