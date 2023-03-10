package work.gaigeshen.tripartite.pay.wechat.notify;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import work.gaigeshen.tripartite.core.notify.filter.AbstractNotifyBodyFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 微信支付异步通知数据体过滤器
 *
 * @author gaigeshen
 */
public class WechatNotifyBodyFilter extends AbstractNotifyBodyFilter<WechatNotifyBody> {

    public static final byte[] SUCCESS_REPLY = "{\"code\":\"SUCCESS\", \"message\": \"OK\"}".getBytes(StandardCharsets.UTF_8);

    public static final byte[] FAIL_REPLY = "{\"code\":\"FAIL\", \"message\": \"ERROR\"}".getBytes(StandardCharsets.UTF_8);

    public WechatNotifyBodyFilter(WechatNotifyBodyReceiver receiver) {
        super(receiver);
    }

    @Override
    protected void renderOnSuccess(WechatNotifyBody notifyContent,
                                   HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getOutputStream().write(SUCCESS_REPLY);
    }

    @Override
    protected void renderOnFail(WechatNotifyBody notifyContent,
                                HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getOutputStream().write(FAIL_REPLY);
    }

    @Override
    protected WechatNotifyBody initNotifyBody(HttpServletRequest request) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
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
        byte[] bodyBytes = IOUtils.toByteArray(request.getInputStream());
        return new WechatNotifyBody(bodyBytes, appId, merchantId);
    }
}
