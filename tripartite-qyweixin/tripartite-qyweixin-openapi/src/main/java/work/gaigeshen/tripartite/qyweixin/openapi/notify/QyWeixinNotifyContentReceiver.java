package work.gaigeshen.tripartite.qyweixin.openapi.notify;

import org.apache.commons.lang3.StringUtils;
import work.gaigeshen.tripartite.core.client.Clients;
import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentReceiver;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.core.notify.NotifyContentIncorrectException;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.core.util.xml.XmlUtils;
import work.gaigeshen.tripartite.qyweixin.openapi.config.QyWeixinConfig;
import work.gaigeshen.tripartite.qyweixin.openapi.notify.message.ReceiveMessage;
import work.gaigeshen.tripartite.qyweixin.openapi.notify.util.SecureUtils;
import work.gaigeshen.tripartite.qyweixin.openapi.notify.util.SignatureUtils;

import java.security.GeneralSecurityException;
import java.util.Objects;

/**
 * 企业微信回调通知数据接收器，首先会对接收到的回调通知数据校验签名是否合法，然后进行解密操作，将解密后的内容原封不动加入到原始的异步通知数据里面
 *
 * @author gaigeshen
 */
public class QyWeixinNotifyContentReceiver extends AbstractNotifyContentReceiver<DefaultNotifyContent> {

    private final Clients<QyWeixinConfig> qyWeixinClients;

    public QyWeixinNotifyContentReceiver(Clients<QyWeixinConfig> clients) {
        ArgumentValidate.notNull(clients, "clients cannot be null");
        this.qyWeixinClients = clients;
    }

    @Override
    protected DefaultNotifyContent validate(DefaultNotifyContent content) throws NotifyContentIncorrectException {
        QyWeixinConfig config = determineConfig(content);
        content.put("qy_weixin_config", config);
        String signature = (String) content.getValue("msg_signature");
        if (StringUtils.isBlank(signature)) {
            throw new NotifyContentIncorrectException("could not find [msg_signature] parameter: " + content);
        }
        String timestamp = (String) content.getValue("timestamp");
        if (StringUtils.isBlank(timestamp)) {
            throw new NotifyContentIncorrectException("could not find [timestamp] parameter: " + content);
        }
        String nonce = (String) content.getValue("nonce");
        if (StringUtils.isBlank(nonce)) {
            throw new NotifyContentIncorrectException("could not find [nonce] parameter: " + content);
        }
        String echostr = (String) content.getValue("echostr");
        if (StringUtils.isNotBlank(echostr)) {
            if (!Objects.equals(SignatureUtils.genSignature(config.getToken(), timestamp, nonce, echostr), signature)) {
                throw new NotifyContentIncorrectException("invalid signature: " + content);
            }
            try {
                String decrypt = SecureUtils.decrypt(config.getAesKey(), echostr);
                content.put("echostr", decrypt);
            } catch (GeneralSecurityException e) {
                throw new NotifyContentIncorrectException("could not decrypt: " + content, e);
            }
            return content;
        }
        String bodyString = content.getBodyAsString();
        if (StringUtils.isBlank(bodyString)) {
            throw new NotifyContentIncorrectException("could not find request body: " + content);
        }
        ReceiveMessage receiveMessage = XmlUtils.decodeObject(bodyString, ReceiveMessage.class);
        String encrypted = receiveMessage.getEncrypt();
        if (StringUtils.isBlank(encrypted)) {
            throw new NotifyContentIncorrectException("request body field [Encrypt] is blank: " + content);
        }
        if (!Objects.equals(SignatureUtils.genSignature(config.getToken(), timestamp, nonce, encrypted), signature)) {
            throw new NotifyContentIncorrectException("invalid signature: " + content);
        }
        try {
            content.put("decrypted", SecureUtils.decrypt(config.getAesKey(), encrypted));
        } catch (GeneralSecurityException e) {
            throw new NotifyContentIncorrectException("could not decrypt: " + content, e);
        }
        return content;
    }

    /**
     * 确定企业微信配置信息，由于企业微信推送通知数据的时候不会表明是哪个应用，所以需要配置回调地址的时候手动加上相关参数
     *
     * @param content 异步通知数据
     * @return 企业微信配置信息，如果没有在回调地址中配置相关参数或者获取配置信息失败的情况，会抛出异常
     * @throws NotifyContentIncorrectException 没有成功获取到企业微信配置信息
     */
    private QyWeixinConfig determineConfig(DefaultNotifyContent content) throws NotifyContentIncorrectException {
        String agentId = (String) content.getValue("agent_id");
        if (StringUtils.isNotBlank(agentId)) {
            return qyWeixinClients.getConfig(cfg -> Objects.equals(cfg.getAgentId(), Integer.parseInt(agentId)));
        }
        throw new NotifyContentIncorrectException("could not determine config: " + content);
    }
}
