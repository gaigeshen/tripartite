package work.gaigeshen.triparttite.core.notify.filter;

import work.gaigeshen.triparttite.core.notify.AbstractNotifyContentReceiver;
import work.gaigeshen.triparttite.core.notify.NotifyBody;
import work.gaigeshen.triparttite.core.notify.NotifyContentReceiver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * 异步通知数据体过滤器的抽象实现
 *
 * @author gaigeshen
 */
public abstract class AbstractNotifyBodyFilter<C extends NotifyBody> extends AbstractNotifyContentFilter<C> {

  protected AbstractNotifyBodyFilter(NotifyContentReceiver<C> receiver) {
    super(receiver);
  }

  protected AbstractNotifyBodyFilter() {
    super(new AbstractNotifyContentReceiver<>() {});
  }

  @Override
  protected final C initNotifyContent(HttpServletRequest request) throws ServletException, IOException {
    C notifyBody = initNotifyBody(request);
    if (Objects.isNull(notifyBody)) {
      throw new ServletException("initialized notify body cannot be null: " + request);
    }
    return notifyBody;
  }

  /**
   * 此方法用于实例化异步通知数据体对象
   *
   * @param request 请求对象不为空
   * @return 返回的异步通知数据体对象不能为空
   * @throws ServletException 无法实例化异步通知数据体对象的时候可以抛出此异常
   * @throws IOException 可能在使用请求对象的时候发生异常
   */
  protected abstract C initNotifyBody(HttpServletRequest request) throws ServletException, IOException;
}
