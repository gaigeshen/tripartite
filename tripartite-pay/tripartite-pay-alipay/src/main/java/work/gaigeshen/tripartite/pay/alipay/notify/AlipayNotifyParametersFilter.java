package work.gaigeshen.tripartite.pay.alipay.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * 支付宝异步通知参数过滤器
 *
 * @author gaigeshen
 */
public class AlipayNotifyParametersFilter implements Filter {

  private static final Logger log = LoggerFactory.getLogger(AlipayNotifyParametersFilter.class);

  public static final byte[] SUCCESS_REPLY = "success".getBytes(StandardCharsets.UTF_8);

  public static final byte[] FAIL_REPLY = "fail".getBytes(StandardCharsets.UTF_8);

  private final AlipayNotifyParametersReceiver receiver;

  public AlipayNotifyParametersFilter(AlipayNotifyParametersReceiver receiver) {
    if (Objects.isNull(receiver)) {
      throw new IllegalArgumentException("alipay notify parameters receiver cannot be null");
    }
    this.receiver = receiver;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
    AlipayNotifyParameters notifyParameters = new AlipayNotifyParameters();
    Map<String, String[]> parameters = request.getParameterMap();
    for (Map.Entry<String, String[]> parameter : parameters.entrySet()) {
      notifyParameters.put(parameter.getKey(), String.join(",", parameter.getValue()));
    }
    try {
      receiver.receive(notifyParameters);
    } catch (Exception e) {
      log.warn(e.getMessage(), e);
      response.getOutputStream().write(FAIL_REPLY);
      return;
    }
    response.getOutputStream().write(SUCCESS_REPLY);
  }

  @Override
  public void init(FilterConfig filterConfig) {
  }

  @Override
  public void destroy() {
  }
}
