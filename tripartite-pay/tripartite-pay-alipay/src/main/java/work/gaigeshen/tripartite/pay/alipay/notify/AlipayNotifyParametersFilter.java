package work.gaigeshen.tripartite.pay.alipay.notify;

import work.gaigeshen.tripartite.core.notify.filter.AbstractNotifyParametersFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 支付宝异步通知参数过滤器
 *
 * @author gaigeshen
 */
public class AlipayNotifyParametersFilter extends AbstractNotifyParametersFilter<AlipayNotifyParameters> {

  public static final byte[] SUCCESS_REPLY = "success".getBytes(StandardCharsets.UTF_8);

  public static final byte[] FAIL_REPLY = "fail".getBytes(StandardCharsets.UTF_8);

  public AlipayNotifyParametersFilter(AlipayNotifyParametersReceiver receiver) {
    super(receiver);
  }

  @Override
  protected void renderOnSuccess(AlipayNotifyParameters notifyContent,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.getOutputStream().write(SUCCESS_REPLY);
  }

  @Override
  protected void renderOnFail(AlipayNotifyParameters notifyContent,
                              HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.getOutputStream().write(FAIL_REPLY);
  }

  @Override
  protected AlipayNotifyParameters initNotifyParameters(HttpServletRequest request) {
    return new AlipayNotifyParameters();
  }
}
