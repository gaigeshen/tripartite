package work.gaigeshen.tripartite.core.response.consumer;

import work.gaigeshen.tripartite.core.header.Headers;

import java.io.InputStream;

/**
 * 响应结果数据消费器
 *
 * @author gaigeshen
 */
public interface ResponseConsumer {
  /**
   * 消费响应结果数据
   *
   * @param inputStream 响应结果数据输入流不为空
   * @param headers 响应结果数据响应头不为空
   * @throws ResponseConsumingException 消费响应结果数据的时候发生异常
   */
  void consume(InputStream inputStream, Headers headers) throws ResponseConsumingException;

}
