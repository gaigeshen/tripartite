package work.gaigeshen.triparttite.ding.openapi;

import work.gaigeshen.triparttite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.triparttite.core.interceptor.InterceptingException;
import work.gaigeshen.triparttite.core.interceptor.Interceptor;
import work.gaigeshen.triparttite.ding.openapi.accesstoken.DingAccessToken;
import work.gaigeshen.triparttite.ding.openapi.accesstoken.DingAccessTokenManager;
import work.gaigeshen.triparttite.ding.openapi.accesstoken.DingAccessTokenSource;
import work.gaigeshen.triparttite.ding.openapi.accesstoken.DingAccessTokenSourceException;
import work.gaigeshen.triparttite.ding.openapi.config.DingConfig;
import work.gaigeshen.triparttite.ding.openapi.parameters.DingParametersBuilder;
import work.gaigeshen.triparttite.ding.openapi.parameters.process.DingProcessInstanceCreateParameters;
import work.gaigeshen.triparttite.ding.openapi.parameters.process.DingProcessInstanceGetParameters;
import work.gaigeshen.triparttite.ding.openapi.parameters.process.DingProcessInstanceTerminateParameters;
import work.gaigeshen.triparttite.ding.openapi.response.DingResponse;
import work.gaigeshen.triparttite.ding.openapi.response.process.DingProcessInstanceCreateResponse;
import work.gaigeshen.triparttite.ding.openapi.response.process.DingProcessInstanceGetResponse;
import work.gaigeshen.triparttite.ding.openapi.response.process.DingProcessInstanceTerminateResponse;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 此类用于实现所有的钉钉遗留旧的接口
 *
 * @author gaigeshen
 */
public class LegacyDingClient extends AbstractDingClient implements DingProcessClient {

  private static final String ACCESS_TOKEN_GET_URI = "/gettoken?appkey={appkey}&appsecret={appsecret}";

  private static final String PROCESS_CREATE_INSTANCE_URI = "/topapi/processinstance/create?access_token={access_token}";

  private static final String PROCESS_GET_INSTANCE_URI = "/topapi/processinstance/get?access_token={access_token}";

  private static final String PROCESS_TERMINATE_INSTANCE_URI = "/topapi/process/instance/terminate?access_token={access_token}";


  public LegacyDingClient(DingConfig config, DingAccessTokenManager accessTokenManager, String serverHost) {
    super(config, accessTokenManager, serverHost);
  }

  public static LegacyDingClient create(DingConfig config, DingAccessTokenManager accessTokenManager, String serverHost) {
    return new LegacyDingClient(config, accessTokenManager, serverHost);
  }

  @Override
  protected List<Interceptor> getInterceptors() {
    return Collections.singletonList(new AbstractInterceptor() {
      @Override
      protected void updateRequest(Request request) throws InterceptingException {}
      @Override
      protected void validateResponse(Request request, Response response) throws InterceptingException {}
    });
  }

  @Override
  protected DingAccessTokenSource getAccessTokenSource() {
    return new LegacyDingAccessTokenSource();
  }

  @Override
  public DingProcessInstanceCreateResponse createProcessInstance(DingProcessInstanceCreateParameters parameters) throws DingClientException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("parameters cannot be null");
    }
    return execute(new DingParametersBuilder(parameters) {}, DingProcessInstanceCreateResponse.class, PROCESS_CREATE_INSTANCE_URI, getAccessToken().getToken());
  }

  @Override
  public DingProcessInstanceGetResponse getProcessInstance(DingProcessInstanceGetParameters parameters) throws DingClientException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("parameters cannot be null");
    }
    return execute(new DingParametersBuilder(parameters) {}, DingProcessInstanceGetResponse.class, PROCESS_GET_INSTANCE_URI, getAccessToken().getToken());
  }

  @Override
  public DingProcessInstanceTerminateResponse terminateProcessInstance(DingProcessInstanceTerminateParameters parameters) throws DingClientException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("parameters cannot be null");
    }
    return execute(new DingParametersBuilder(parameters) {}, DingProcessInstanceTerminateResponse.class, PROCESS_TERMINATE_INSTANCE_URI, getAccessToken().getToken());
  }


  /**
   *
   * @author gaigeshen
   */
  private class LegacyDingAccessTokenSource implements DingAccessTokenSource {

    @Override
    public DingAccessToken getAccessToken(DingConfig config) throws DingAccessTokenSourceException {

      LegacyDingAccessTokenResponse response = execute(LegacyDingAccessTokenResponse.class, ACCESS_TOKEN_GET_URI, config.getAppKey(), config.getAppSecret());

      return new DingAccessToken(response.access_token, response.expires_in);
    }
  }

  /**
   *
   * @author gaigeshen
   */
  public static class LegacyDingAccessTokenResponse implements DingResponse {

    public String access_token;

    public Long expires_in;
  }
}
