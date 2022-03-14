package work.gaigeshen.triparttite.core.interceptor;

import work.gaigeshen.triparttite.core.WebException;

/**
 * 拦截处理的时候发生异常
 *
 * @author gaigeshen
 */
public class InterceptingException extends WebException {
  public InterceptingException(String message) {
    super(message);
  }
  public InterceptingException(String message, Throwable cause) {
    super(message, cause);
  }
}
