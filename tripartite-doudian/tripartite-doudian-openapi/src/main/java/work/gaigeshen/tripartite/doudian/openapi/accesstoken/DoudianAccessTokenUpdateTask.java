package work.gaigeshen.tripartite.doudian.openapi.accesstoken;

/**
 * 任务类，用于更新访问令牌，每个此任务对象应该对应某个访问令牌，根据设置的店铺编号进行区分
 *
 * @author gaigeshen
 */
public interface DoudianAccessTokenUpdateTask extends Runnable {
  /**
   * 设置访问令牌存储器到此任务，此存储器用于获取访问令牌和保存更新之后的访问令牌
   *
   * @param accessTokenStore 访问令牌存储器
   */
  void setAccessTokenStore(DoudianAccessTokenStore accessTokenStore);

  /**
   * 设置访问令牌更新监听器，监听器应该不是必须设置的
   *
   * @param accessTokenUpdateListener 访问令牌更新监听器
   */
  void setAccessTokenUpdateListener(DoudianAccessTokenUpdateListener accessTokenUpdateListener);

  /**
   * 设置店铺编号到此任务，此店铺编号用于在执行更新任务的时候从存储器中查询访问令牌
   *
   * @param shopId 店铺编号
   */
  void setShopId(String shopId);

  /**
   * 返回设置的访问令牌存储器
   *
   * @return 访问令牌存储器
   */
  DoudianAccessTokenStore getAccessTokenStore();

  /**
   * 返回设置的访问令牌更新监听器
   *
   * @return 访问令牌更新监听器
   */
  DoudianAccessTokenUpdateListener getAccessTokenUpdateListener();

  /**
   * 返回设置的店铺编号
   *
   * @return 店铺编号
   */
  String getShopId();

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
