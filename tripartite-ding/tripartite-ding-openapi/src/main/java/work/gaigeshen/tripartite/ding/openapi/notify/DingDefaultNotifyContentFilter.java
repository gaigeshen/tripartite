package work.gaigeshen.tripartite.ding.openapi.notify;

import org.apache.commons.lang3.RandomStringUtils;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.core.notify.filter.AbstractDefaultNotifyContentFilter;
import work.gaigeshen.tripartite.core.util.TimestampUtils;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

/**
 * 钉钉异步通知数据过滤器，用于接收异步通知数据和响应处理结果给钉钉服务器，具体的响应格式参见钉钉文档
 *
 * @author gaigeshen
 */
public class DingDefaultNotifyContentFilter extends AbstractDefaultNotifyContentFilter {

    public static final String REPLY_TEXT_TEMPLATE = "{\"msg_signature\":\"%s\",\"timeStamp\":\"%s\",\"nonce\":\"%s\",\"encrypt\":\"%s\"}";

    public DingDefaultNotifyContentFilter(DingDefaultNotifyContentReceiver receiver) {
        super(receiver);
    }

    @Override
    protected void renderOnSuccess(DefaultNotifyContent notifyContent, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        renderInternal("success", notifyContent, response);
    }

    @Override
    protected void renderOnFail(DefaultNotifyContent notifyContent, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        renderInternal("fail", notifyContent, response);
    }

    private void renderInternal(String replyPlainText, DefaultNotifyContent notifyContent, HttpServletResponse response) throws ServletException, IOException {

        DingConfig dingConfig = (DingConfig) notifyContent.getValue("ding_config");

        String timestamp = TimestampUtils.unixTimestamp();
        String nonce = RandomStringUtils.randomAlphanumeric(16);

        String encrypted;
        try {
            encrypted = DingDefaultNotifyContentReceiver.encrypt(dingConfig, replyPlainText);
        } catch (GeneralSecurityException e) {
            throw new ServletException(e.getMessage(), e);
        }

        String signature = DingDefaultNotifyContentReceiver.genSignature(dingConfig, timestamp, nonce, encrypted);

        String replyText = String.format(REPLY_TEXT_TEMPLATE, signature, timestamp, nonce, encrypted);

        response.setContentType("application/json;charset=UTF-8");
        response.getOutputStream().write(replyText.getBytes(StandardCharsets.UTF_8));
    }
}
