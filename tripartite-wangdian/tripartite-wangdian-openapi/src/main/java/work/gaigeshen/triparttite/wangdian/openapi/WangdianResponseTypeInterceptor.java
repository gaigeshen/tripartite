package work.gaigeshen.triparttite.wangdian.openapi;

import work.gaigeshen.triparttite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.triparttite.core.interceptor.InterceptingException;

/**
 * 由于旺店通返回的响应结果类型申明不正确导致转换结果对象失败，添加此拦截器可解决该问题
 *
 * @author gaigeshen
 */
public class WangdianResponseTypeInterceptor extends AbstractInterceptor {

    @Override
  protected void updateRequest(Request request) throws InterceptingException {
  }

  @Override
  protected void validateResponse(Request request, Response response) throws InterceptingException {
    response.headers().putValue("Content-Type", "application/json");
  }
}
