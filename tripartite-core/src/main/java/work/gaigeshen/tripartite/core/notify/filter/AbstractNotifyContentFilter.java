package work.gaigeshen.tripartite.core.notify.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.notify.NotifyContent;
import work.gaigeshen.tripartite.core.notify.NotifyContentReceiver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

/**
 * 抽象的异步通知数据过滤器
 *
 * @author gaigeshen
 */
public abstract class AbstractNotifyContentFilter<C extends NotifyContent> implements Filter {

  private static final Logger log = LoggerFactory.getLogger(AbstractNotifyContentFilter.class);

  private final NotifyContentReceiver<C> receiver;

  protected AbstractNotifyContentFilter(NotifyContentReceiver<C> receiver) {
    if (Objects.isNull(receiver)) {
      throw new IllegalArgumentException("notify content receiver cannot be null");
    }
    this.receiver = receiver;
  }

  @Override
  public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    if (!(request instanceof HttpServletRequest)) {
      throw new ServletException("can only support http servlet request");
    }
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    C notifyContent = initNotifyContent(httpRequest);
    if (Objects.isNull(notifyContent)) {
      throw new ServletException("initialized notify content cannot be null: " + httpRequest);
    }

    Enumeration<String> headerNames = httpRequest.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      Enumeration<String> headerValues = httpRequest.getHeaders(headerName);
      List<String> values = new ArrayList<>();
      while (headerValues.hasMoreElements()) {
        values.add(headerValues.nextElement());
      }
      notifyContent.putHeader(headerName, values.toArray(new String[0]));
    }

    try {
      receiver.receive(notifyContent);
    } catch (Exception e) {
      log.warn(e.getMessage(), e);
      renderOnFail(notifyContent, httpRequest, (HttpServletResponse) response);
      return;
    }
    renderOnSuccess(notifyContent, httpRequest, (HttpServletResponse) response);
  }

  /**
   * 此方法用于实例化异步通知数据对象
   *
   * @param request 请求对象不为空
   * @return 返回的异步通知数据对象不能为空
   * @throws ServletException 无法实例化异步通知数据对象的时候可以抛出此异常
   * @throws IOException 可能在使用请求对象的时候发生异常
   */
  protected abstract C initNotifyContent(HttpServletRequest request) throws ServletException, IOException;

  /**
   * 此方法用于处理异步通知数据成功之后响应结果给请求者
   *
   * @param notifyContent 异步通知数据不为空
   * @param request 请求对象不为空
   * @param response 响应对象不能为空
   * @throws ServletException 无法响应结果给请求者
   * @throws IOException 发生输入输出相关的异常
   */
  protected abstract void renderOnSuccess(C notifyContent, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

  /**
   * 此方法用于处理异步通知数据失败之后响应结果给请求者
   *
   * @param notifyContent 异步通知数据不为空
   * @param request 请求对象不为空
   * @param response 响应对象不能为空
   * @throws ServletException 无法响应结果给请求者
   * @throws IOException 发生输入输出相关的异常
   */
  protected abstract void renderOnFail(C notifyContent, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

  @Override
  public void init(FilterConfig filterConfig) {}

  @Override
  public void destroy() {}
}
