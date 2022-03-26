package work.gaigeshen.tripartite.ding.openapi;

import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebException;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.interceptor.Interceptor;
import work.gaigeshen.tripartite.core.response.consumer.ResponseConsumer;
import work.gaigeshen.tripartite.core.response.converter.ResponseConverter;
import work.gaigeshen.tripartite.ding.openapi.accesstoken.DingAccessToken;
import work.gaigeshen.tripartite.ding.openapi.accesstoken.DingAccessTokenException;
import work.gaigeshen.tripartite.ding.openapi.accesstoken.DingAccessTokenManager;
import work.gaigeshen.tripartite.ding.openapi.accesstoken.DingAccessTokenSource;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingParametersBuilder;
import work.gaigeshen.tripartite.ding.openapi.response.DingResponse;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public abstract class AbstractDingClient implements DingClient {

  private final DingConfig config;

  private final DingAccessTokenManager accessTokenManager;

  private final String serverHost;

  private final WebExecutor webExecutor;

  protected AbstractDingClient(DingConfig config, DingAccessTokenManager accessTokenManager, String serverHost) {
    if (Objects.isNull(config)) {
      throw new IllegalArgumentException("config cannot be null");
    }
    if (Objects.isNull(accessTokenManager)) {
      throw new IllegalArgumentException("access token manager cannot be null");
    }
    if (Objects.isNull(serverHost)) {
      throw new IllegalArgumentException("server host cannot be null");
    }
    this.config = config;
    this.accessTokenManager = accessTokenManager;
    this.serverHost = serverHost;

    RestTemplateWebExecutor executor = RestTemplateWebExecutor.create();
    List<Interceptor> interceptors = getInterceptors();
    if (Objects.nonNull(interceptors)) {
      executor.setInterceptors(interceptors.toArray(new Interceptor[0]));
    }
    this.webExecutor = executor;
  }

  protected abstract List<Interceptor> getInterceptors();

  protected abstract DingAccessTokenSource getAccessTokenSource();

  @Override
  public final DingConfig getConfig() {
    return config;
  }

  @Override
  public final DingAccessTokenManager getAccessTokenManager() {
    return accessTokenManager;
  }

  @Override
  public final DingAccessToken getAccessToken() {
    DingAccessTokenSource accessTokenSource = getAccessTokenSource();
    if (Objects.isNull(accessTokenSource)) {
      throw new DingAccessTokenException("access token source not found: " + getConfig());
    }
    return getAccessTokenManager().getAccessToken(getConfig(), accessTokenSource);
  }

  @Override
  public final <R extends DingResponse> R execute(Class<R> responseClass, String uri, Object... uriVariables) throws DingClientException {
    if (Objects.isNull(responseClass)) {
      throw new IllegalArgumentException("response class cannot be null");
    }
    if (Objects.isNull(uri)) {
      throw new IllegalArgumentException("uri cannot be null");
    }
    try {
      return webExecutor.execute(serverHost + uri, responseClass, uriVariables);
    } catch (WebException e) {
      throw new DingClientException(e.getMessage(), e);
    }
  }

  @Override
  public final <R extends DingResponse> R execute(DingParametersBuilder builder, Class<R> responseClass, String uri, Object... uriVariables) throws DingClientException {
    if (Objects.isNull(builder)) {
      throw new IllegalArgumentException("parameters builder cannot be null");
    }
    if (Objects.isNull(responseClass)) {
      throw new IllegalArgumentException("response class cannot be null");
    }
    if (Objects.isNull(uri)) {
      throw new IllegalArgumentException("uri cannot be null");
    }
    try {
      return webExecutor.execute(serverHost + uri, builder.build(config), responseClass, uriVariables);
    } catch (WebException e) {
      throw new DingClientException(e.getMessage(), e);
    }
  }

  @Override
  public final <R> R execute(ResponseConverter<R> converter, String uri, Object... uriVariables) throws DingClientException {
    if (Objects.isNull(converter)) {
      throw new IllegalArgumentException("response converter cannot be null");
    }
    if (Objects.isNull(uri)) {
      throw new IllegalArgumentException("uri cannot be null");
    }
    try {
      return webExecutor.execute(serverHost + uri, converter, uriVariables);
    } catch (WebException e) {
      throw new DingClientException(e.getMessage(), e);
    }
  }

  @Override
  public final void execute(ResponseConsumer consumer, String uri, Object... uriVariables) throws DingClientException {
    if (Objects.isNull(consumer)) {
      throw new IllegalArgumentException("response consumer cannot be null");
    }
    if (Objects.isNull(uri)) {
      throw new IllegalArgumentException("uri cannot be null");
    }
    try {
      webExecutor.execute(serverHost + uri, consumer, uriVariables);
    } catch (WebException e) {
      throw new DingClientException(e.getMessage(), e);
    }
  }

  @Override
  public final <R> R execute(DingParametersBuilder builder, ResponseConverter<R> converter, String uri, Object... uriVariables) throws DingClientException {
    if (Objects.isNull(builder)) {
      throw new IllegalArgumentException("parameters builder cannot be null");
    }
    if (Objects.isNull(converter)) {
      throw new IllegalArgumentException("response converter cannot be null");
    }
    if (Objects.isNull(uri)) {
      throw new IllegalArgumentException("uri cannot be null");
    }
    try {
      return webExecutor.execute(serverHost + uri, builder.build(config), converter, uriVariables);
    } catch (WebException e) {
      throw new DingClientException(e.getMessage(), e);
    }
  }

  @Override
  public final void execute(DingParametersBuilder builder, ResponseConsumer consumer, String uri, Object... uriVariables) throws DingClientException {
    if (Objects.isNull(builder)) {
      throw new IllegalArgumentException("parameters builder cannot be null");
    }
    if (Objects.isNull(consumer)) {
      throw new IllegalArgumentException("response consumer cannot be null");
    }
    if (Objects.isNull(uri)) {
      throw new IllegalArgumentException("uri cannot be null");
    }
    try {
      webExecutor.execute(serverHost + uri, builder.build(config), consumer, uriVariables);
    } catch (WebException e) {
      throw new DingClientException(e.getMessage(), e);
    }
  }
}

