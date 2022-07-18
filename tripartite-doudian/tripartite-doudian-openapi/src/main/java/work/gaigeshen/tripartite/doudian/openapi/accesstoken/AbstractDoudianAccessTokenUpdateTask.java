package work.gaigeshen.tripartite.doudian.openapi.accesstoken;

import java.util.Objects;

/**
 * 抽象的访问令牌更新任务，子类只需关注获取新的访问令牌
 *
 * @author gaigeshen
 */
public abstract class AbstractDoudianAccessTokenUpdateTask implements DoudianAccessTokenUpdateTask {

  private DoudianAccessTokenStore accessTokenStore;

  private DoudianAccessTokenUpdateListener accessTokenUpdateListener;

  private String shopId;

  /**
   * 请先设置访问令牌存储器再使用此任务
   *
   * @param accessTokenStore 访问令牌存储器
   */
  @Override
  public final void setAccessTokenStore(DoudianAccessTokenStore accessTokenStore) {
    this.accessTokenStore = accessTokenStore;
  }

  /**
   * 监听器可以不设置
   *
   * @param accessTokenUpdateListener 访问令牌更新监听器
   */
  @Override
  public final void setAccessTokenUpdateListener(DoudianAccessTokenUpdateListener accessTokenUpdateListener) {
    this.accessTokenUpdateListener =  accessTokenUpdateListener;
  }

  /**
   * 请先设置店铺编号再使用此任务
   *
   * @param shopId 店铺编号
   */
  @Override
  public final void setShopId(String shopId) {
    this.shopId = shopId;
  }

  @Override
  public final DoudianAccessTokenStore getAccessTokenStore() {
    return accessTokenStore;
  }

  @Override
  public final DoudianAccessTokenUpdateListener getAccessTokenUpdateListener() {
    return accessTokenUpdateListener;
  }

  @Override
  public final String getShopId() {
    return shopId;
  }

  @Override
  public final void executeUpdate() {
    if (Objects.isNull(accessTokenStore)) {
      throw new IllegalStateException("Could not execute update because 'accessTokenStore' not configured");
    }
    if (Objects.isNull(shopId)) {
      throw new IllegalStateException("Could not execute update because 'shopId' not configured");
    }
    DoudianAccessToken currentAccessToken;
    try {
      currentAccessToken = accessTokenStore.findByShopId(shopId);
    } catch (DoudianAccessTokenStoreException e) {
      handleUpdateFailed(new DoudianAccessTokenUpdateException("Could not find current access token for shop: "  + shopId, e)
              .setCanRetry(true));
      return;
    }
    if (Objects.isNull(currentAccessToken)) {
      handleUpdateFailed(new DoudianAccessTokenUpdateException("No such current access token for shop: "  + shopId));
      return;
    }
    DoudianAccessToken accessToken;
    try {
      accessToken = executeUpdate(currentAccessToken);
    } catch (DoudianAccessTokenUpdateException ex) {
      handleUpdateFailed(ex);
      return;
    }
    if (Objects.isNull(accessToken)) {
      return;
    }
    if (!DoudianAccessTokenHelper.isValid(accessToken)) {
      handleUpdateFailed(new DoudianAccessTokenUpdateException("Could not save invalid access token to store for shop: " + shopId)
              .setCurrentAccessToken(currentAccessToken));
      return;
    }
    try {
      accessTokenStore.save(accessToken);
    } catch (DoudianAccessTokenStoreException e) {
      handleUpdateFailed(new DoudianAccessTokenUpdateException("Could not save access token to store for shop: " + shopId)
              .setCurrentAccessToken(currentAccessToken)
              .setCanRetry(true));
      return;
    }
    if (Objects.nonNull(accessTokenUpdateListener)) {
      accessTokenUpdateListener.handleUpdated(currentAccessToken, accessToken);
    }
  }

  private void handleUpdateFailed(DoudianAccessTokenUpdateException ex) {
    if (Objects.nonNull(accessTokenUpdateListener)) {
      accessTokenUpdateListener.handleFailed(ex);
    }
  }

  /**
   * 此方法用于获取新的访问令牌，获取到的访问令牌必须有效
   *
   * @param currentAccessToken 当前的访问令牌不能为空
   * @return 返回的访问令牌必须有效且不能为空，如果为空则会导致当前的任务立即结束，且不会调用监听器的相关方法
   * @throws DoudianAccessTokenUpdateException 无法获取新的访问令牌
   * @see DoudianAccessTokenHelper#isValid(DoudianAccessToken)
   */
  protected abstract DoudianAccessToken executeUpdate(DoudianAccessToken currentAccessToken) throws DoudianAccessTokenUpdateException;
}
