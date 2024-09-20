package work.gaigeshen.tripartite.qyweixin.openapi.notify;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.core.notify.filter.AbstractDefaultNotifyContentFilter;
import work.gaigeshen.tripartite.core.util.TimestampUtils;
import work.gaigeshen.tripartite.qyweixin.openapi.config.QyWeixinConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

/**
 * 企业微信异步通知数据过滤器，用于接收异步通知数据和响应处理结果给钉钉服务器，具体的响应格式参见企业微信文档
 *
 * @author gaigeshen
 */
public class QyWeixinNotifyContentFilter extends AbstractDefaultNotifyContentFilter {

    public static final String REPLY_TEXT_TEMPLATE = "<xml><MsgSignature><![CDATA[%s]]></MsgSignature><TimeStamp>%s</TimeStamp><Nonce><![CDATA[%s]]></Nonce><Encrypt><![CDATA[%s]]></Encrypt></xml>";

    public QyWeixinNotifyContentFilter(QyWeixinNotifyContentReceiver receiver) {
        super(receiver);
    }

    @Override
    protected void renderOnSuccess(DefaultNotifyContent notifyContent,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws ServletException, IOException {
        String echostr = (String) notifyContent.getValue("echostr");
        if (StringUtils.isNotBlank(echostr)) {
            response.setContentType("text/plain;charset=UTF-8");
            response.getOutputStream().write(echostr.getBytes(StandardCharsets.UTF_8));
        } else {
            renderWithTemplate("success", notifyContent, response);
        }
    }

    @Override
    protected void renderOnFail(DefaultNotifyContent notifyContent,
                                HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        renderWithTemplate("fail", notifyContent, response);
    }

    private void renderWithTemplate(String replyPlainText,
                                    DefaultNotifyContent notifyContent,
                                    HttpServletResponse response) throws ServletException, IOException {

        QyWeixinConfig qyWeixinConfig = (QyWeixinConfig) notifyContent.getValue("qy_weixin_config");

        String timestamp = TimestampUtils.unixTimestamp();
        String nonce = RandomStringUtils.randomAlphanumeric(16);

        String encrypted;
        try {
            encrypted = QyWeixinNotifyContentReceiver.encrypt(qyWeixinConfig, replyPlainText);
        } catch (GeneralSecurityException e) {
            throw new ServletException(e.getMessage(), e);
        }

        String signature = QyWeixinNotifyContentReceiver.genSignature(qyWeixinConfig, timestamp, nonce, encrypted);

        String replyText = String.format(REPLY_TEXT_TEMPLATE, signature, timestamp, nonce, encrypted);

        response.setContentType("text/plain;charset=UTF-8");
        response.getOutputStream().write(replyText.getBytes(StandardCharsets.UTF_8));
    }
}
