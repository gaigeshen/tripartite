package work.gaigeshen.tripartite.qyweixin.openapi.notify;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.core.notify.filter.AbstractDefaultNotifyContentFilter;
import work.gaigeshen.tripartite.core.util.TimestampUtils;
import work.gaigeshen.tripartite.core.util.xml.XmlCodec;
import work.gaigeshen.tripartite.qyweixin.openapi.config.QyWeixinConfig;
import work.gaigeshen.tripartite.qyweixin.openapi.notify.message.ReplyMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

/**
 * 企业微信回调通知数据过滤器，用于接收回调通知数据和响应处理结果给企业微信服务器，具体的响应格式参见企业微信文档
 *
 * @author gaigeshen
 */
public class QyWeixinNotifyContentFilter extends AbstractDefaultNotifyContentFilter {

    public QyWeixinNotifyContentFilter(QyWeixinNotifyContentReceiver receiver) {
        super(receiver);
    }

    @Override
    protected void renderOnSuccess(DefaultNotifyContent notifyContent, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String echostr = (String) notifyContent.getValue("echostr");
        if (StringUtils.isNotBlank(echostr)) {
            renderText(echostr, response);
        } else {
            renderReplyMessage("success", notifyContent, response);
        }
    }

    @Override
    protected void renderOnFail(DefaultNotifyContent notifyContent, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        renderReplyMessage("fail", notifyContent, response);
    }

    private void renderReplyMessage(String replyPlainText, DefaultNotifyContent notifyContent, HttpServletResponse response) throws ServletException, IOException {
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

        ReplyMessage replyMessage = new ReplyMessage().setEncrypt(encrypted).setMsgSignature(signature).setTimeStamp(timestamp).setNonce(nonce);
        String encodedReplyMessage = XmlCodec.instance().encode(replyMessage);

        renderText(encodedReplyMessage, response);
    }

    private void renderText(String text, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        response.getOutputStream().write(text.getBytes(StandardCharsets.UTF_8));
    }
}
