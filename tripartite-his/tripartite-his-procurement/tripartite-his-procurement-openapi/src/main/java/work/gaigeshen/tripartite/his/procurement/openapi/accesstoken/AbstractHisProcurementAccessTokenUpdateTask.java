package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

import java.util.Objects;

/**
 * 抽象的访问令牌更新任务，子类只需关注获取新的访问令牌
 *
 * @author gaigeshen
 */
public abstract class AbstractHisProcurementAccessTokenUpdateTask implements HisProcurementAccessTokenUpdateTask {

  private HisProcurementAccessTokenStore accessTokenStore;

  private HisProcurementAccessTokenUpdateListener accessTokenUpdateListener;

  private String account;

  /**
   * 请先设置访问令牌存储器再使用此任务
   *
   * @param accessTokenStore 访问令牌存储器
   */
  @Override
  public final void setAccessTokenStore(HisProcurementAccessTokenStore accessTokenStore) {
    this.accessTokenStore = accessTokenStore;
  }

  /**
   * 监听器可以不设置
   *
   * @param accessTokenUpdateListener 访问令牌更新监听器
   */
  @Override
  public final void setAccessTokenUpdateListener(HisProcurementAccessTokenUpdateListener accessTokenUpdateListener) {
    this.accessTokenUpdateListener =  accessTokenUpdateListener;
  }

  @Override
  public final void setAccount(String account) {
    this.account = account;
  }

  @Override
  public final HisProcurementAccessTokenStore getAccessTokenStore() {
    return accessTokenStore;
  }

  @Override
  public final HisProcurementAccessTokenUpdateListener getAccessTokenUpdateListener() {
    return accessTokenUpdateListener;
  }

  @Override
  public final String getAccount() {
    return account;
  }

  @Override
  public final void executeUpdate() {
    if (Objects.isNull(accessTokenStore)) {
      throw new IllegalStateException("Could not execute update because 'accessTokenStore' not configured");
    }
    if (Objects.isNull(account)) {
      throw new IllegalStateException("Could not execute update because 'account' not configured");
    }
    HisProcurementAccessToken currentAccessToken;
    try {
      currentAccessToken = accessTokenStore.findByAccount(account);
    } catch (HisProcurementAccessTokenStoreException e) {
      handleUpdateFailed(new HisProcurementAccessTokenUpdateException("Could not find current access token for account: "  + account, e)
              .setCanRetry(true));
      return;
    }
    if (Objects.isNull(currentAccessToken)) {
      handleUpdateFailed(new HisProcurementAccessTokenUpdateException("No such current access token for account: "  + account));
      return;
    }
    HisProcurementAccessToken accessToken;
    try {
      accessToken = executeUpdate(currentAccessToken);
    } catch (HisProcurementAccessTokenUpdateException ex) {
      handleUpdateFailed(ex);
      return;
    }
    if (Objects.isNull(accessToken)) {
      return;
    }
    if (!HisProcurementAccessTokenHelper.isValid(accessToken)) {
      handleUpdateFailed(new HisProcurementAccessTokenUpdateException("Could not save invalid access token to store for account: " + account)
              .setCurrentAccessToken(currentAccessToken));
      return;
    }
    try {
      accessTokenStore.save(accessToken);
    } catch (HisProcurementAccessTokenStoreException e) {
      handleUpdateFailed(new HisProcurementAccessTokenUpdateException("Could not save access token to store for account: " + account)
              .setCurrentAccessToken(currentAccessToken)
              .setCanRetry(true));
      return;
    }
    if (Objects.nonNull(accessTokenUpdateListener)) {
      accessTokenUpdateListener.handleUpdated(currentAccessToken, accessToken);
    }
  }

  private void handleUpdateFailed(HisProcurementAccessTokenUpdateException ex) {
    if (Objects.nonNull(accessTokenUpdateListener)) {
      accessTokenUpdateListener.handleFailed(ex);
    }
  }

  /**
   * 此方法用于获取新的访问令牌，获取到的访问令牌必须有效
   *
   * @param currentAccessToken 当前的访问令牌不能为空
   * @return 返回的访问令牌必须有效且不能为空，如果为空则会导致当前的任务立即结束，且不会调用监听器的相关方法
   * @throws HisProcurementAccessTokenUpdateException 无法获取新的访问令牌
   * @see HisProcurementAccessTokenHelper#isValid(HisProcurementAccessToken)
   */
  protected abstract HisProcurementAccessToken executeUpdate(HisProcurementAccessToken currentAccessToken) throws HisProcurementAccessTokenUpdateException;
}
