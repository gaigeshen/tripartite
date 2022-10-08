package work.gaigeshen.tripartite.ding.openapi.accesstoken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 访问令牌管理器实现，创建此管理器的时候，会同时为存储器中所有的访问令牌创建并调度更新任务，这些任务的执行时间早于访问令牌过期时间十分钟
 *
 * @author gaigeshen
 */
public class DefaultDingAccessTokenManager implements DingAccessTokenManager {

    private static final Logger log = LoggerFactory.getLogger(DefaultDingAccessTokenManager.class);

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    private final DingAccessTokenStore accessTokenStore;

    private final DingAccessTokenRefresher accessTokenRefresher;

    /**
     * 创建访问令牌管理器，将会从访问令牌存储器中查询所有的访问令牌，并为这些访问令牌创建并调度更新任务
     *
     * @param accessTokenStore 访问令牌存储器不能为空
     * @param accessTokenRefresher 访问令牌刷新器不能为空
     * @throws DingAccessTokenManagerException 在为访问令牌创建并调度更新任务的时候发生异常
     */
    public DefaultDingAccessTokenManager(
            DingAccessTokenStore accessTokenStore, DingAccessTokenRefresher accessTokenRefresher)
            throws DingAccessTokenManagerException {
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
    public void addNewAccessToken(DingConfig config, DingAccessToken accessToken)
            throws DingAccessTokenManagerException {
        if (Objects.isNull(config) || Objects.isNull(accessToken)) {
            throw new IllegalArgumentException("config and accessToken cannot be null");
        }
        if (!DingAccessTokenHelper.isValid(accessToken)) {
            throw new DingAccessTokenManagerException("Could not add invalid access token: " + accessToken);
        }
        try {
            if (!accessTokenStore.save(config, accessToken)) return;
        } catch (DingAccessTokenStoreException e) {
            throw new DingAccessTokenManagerException("Could not add new access token: " + accessToken, e);
        }
        try {
            createAndScheduleUpdateTask(config, accessToken);
        } catch (Exception e) {
            throw new DingAccessTokenManagerException(
                    "Could not schedule update task for new access token: " + accessToken, e);
        }
    }

    @Override
    public DingAccessToken findAccessToken(DingConfig config) throws DingAccessTokenManagerException {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        try {
            return accessTokenStore.find(config);
        } catch (DingAccessTokenStoreException e) {
            throw new DingAccessTokenManagerException("Could not find access token: " + config, e);
        }
    }

    @Override
    public synchronized void shutdown() throws DingAccessTokenManagerException {
        executorService.shutdownNow();
        try {
            if (executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                log.info("access token manager terminated");
            } else {
                log.warn("access token manager termination timeout");
            }
        } catch (InterruptedException e) {
            throw new DingAccessTokenManagerException("Current thread interrupted while shutting down", e);
        }
    }

    private void createAndScheduleUpdateTasks() throws DingAccessTokenManagerException {
        try {
            accessTokenStore.findAll().forEach(this::createAndScheduleUpdateTask);
        } catch (Exception e) {
            throw new DingAccessTokenManagerException("Could not schedule access token update tasks", e);
        }
    }

    private void createAndScheduleUpdateTask(DingConfig config, DingAccessToken accessToken) {
        long remainingDuration = DingAccessTokenHelper.getRemainingDuration(accessToken);
        createAndScheduleUpdateTask(config, remainingDuration - 600);
    }

    private void createAndScheduleUpdateTask(DingConfig config, long delaySeconds) {
        executorService.schedule(createUpdateTask(config), delaySeconds, TimeUnit.SECONDS);
    }

    private DingAccessTokenUpdateTask createUpdateTask(DingConfig config) {
        return new DingAccessTokenUpdateTaskImpl(config);
    }

    /**
     * 访问令牌更新任务实现，使用当前访问令牌管理器关联的存储器
     *
     * @author gaigeshen
     */
    private class DingAccessTokenUpdateTaskImpl extends AbstractDingAccessTokenUpdateTask {

        public DingAccessTokenUpdateTaskImpl(DingConfig config) {
            setAccessTokenStore(accessTokenStore);
            setAccessTokenUpdateListener(new DingAccessTokenUpdateListenerImpl());
            setConfig(config);
        }

        @Override
        protected DingAccessToken executeUpdate(DingAccessToken currentAccessToken)
                throws DingAccessTokenUpdateException {
            return accessTokenRefresher.refresh(getConfig(), currentAccessToken);
        }
    }

    /**
     * 访问令牌更新监听器实现，在访问令牌被更新成功之后（获取到新的访问令牌且保存到存储器中成功），将会创建并调度新的访问令牌更新任务，
     *
     * @author gaigeshen
     */
    private class DingAccessTokenUpdateListenerImpl implements DingAccessTokenUpdateListener {
        @Override
        public void handleUpdated(DingConfig config, DingAccessToken oldAccessToken, DingAccessToken newAccessToken) {
            log.info("Access token updated, old access token is {}, new access token is {}", oldAccessToken, newAccessToken);
            try {
                createAndScheduleUpdateTask(config, newAccessToken);
            } catch (Exception e) {
                log.warn("Could not schedule access token update task, current access token is " + newAccessToken, e);
            }
        }

        @Override
        public void handleFailed(DingConfig config, DingAccessTokenUpdateException ex) {
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
