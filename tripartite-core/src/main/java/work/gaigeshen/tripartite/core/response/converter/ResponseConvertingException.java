package work.gaigeshen.tripartite.core.response.converter;

import work.gaigeshen.tripartite.core.response.ResponseException;

/**
 * 转换响应结果数据的时候发生异常
 *
 * @author gaigeshen
 */
public class ResponseConvertingException extends ResponseException {
  public ResponseConvertingException(String message) {
    super(message);
  }
  public ResponseConvertingException(String message, Throwable cause) {
    super(message, cause);
  }
}
