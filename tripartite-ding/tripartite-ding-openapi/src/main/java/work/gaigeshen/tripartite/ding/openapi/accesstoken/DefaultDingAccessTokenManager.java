package work.gaigeshen.tripartite.ding.openapi.accesstoken;

import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认的钉钉访问令牌管理器
 *
 * @author gaigeshen
 */
public class DefaultDingAccessTokenManager implements DingAccessTokenManager {

  private static final Logger log = LoggerFactory.getLogger(DefaultDingAccessTokenManager.class);

  public static final DefaultDingAccessTokenManager INSTANCE = new DefaultDingAccessTokenManager();

  private final Map<DingConfig, DingAccessToken> accessTokens = new ConcurrentHashMap<>();

  @Override
  public DingAccessToken getAccessToken(DingConfig config, DingAccessTokenSource source) throws DingAccessTokenManagerException, DingAccessTokenSourceException {
    if (Objects.isNull(config)) {
      throw new IllegalArgumentException("config cannot be null");
    }
    if (Objects.isNull(source)) {
      throw new IllegalArgumentException("access token source cannot be null");
    }
    DingAccessToken accessToken = accessTokens.computeIfAbsent(config, source::getAccessToken);
    if (accessToken.isExpired()) {
      DingAccessToken newAccessToken = source.getAccessToken(config);
      if (newAccessToken.isExpired()) {
        throw new DingAccessTokenManagerException("new access token is expired from source: " + config + ", " + newAccessToken);
      }
      log.info("get new access token [{}] from source of config [{}]", newAccessToken, config);
      accessTokens.put(config, accessToken);
      return newAccessToken;
    }
    return accessToken;
  }
}
