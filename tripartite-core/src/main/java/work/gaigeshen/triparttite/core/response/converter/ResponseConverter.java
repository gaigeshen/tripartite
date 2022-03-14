package work.gaigeshen.triparttite.core.response.converter;

import work.gaigeshen.triparttite.core.header.Headers;

import java.io.InputStream;

/**
 * 响应结果数据转换器
 *
 * @author gaigeshen
 */
public interface ResponseConverter<R> {

  /**
   * 钻换响应结果数据
   *
   * @param inputStream 响应结果数据输入流不为空
   * @param headers 响应结果数据响应头不为空
   * @return 转换后的目标对象不为空
   * @throws ResponseConvertingException 转换响应结果数据的时候发生异常
   */
  R convert(InputStream inputStream, Headers headers) throws ResponseConvertingException;
}
