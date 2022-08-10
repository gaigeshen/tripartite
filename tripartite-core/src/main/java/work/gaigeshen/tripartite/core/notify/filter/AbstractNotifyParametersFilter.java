package work.gaigeshen.tripartite.core.notify.filter;

import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentReceiver;
import work.gaigeshen.tripartite.core.notify.NotifyContentReceiver;
import work.gaigeshen.tripartite.core.notify.NotifyParameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * 异步通知参数过滤器的抽象实现
 *
 * @author gaigeshen
 */
public abstract class AbstractNotifyParametersFilter<C extends NotifyParameters> extends AbstractNotifyContentFilter<C> {

  protected AbstractNotifyParametersFilter(NotifyContentReceiver<C> receiver) {
    super(receiver);
  }

  protected AbstractNotifyParametersFilter() {
    super(new AbstractNotifyContentReceiver<C>() {});
  }

  @Override
  protected final C initNotifyContent(HttpServletRequest request) throws ServletException, IOException {
    C notifyParameters = initNotifyParameters(request);
    if (Objects.isNull(notifyParameters)) {
      throw new ServletException("initialized notify parameters cannot be null: " + request);
    }
    Map<String, String[]> parameters = request.getParameterMap();
    for (Map.Entry<String, String[]> parameter : parameters.entrySet()) {
      notifyParameters.put(parameter.getKey(), String.join(",", parameter.getValue()));
    }
    return notifyParameters;
  }

  /**
   * 此方法用于实例化异步通知参数对象
   *
   * @param request 请求对象不为空
   * @return 返回的异步通知参数对象不能为空
   * @throws ServletException 无法实例化异步通知参数对象的时候可以抛出此异常
   * @throws IOException 可能在使用请求对象的时候发生异常
   */
  protected abstract C initNotifyParameters(HttpServletRequest request) throws ServletException, IOException;
}
