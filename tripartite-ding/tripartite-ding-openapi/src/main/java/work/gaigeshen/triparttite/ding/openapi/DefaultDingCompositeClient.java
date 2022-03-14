package work.gaigeshen.triparttite.ding.openapi;

import work.gaigeshen.triparttite.ding.openapi.accesstoken.DefaultDingAccessTokenManager;
import work.gaigeshen.triparttite.ding.openapi.accesstoken.DingAccessTokenManager;
import work.gaigeshen.triparttite.ding.openapi.config.DingConfig;
import work.gaigeshen.triparttite.ding.openapi.parameters.message.DingRobotMessageParameters;
import work.gaigeshen.triparttite.ding.openapi.parameters.process.DingProcessInstanceCreateParameters;
import work.gaigeshen.triparttite.ding.openapi.parameters.process.DingProcessInstanceGetParameters;
import work.gaigeshen.triparttite.ding.openapi.parameters.process.DingProcessInstanceTerminateParameters;
import work.gaigeshen.triparttite.ding.openapi.response.message.DingRobotMessageResponse;
import work.gaigeshen.triparttite.ding.openapi.response.process.DingProcessInstanceCreateResponse;
import work.gaigeshen.triparttite.ding.openapi.response.process.DingProcessInstanceGetResponse;
import work.gaigeshen.triparttite.ding.openapi.response.process.DingProcessInstanceTerminateResponse;

import java.util.Objects;

/**
 *
 *
 * @author gaigeshen
 */
public class DefaultDingCompositeClient implements DingCompositeClient {

  private final DingConfig config;

  private final LegacyDingClient legacyDingClient;

  private final ModernDingClient modernDingClient;

  public DefaultDingCompositeClient(DingConfig config, DingAccessTokenManager accessTokenManager, String legacyServerHost, String modernServerHost) {
    if (Objects.isNull(config)) {
      throw new IllegalArgumentException("config cannot be null");
    }
    if (Objects.isNull(accessTokenManager)) {
      throw new IllegalArgumentException("access token manager cannot be null");
    }
    if (Objects.isNull(legacyServerHost) || Objects.isNull(modernServerHost)) {
      throw new IllegalArgumentException("server host cannot be null");
    }
    this.config = config;
    this.legacyDingClient = LegacyDingClient.create(config, accessTokenManager, legacyServerHost);
    this.modernDingClient = ModernDingClient.create(config, accessTokenManager, modernServerHost);
  }

  public DefaultDingCompositeClient(DingConfig config, String legacyServerHost, String modernServerHost) {
    this(config, DefaultDingAccessTokenManager.INSTANCE, legacyServerHost, modernServerHost);
  }

  @Override
  public DingProcessInstanceCreateResponse createProcessInstance(DingProcessInstanceCreateParameters parameters) throws DingClientException {
    return legacyDingClient.createProcessInstance(parameters);
  }

  @Override
  public DingProcessInstanceGetResponse getProcessInstance(DingProcessInstanceGetParameters parameters) throws DingClientException {
    return legacyDingClient.getProcessInstance(parameters);
  }

  @Override
  public DingProcessInstanceTerminateResponse terminateProcessInstance(DingProcessInstanceTerminateParameters parameters) throws DingClientException {
    return legacyDingClient.terminateProcessInstance(parameters);
  }

  @Override
  public DingRobotMessageResponse batchSendMessage(DingRobotMessageParameters parameters) throws DingClientException {
    return modernDingClient.batchSendMessage(parameters);
  }

  @Override
  public DingConfig getConfig() {
    return config;
  }
}
