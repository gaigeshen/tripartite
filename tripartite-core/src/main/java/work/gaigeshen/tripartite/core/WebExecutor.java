package work.gaigeshen.tripartite.core;

import work.gaigeshen.tripartite.core.response.consumer.ResponseConsumer;
import work.gaigeshen.tripartite.core.parameter.Parameters;
import work.gaigeshen.tripartite.core.parameter.creator.ParametersCreator;
import work.gaigeshen.tripartite.core.response.converter.ResponseConverter;

/**
 * 请求执行器接受请求参数
 *
 * @author gaigeshen
 */
public interface WebExecutor {

  <T> T execute(String url, Class<T> responseClass, Object... uriVariables) throws WebException;

  <T> T execute(String url, ResponseConverter<T> converter, Object... uriVariables) throws WebException;

  void execute(String url, ResponseConsumer consumer, Object... uriVariables) throws WebException;

  <T> T execute(String url, Parameters parameters, Class<T> responseClass, Object... uriVariables) throws WebException;

  <T> T execute(String url, Parameters parameters, ResponseConverter<T> converter, Object... uriVariables) throws WebException;

  void execute(String url, Parameters parameters, ResponseConsumer consumer, Object... uriVariables) throws WebException;

  <T> T execute(String url, Object parameters, Class<T> responseClass, Object... uriVariables) throws WebException;

  <T> T execute(String url, Object parameters, ResponseConverter<T> converter, Object... uriVariables) throws WebException;

  void execute(String url, Object parameters, ResponseConsumer consumer, Object... uriVariables) throws WebException;

  <T> T execute(String url, ParametersCreator creator, Class<T> responseClass, Object... uriVariables) throws WebException;

  <T> T execute(String url, ParametersCreator creator, ResponseConverter<T> converter, Object... uriVariables) throws WebException;

  void execute(String url, ParametersCreator creator, ResponseConsumer consumer, Object... uriVariables) throws WebException;
}
