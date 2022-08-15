package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 访问令牌管理器实现，创建此管理器的时候，会同时为存储器中所有的访问令牌创建并调度更新任务，这些任务的执行时间早于访问令牌过期时间十分钟
 *
 * @author gaigeshen
 */
public class DefaultHisProcurementAccessTokenManager implements HisProcurementAccessTokenManager {

  private static final Logger log = LoggerFactory.getLogger(DefaultHisProcurementAccessTokenManager.class);

  private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

  private final HisProcurementAccessTokenStore accessTokenStore;

  private final HisProcurementAccessTokenRefresher accessTokenRefresher;

  /**
   * 创建访问令牌管理器，将会从访问令牌存储器中查询所有的访问令牌，并为这些访问令牌创建并调度更新任务
   *
   * @param accessTokenStore 访问令牌存储器不能为空
   * @param accessTokenRefresher 访问令牌刷新器不能为空
   * @throws HisProcurementAccessTokenManagerException 在为访问令牌创建并调度更新任务的时候发生异常
   */
  public DefaultHisProcurementAccessTokenManager(
          HisProcurementAccessTokenStore accessTokenStore, HisProcurementAccessTokenRefresher accessTokenRefresher)
          throws HisProcurementAccessTokenManagerException {
    if (Objects.isNull(accessTokenStore)) {
      throw new IllegalArgumentException("accessTokenStore cannot be null");
    }
    if (Objects.isNull(accessTokenRefresher)) {
      throw new IllegalArgumentException("accessTokenRefresher cannot be null");
    }
    this.accessTokenStore = accessTokenStore;
    this.accessTokenRefresher = accessTokenRefresher;
    createAndScheduleUpdateTasks();
  }

  @Override
  public void addNewAccessToken(HisProcurementConfig config, HisProcurementAccessToken accessToken)
          throws HisProcurementAccessTokenManagerException {
    if (Objects.isNull(config) || Objects.isNull(accessToken)) {
      throw new IllegalArgumentException("config and accessToken cannot be null");
    }
    if (!HisProcurementAccessTokenHelper.isValid(accessToken)) {
      throw new HisProcurementAccessTokenManagerException("Could not add invalid access token: " + accessToken);
    }
    try {
      if (!accessTokenStore.save(config, accessToken)) return;
    } catch (HisProcurementAccessTokenStoreException e) {
      throw new HisProcurementAccessTokenManagerException("Could not add new access token: " + accessToken, e);
    }
    try {
      createAndScheduleUpdateTask(config, accessToken);
    } catch (Exception e) {
      throw new HisProcurementAccessTokenManagerException(
              "Could not schedule update task for new access token: " + accessToken, e);
    }
  }

  @Override
  public HisProcurementAccessToken findAccessToken(HisProcurementConfig config) throws HisProcurementAccessTokenManagerException {
    if (Objects.isNull(config)) {
      throw new IllegalArgumentException("config cannot be null");
    }
    try {
      return accessTokenStore.find(config);
    } catch (HisProcurementAccessTokenStoreException e) {
      throw new HisProcurementAccessTokenManagerException("Could not find access token: " + config, e);
    }
  }

  @Override
  public synchronized void shutdown() throws HisProcurementAccessTokenManagerException {
    executorService.shutdownNow();
    try {
      if (executorService.awaitTermination(10, TimeUnit.SECONDS)) {
        log.info("access token manager terminated");
      } else {
        log.warn("access token manager termination timeout");
      }
    } catch (InterruptedException e) {
      throw new HisProcurementAccessTokenManagerException("Current thread interrupted while shutting down", e);
    }
  }

  private void createAndScheduleUpdateTasks() throws HisProcurementAccessTokenManagerException {
    try {
      accessTokenStore.findAll().forEach(this::createAndScheduleUpdateTask);
    } catch (Exception e) {
      throw new HisProcurementAccessTokenManagerException("Could not schedule access token update tasks", e);
    }
  }

  private void createAndScheduleUpdateTask(HisProcurementConfig config, HisProcurementAccessToken accessToken) {
    long executionTimestamp = accessToken.getExpiresTimestamp() - 600;
    createAndScheduleUpdateTask(config, executionTimestamp - System.currentTimeMillis() / 1000);
  }

  private void createAndScheduleUpdateTask(HisProcurementConfig config, long delaySeconds) {
    executorService.schedule(createUpdateTask(config), delaySeconds, TimeUnit.SECONDS);
  }

  private HisProcurementAccessTokenUpdateTask createUpdateTask(HisProcurementConfig config) {
    return new HisProcurementAccessTokenUpdateTaskImpl(config);
  }

  /**
   * 访问令牌更新任务实现，使用当前访问令牌管理器关联的存储器
   *
   * @author gaigeshen
   */
  private class HisProcurementAccessTokenUpdateTaskImpl extends AbstractHisProcurementAccessTokenUpdateTask {

    public HisProcurementAccessTokenUpdateTaskImpl(HisProcurementConfig config) {
      setAccessTokenStore(accessTokenStore);
      setAccessTokenUpdateListener(new HisProcurementAccessTokenUpdateListenerImpl());
      setConfig(config);
    }

    @Override
    protected HisProcurementAccessToken executeUpdate(HisProcurementAccessToken currentAccessToken)
            throws HisProcurementAccessTokenUpdateException {
      return accessTokenRefresher.refresh(getConfig(), currentAccessToken);
    }
  }

  /**
   * 访问令牌更新监听器实现，在访问令牌被更新成功之后（获取到新的访问令牌且保存到存储器中成功），将会创建并调度新的访问令牌更新任务，
   *
   * @author gaigeshen
   */
  private class HisProcurementAccessTokenUpdateListenerImpl implements HisProcurementAccessTokenUpdateListener {
    @Override
    public void handleUpdated(HisProcurementConfig config, HisProcurementAccessToken oldAccessToken, HisProcurementAccessToken newAccessToken) {
      log.info("Access token updated, old access token is {}, new access token is {}", oldAccessToken, newAccessToken);
      try {
        createAndScheduleUpdateTask(config, newAccessToken);
      } catch (Exception e) {
        log.warn("Could not schedule access token update task, current access token is " + newAccessToken, e);
      }
    }
    @Override
    public void handleFailed(HisProcurementConfig config, HisProcurementAccessTokenUpdateException ex) {
      log.warn("Access token update failed"
              + (ex.isCanRetry() && ex.hasCurrentAccessToken() ? ", retry again 10 seconds later" : "")
              + (ex.hasCurrentAccessToken() ? ", current access token is " + ex.getCurrentAccessToken() : ""), ex);
      if (ex.isCanRetry() && ex.hasCurrentAccessToken()) {
        try {
          createAndScheduleUpdateTask(config, 10);
        } catch (Exception e) {
          log.warn("Could not reschedule update task, current access token is " + ex.getCurrentAccessToken(), e);
        }
      }
    }
  }
}
