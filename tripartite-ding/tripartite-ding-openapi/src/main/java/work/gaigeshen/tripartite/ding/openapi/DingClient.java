package work.gaigeshen.tripartite.ding.openapi;

import work.gaigeshen.tripartite.core.response.consumer.ResponseConsumer;
import work.gaigeshen.tripartite.core.response.converter.ResponseConverter;
import work.gaigeshen.tripartite.ding.openapi.accesstoken.DingAccessToken;
import work.gaigeshen.tripartite.ding.openapi.accesstoken.DingAccessTokenManager;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingParametersBuilder;
import work.gaigeshen.tripartite.ding.openapi.response.DingResponse;

/**
 *
 * @author gaigeshen
 */
public interface DingClient {

  DingConfig getConfig();

  DingAccessTokenManager getAccessTokenManager();

  DingAccessToken getAccessToken();

  <R extends DingResponse> R execute(Class<R> responseClass, String uri, Object... uriVariables) throws DingClientException;

  <R extends DingResponse> R execute(DingParametersBuilder builder, Class<R> responseClass, String uri, Object... uriVariables) throws DingClientException;

  <R> R execute(ResponseConverter<R> converter, String uri, Object... uriVariables) throws DingClientException;

  void execute(ResponseConsumer consumer, String uri, Object... uriVariables) throws DingClientException;

  <R> R execute(DingParametersBuilder builder, ResponseConverter<R> converter, String uri, Object... uriVariables) throws DingClientException;

  void execute(DingParametersBuilder builder, ResponseConsumer consumer, String uri, Object... uriVariables) throws DingClientException;
}
