package work.gaigeshen.triparttite.core.interceptor;

import work.gaigeshen.triparttite.core.WebExecutionException;

/**
 * 抽象拦截器有两个方法分别用于修改请求内容和校验响应内容
 *
 * @author gaigeshen
 */
public abstract class AbstractInterceptor implements Interceptor {

  @Override
  public final Response intercept(Request request, Chain chain) throws InterceptingException, WebExecutionException {
    updateRequest(request);

    Response response = chain.intercept(request);
    validateResponse(request, response);
    return response;
  }

  /**
   * 修改请求内容
   *
   * @param request 请求内容不为空
   * @throws InterceptingException 修改请求内容失败抛出此异常或者其他运行时异常
   */
  protected abstract void updateRequest(Request request) throws InterceptingException;

  /**
   * 校验响应内容
   *
   * @param request 请求内容不为空
   * @param response 响应内容不为空
   * @throws InterceptingException 校验失败抛出此异常或者其他运行时异常
   */
  protected abstract void validateResponse(Request request, Response response) throws InterceptingException;
}
