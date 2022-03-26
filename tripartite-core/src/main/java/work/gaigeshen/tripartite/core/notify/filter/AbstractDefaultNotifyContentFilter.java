package work.gaigeshen.tripartite.core.notify.filter;

import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentReceiver;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.core.notify.NotifyContentReceiver;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * 默认的异步通知数据过滤器的抽象实现
 *
 * @author gaigeshen
 */
public abstract class AbstractDefaultNotifyContentFilter extends AbstractNotifyContentFilter<DefaultNotifyContent> {

  public AbstractDefaultNotifyContentFilter() {
    super(new AbstractNotifyContentReceiver<>() {});
  }

  public AbstractDefaultNotifyContentFilter(NotifyContentReceiver<DefaultNotifyContent> receiver) {
    super(receiver);
  }

  @Override
  protected final DefaultNotifyContent initNotifyContent(HttpServletRequest request) throws ServletException, IOException {

    byte[] buffer = new byte[4096];
    int len;
    ByteArrayOutputStream output = new ByteArrayOutputStream();

    ServletInputStream input = request.getInputStream();
    while ((len = input.read(buffer)) != -1) {
      output.write(buffer, 0, len);
    }

    DefaultNotifyContent notifyContent = DefaultNotifyContent.create(output.toByteArray());

    postProcessInitNotifyContent(notifyContent, request);

    Map<String, String[]> parameters = request.getParameterMap();
    for (Map.Entry<String, String[]> parameter : parameters.entrySet()) {
      notifyContent.put(parameter.getKey(), String.join(",", parameter.getValue()));
    }

    return notifyContent;
  }

  /**
   * 可以选择重写此方法用于在实例化默认的异步通知数据之后做些操作，默认情况下什么也不做
   *
   * @param notifyContent 默认的异步通知数据不为空
   * @param request 请求对象不为空，可能无法从中读取请求体内容
   * @throws ServletException 可能发生的异常
   */
  protected void postProcessInitNotifyContent(DefaultNotifyContent notifyContent, HttpServletRequest request) throws ServletException {

  }
}
