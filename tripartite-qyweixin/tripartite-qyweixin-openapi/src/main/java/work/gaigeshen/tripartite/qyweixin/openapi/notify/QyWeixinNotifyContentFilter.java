package work.gaigeshen.tripartite.qyweixin.openapi.notify;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.core.notify.filter.AbstractDefaultNotifyContentFilter;
import work.gaigeshen.tripartite.core.util.TimestampUtils;
import work.gaigeshen.tripartite.core.util.xml.XmlUtils;
import work.gaigeshen.tripartite.qyweixin.openapi.config.QyWeixinConfig;
import work.gaigeshen.tripartite.qyweixin.openapi.notify.message.ReplyMessage;
import work.gaigeshen.tripartite.qyweixin.openapi.notify.util.SecureUtils;
import work.gaigeshen.tripartite.qyweixin.openapi.notify.util.SignatureUtils;

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
@Slf4j
public class QyWeixinNotifyContentFilter extends AbstractDefaultNotifyContentFilter {

    public QyWeixinNotifyContentFilter(QyWeixinNotifyContentReceiver receiver) {
        super(receiver);
    }

    @Override
    protected void renderOnSuccess(DefaultNotifyContent notifyContent,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws ServletException, IOException {
        String echostr = (String) notifyContent.getValue("echostr");
        if (StringUtils.isNotBlank(echostr)) {
            renderText(echostr, response);
            log.info("validate notify content receiver success, rendered response: {}", echostr);
        } else {
            String renderedReplyMessage = renderReplyMessage("success", notifyContent, response);
            log.info("receive and process notify content success, rendered reply message: {}", renderedReplyMessage);
        }
    }

    @Override
    protected void renderOnFail(DefaultNotifyContent notifyContent,
                                HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        renderReplyMessage("fail", notifyContent, response);
    }

    private String renderReplyMessage(String replyPlainText, DefaultNotifyContent notifyContent, HttpServletResponse response) throws ServletException, IOException {
        QyWeixinConfig qyWeixinConfig = (QyWeixinConfig) notifyContent.getValue("qy_weixin_config");
        String timestamp = TimestampUtils.unixTimestamp();
        String nonce = RandomStringUtils.randomAlphanumeric(16);
        String encrypted;
        try {
            encrypted = SecureUtils.encrypt(qyWeixinConfig.getAesKey(), qyWeixinConfig.getCorpId(), replyPlainText);
        } catch (GeneralSecurityException e) {
            throw new ServletException(e.getMessage(), e);
        }
        String signature = SignatureUtils.genSignature(qyWeixinConfig.getToken(), timestamp, nonce, encrypted);
        ReplyMessage replyMessage = new ReplyMessage().setEncrypt(encrypted).setMsgSignature(signature).setTimeStamp(timestamp).setNonce(nonce);
        String encodedReplyMessage = XmlUtils.encode(replyMessage);

        renderText(encodedReplyMessage, response);

        return encodedReplyMessage;
    }

    private void renderText(String text, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        response.getOutputStream().write(text.getBytes(StandardCharsets.UTF_8));
    }
}
