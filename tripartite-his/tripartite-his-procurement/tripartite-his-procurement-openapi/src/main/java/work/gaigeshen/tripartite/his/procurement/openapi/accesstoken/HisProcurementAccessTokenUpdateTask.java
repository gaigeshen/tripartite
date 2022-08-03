package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

/**
 * 任务类，用于更新访问令牌，每个此任务对象应该对应某个访问令牌，根据设置的账号进行区分
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
   * 设置账号到此任务，此店铺编号用于在执行更新任务的时候从存储器中查询访问令牌
   *
   * @param account 账号
   */
  void setAccount(String account);

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
   * 返回设置的账号
   *
   * @return 账号
   */
  String getAccount();

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
