package work.gaigeshen.tripartite.ding.openapi;

import work.gaigeshen.tripartite.core.header.Headers;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.core.interceptor.Interceptor;
import work.gaigeshen.tripartite.ding.openapi.accesstoken.DingAccessToken;
import work.gaigeshen.tripartite.ding.openapi.accesstoken.DingAccessTokenManager;
import work.gaigeshen.tripartite.ding.openapi.accesstoken.DingAccessTokenSource;
import work.gaigeshen.tripartite.ding.openapi.accesstoken.DingAccessTokenSourceException;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingParametersBuilder;
import work.gaigeshen.tripartite.ding.openapi.parameters.accesstoken.DingAccessTokenParameters;
import work.gaigeshen.tripartite.ding.openapi.parameters.message.DingRobotMessageParameters;
import work.gaigeshen.tripartite.ding.openapi.response.accesstoken.DingAccessTokenResponse;
import work.gaigeshen.tripartite.ding.openapi.response.message.DingRobotMessageResponse;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 此类用于实现钉钉的新的接口
 *
 * @author gaigeshen
 */
public class ModernDingClient extends AbstractDingClient implements DingRobotClient {

  private static final String ACCESS_TOKEN_GET_URI = "/v1.0/oauth2/accessToken";

  private static final String ROBOT_MESSAGE_BATCH_SEND_URI = "/v1.0/robot/oToMessages/batchSend";


  public ModernDingClient(DingConfig config, DingAccessTokenManager accessTokenManager, String serverHost) {
    super(config, accessTokenManager, serverHost);
  }

  public static ModernDingClient create(DingConfig config, DingAccessTokenManager accessTokenManager, String serverHost) {
    return new ModernDingClient(config, accessTokenManager, serverHost);
  }

  @Override
  protected List<Interceptor> getInterceptors() {
    return Collections.singletonList(new AbstractInterceptor() {
      @Override
      protected void updateRequest(Request request) throws InterceptingException {
        if (!request.url().contains(ACCESS_TOKEN_GET_URI)) {
          Headers headers = request.headers();
          headers.putValue("x-acs-dingtalk-access-token", getAccessToken().getToken());
        }
      }

      @Override
      protected void validateResponse(Request request, Response response) throws InterceptingException {
      }
    });
  }

  @Override
  protected DingAccessTokenSource getAccessTokenSource() {
    return new ModernDingAccessTokenSource();
  }

  @Override
  public DingRobotMessageResponse batchSendMessage(DingRobotMessageParameters parameters) throws DingClientException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("parameters cannot be null");
    }
    return execute(new DingParametersBuilder(parameters) {}, DingRobotMessageResponse.class, ROBOT_MESSAGE_BATCH_SEND_URI);
  }


  /**
   *
   * @author gaigeshen
   */
  private class ModernDingAccessTokenSource implements DingAccessTokenSource {

    @Override
    public DingAccessToken getAccessToken(DingConfig config) throws DingAccessTokenSourceException {

      DingAccessTokenParameters parameters = new DingAccessTokenParameters();
      parameters.appKey = config.getAppKey();
      parameters.appSecret = config.getAppSecret();

      DingAccessTokenResponse response = execute(new DingParametersBuilder(parameters) {}, DingAccessTokenResponse.class, ACCESS_TOKEN_GET_URI);

      return new DingAccessToken(response.accessToken, response.expireIn);
    }
  }
}
