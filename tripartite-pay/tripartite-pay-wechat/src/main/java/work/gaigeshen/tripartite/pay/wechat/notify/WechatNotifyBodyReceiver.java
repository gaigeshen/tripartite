package work.gaigeshen.tripartite.pay.wechat.notify;

import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentReceiver;
import work.gaigeshen.tripartite.core.notify.NotifyContentIncorrectException;
import work.gaigeshen.tripartite.core.util.json.JsonCodec;
import work.gaigeshen.tripartite.pay.wechat.WechatClients;
import work.gaigeshen.tripartite.pay.wechat.config.WechatCertificates;
import work.gaigeshen.tripartite.pay.wechat.config.WechatConfig;
import work.gaigeshen.tripartite.pay.wechat.config.WechatSecretKey;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * 微信支付异步通知数据体接收器
 *
 * @author gaigeshen
 */
public class WechatNotifyBodyReceiver extends AbstractNotifyContentReceiver<WechatNotifyBody> {

    private final WechatClients wechatClients;

    public WechatNotifyBodyReceiver(WechatClients wechatClients) {
        if (Objects.isNull(wechatClients)) {
            throw new IllegalArgumentException("wechat clients cannot be null");
        }
        this.wechatClients = wechatClients;
    }

    @Override
    protected WechatNotifyBody validate(WechatNotifyBody body) throws NotifyContentIncorrectException {
        String bodyString = body.getBodyAsString();
        Map<String, Object> bodyDecoded = JsonCodec.instance().decodeObject(bodyString);

        String eventType = (String) bodyDecoded.get("event_type");
        if (Objects.isNull(eventType)) {
            throw new NotifyContentIncorrectException("notify event type not found: " + body);
        }
        body.setEventType(eventType);

        String timestamp = body.getHeaderValue("Wechatpay-Timestamp");
        if (Objects.isNull(timestamp)) {
            throw new NotifyContentIncorrectException("missing [Wechatpay-Timestamp] header: " + body);
        }
        String nonce = body.getHeaderValue("Wechatpay-Nonce");
        if (Objects.isNull(nonce)) {
            throw new NotifyContentIncorrectException("missing [Wechatpay-Nonce] header: " + body);
        }
        String serial = body.getHeaderValue("Wechatpay-Serial");
        if (Objects.isNull(serial)) {
            throw new NotifyContentIncorrectException("missing [Wechatpay-Serial] header: " + body);
        }
        String signature = body.getHeaderValue("Wechatpay-Signature");
        if (Objects.isNull(signature)) {
            throw new NotifyContentIncorrectException("missing [Wechatpay-Signature] header: " + body);
        }
        String signContent = timestamp + "\n" + nonce + "\n" + bodyString + "\n";

        WechatConfig config = wechatClients.getConfig(body.getAppId(), body.getMerchantId());

        WechatCertificates certificates = config.getCertificates();
        if (!certificates.verify(serial, signature, signContent.getBytes(StandardCharsets.UTF_8))) {
            throw new NotifyContentIncorrectException("sign is invalid: " + body);
        }

        Map<?, ?> resource = (Map<?, ?>) bodyDecoded.get("resource");
        if (Objects.isNull(resource)) {
            throw new NotifyContentIncorrectException("notify resource not found: " + body);
        }
        String resourceCiphertext = (String) resource.get("ciphertext");
        if (Objects.isNull(resourceCiphertext)) {
            throw new NotifyContentIncorrectException("notify ciphertext resource not found: " + body);
        }
        String resourceNonce = (String) resource.get("nonce");
        if (Objects.isNull(resourceNonce)) {
            throw new NotifyContentIncorrectException("notify nonce resource not found: " + body);
        }
        String resourceAssociatedData = (String) resource.get("associated_data");

        byte[] nonceBytes = resourceNonce.getBytes(StandardCharsets.UTF_8);
        byte[] associatedDataBytes;
        if (Objects.isNull(resourceAssociatedData)) {
            associatedDataBytes = new byte[0];
        } else {
            associatedDataBytes = resourceAssociatedData.getBytes(StandardCharsets.UTF_8);
        }
        WechatSecretKey secretKey = config.getSecretKey();
        byte[] decrypted = secretKey.decrypt(resourceCiphertext, nonceBytes, associatedDataBytes);

        body.setResource(new String(decrypted, StandardCharsets.UTF_8));

        return body;
    }
}
