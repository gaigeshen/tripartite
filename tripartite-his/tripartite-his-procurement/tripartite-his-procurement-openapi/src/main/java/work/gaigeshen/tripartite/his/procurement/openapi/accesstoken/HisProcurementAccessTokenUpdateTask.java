package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

/**
 * 任务类，用于更新访问令牌，每个此任务对象应该对应某个访问令牌，根据设置的配置信息进行区分
 *
 * @author gaigeshen
 */
public interface HisProcurementAccessTokenUpdateTask extends Runnable {
  /**
   * 设置访问令牌存储器到此任务，此存储器用于获取访问令牌和保存更新之后的访问令牌
   *
   * @param accessTokenStore 访问令牌存储器
   */
  void setAccessTokenStore(HisProcurementAccessTokenStore accessTokenStore);

  /**
   * 设置访问令牌更新监听器，监听器应该不是必须设置的
   *
   * @param accessTokenUpdateListener 访问令牌更新监听器
   */
  void setAccessTokenUpdateListener(HisProcurementAccessTokenUpdateListener accessTokenUpdateListener);

  /**
   * 设置配置信息到此任务，此配置信息用于在执行更新任务的时候从存储器中查询访问令牌
   *
   * @param config 配置信息
   */
  void setConfig(HisProcurementConfig config);

  /**
   * 返回设置的访问令牌存储器
   *
   * @return 访问令牌存储器
   */
  HisProcurementAccessTokenStore getAccessTokenStore();

  /**
   * 返回设置的访问令牌更新监听器
   *
   * @return 访问令牌更新监听器
   */
  HisProcurementAccessTokenUpdateListener getAccessTokenUpdateListener();

  /**
   * 返回设置的配置信息
   *
   * @return 设置的配置信息
   */
  HisProcurementConfig getConfig();

  /**
   * 执行具体的更新任务，注意此方法不抛出任何异常，如任务执行失败，请使用监听器传达
   */
  void executeUpdate();

  /**
   * 不需要重新实现此方法，内部直接调用执行具体的更新任务方法
   */
  @Override
  default void run() {
    executeUpdate();
  }
}
