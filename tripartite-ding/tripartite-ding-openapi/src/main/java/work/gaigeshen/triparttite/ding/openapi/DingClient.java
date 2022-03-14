package work.gaigeshen.triparttite.ding.openapi;

import work.gaigeshen.triparttite.core.response.consumer.ResponseConsumer;
import work.gaigeshen.triparttite.core.response.converter.ResponseConverter;
import work.gaigeshen.triparttite.ding.openapi.accesstoken.DingAccessToken;
import work.gaigeshen.triparttite.ding.openapi.accesstoken.DingAccessTokenManager;
import work.gaigeshen.triparttite.ding.openapi.config.DingConfig;
import work.gaigeshen.triparttite.ding.openapi.parameters.DingParametersBuilder;
import work.gaigeshen.triparttite.ding.openapi.response.DingResponse;

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
