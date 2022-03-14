package work.gaigeshen.triparttite.core.interceptor;

import work.gaigeshen.triparttite.core.WebExecutionException;

/**
 * 表示请求执行过程
 *
 * @author gaigeshen
 */
public interface Execution {
  /**
   * 执行请求并返回结果
   *
   * @param request 请求内容不为空
   * @return 响应内容不为空
   * @throws WebExecutionException 执行请求的时候发生异常
   */
  Interceptor.Response execute(Interceptor.Request request) throws WebExecutionException;
}
