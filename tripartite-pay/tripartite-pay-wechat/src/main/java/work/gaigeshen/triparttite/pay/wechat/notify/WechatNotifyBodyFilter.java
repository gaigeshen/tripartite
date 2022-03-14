package work.gaigeshen.triparttite.pay.wechat.notify;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

/**
 * 微信支付异步通知数据体过滤器
 *
 * @author gaigeshen
 */
public class WechatNotifyBodyFilter implements Filter {

  private static final Logger log = LoggerFactory.getLogger(WechatNotifyBodyFilter.class);

  public static final byte[] SUCCESS_REPLY = "{\"code\":\"SUCCESS\", \"message\": \"OK\"}".getBytes(StandardCharsets.UTF_8);

  public static final byte[] FAIL_REPLY = "{\"code\":\"FAIL\", \"message\": \"ERROR\"}".getBytes(StandardCharsets.UTF_8);

  private final WechatNotifyBodyReceiver receiver;

  public WechatNotifyBodyFilter(WechatNotifyBodyReceiver receiver) {
    if (Objects.isNull(receiver)) {
      throw new IllegalArgumentException("wechat notify body receiver cannot be null");
    }
    this.receiver = receiver;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    if (!(request instanceof HttpServletRequest)) {
      throw new ServletException("can only support http servlet request");
    }
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    String requestURI = httpRequest.getRequestURI();

    int slashLastIndex = requestURI.lastIndexOf("/");
    int slashFirstIndex = requestURI.substring(0, slashLastIndex).lastIndexOf("/");
    if (slashFirstIndex == -1) {
      throw new ServletException("cannot resolve 'appId' and 'merchantId': " + requestURI);
    }
    String appId = requestURI.substring(slashFirstIndex + 1, slashLastIndex);
    String merchantId = requestURI.substring(slashLastIndex + 1);
    if (StringUtils.isAnyBlank(appId, merchantId)) {
      throw new ServletException("cannot resolve 'appId' and 'merchantId': " + requestURI);
    }
    byte[] bodyBytes = IOUtils.toByteArray(httpRequest.getInputStream());
    WechatNotifyBody notifyBody = new WechatNotifyBody(bodyBytes, appId, merchantId);

    Enumeration<String> headerNames = httpRequest.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      Enumeration<String> headerValues = httpRequest.getHeaders(headerName);
      List<String> values = new ArrayList<>();
      while (headerValues.hasMoreElements()) {
        values.add(headerValues.nextElement());
      }
      notifyBody.putHeader(headerName, values.toArray(new String[0]));
    }

    try {
      receiver.receive(notifyBody);
    } catch (Exception e) {
      log.warn(e.getMessage(), e);
      httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
      httpResponse.getOutputStream().write(FAIL_REPLY);
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
