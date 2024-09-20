package work.gaigeshen.tripartite.qyweixin.openapi.notify;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import work.gaigeshen.tripartite.core.client.Clients;
import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentReceiver;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.core.notify.NotifyContentIncorrectException;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.qyweixin.openapi.config.QyWeixinConfig;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 企业微信异步通知数据接收器，首先会对接收到的异步通知数据校验签名是否合法，然后进行解密操作，将解密后的内容原封不动加入到原始的异步通知数据里面
 *
 * @author gaigeshen
 */
public class QyWeixinNotifyContentReceiver extends AbstractNotifyContentReceiver<DefaultNotifyContent> {

    private final Clients<QyWeixinConfig> qyWeixinClients;

    private final XmlMapper xmlMapper;

    public QyWeixinNotifyContentReceiver(Clients<QyWeixinConfig> clients) {
        ArgumentValidate.notNull(clients, "clients cannot be null");
        this.qyWeixinClients = clients;
        this.xmlMapper = XmlMapper.builder().build();
    }

    @Override
    protected DefaultNotifyContent validate(DefaultNotifyContent content) throws NotifyContentIncorrectException {
        String suiteId = (String) content.getValue("suite_id");
        String agentId = (String) content.getValue("agent_id");
        QyWeixinConfig qyWeixinConfig;
        if (StringUtils.isNotBlank(suiteId)) {
            qyWeixinConfig = qyWeixinClients.getConfig(cfg -> Objects.equals(cfg.getSuiteId(), suiteId));
        } else if (StringUtils.isNotBlank(agentId)) {
            qyWeixinConfig = qyWeixinClients.getConfig(cfg -> Objects.equals(cfg.getAgentId(), Integer.parseInt(agentId)));
        } else {
            throw new NotifyContentIncorrectException("could not find [suite_id] or [agent_id] parameter: " + content);
        }
        content.put("qy_weixin_config", qyWeixinConfig);
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
            if (!Objects.equals(genSignature(qyWeixinConfig, timestamp, nonce, echostr), signature)) {
                throw new NotifyContentIncorrectException("invalid signature: " + content);
            }
            try {
                content.put("echostr", decrypt(qyWeixinConfig, echostr));
            } catch (GeneralSecurityException e) {
                throw new NotifyContentIncorrectException("could not decrypt [echostr]: " + content, e);
            }
            return content;
        }
        Map<?, ?> receivedMessage;
        try {
            receivedMessage = xmlMapper.readValue(content.getBodyAsString(), HashMap.class);
        } catch (Exception e) {
            throw new NotifyContentIncorrectException("could not read request body: " + content, e);
        }
        String encrypted = (String) receivedMessage.get("Encrypt");
        if (StringUtils.isBlank(encrypted)) {
            throw new NotifyContentIncorrectException("request body field [Encrypt] is blank: " + content);
        }
        if (!Objects.equals(genSignature(qyWeixinConfig, timestamp, nonce, encrypted), signature)) {
            throw new NotifyContentIncorrectException("invalid signature: " + content);
        }
        try {
            content.put("decrypted", decrypt(qyWeixinConfig, encrypted));
        } catch (GeneralSecurityException e) {
            throw new NotifyContentIncorrectException("could not decrypt: " + content, e);
        }
        return content;
    }

    /**
     * 此方法用于计算密文的签名，这是个静态方法，在其他类中可以直接调用
     *
     * @param config 企业微信配置不可为空
     * @param timestamp 时间戳不可为空
     * @param nonce 随机值不可为空
     * @param encrypted 密文不可为空
     * @return 返回该密文的签名
     */
    public static String genSignature(QyWeixinConfig config, String timestamp, String nonce, String encrypted) {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notTrue(Objects.isNull(timestamp) || Objects.isNull(nonce) || Objects.isNull(encrypted),
                "timestamp and nonce and encrypted cannot be null");
        String[] message = new String[]{config.getToken(), timestamp, nonce, encrypted};
        Arrays.sort(message);
        return DigestUtils.sha1Hex(String.join("", message));
    }

    /**
     * 此方法用于对明文进行加密，加密方案参见企业微信文档，这是个静态方法，在其他类中可以直接调用
     *
     * @param config 企业微信配置不可为空
     * @param plainText 明文不可为空
     * @return 返回该明文加密后的密文
     * @throws GeneralSecurityException 加密通用异常
     */
    public static String encrypt(QyWeixinConfig config, String plainText) throws GeneralSecurityException {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(plainText, "plainText cannot be null");
        byte[] secretKeyBytes = Base64.decodeBase64(config.getAesKey() + "=");

        byte[] randomBytes = RandomStringUtils.randomAscii(16).getBytes();
        byte[] plainTextBytes = plainText.getBytes();
        byte[] suiteIdBytes = config.getSuiteId().getBytes();

        byte[] plainTextLenBytes = new byte[]{
                (byte) (plainTextBytes.length >> 24 & 0xff),
                (byte) (plainTextBytes.length >> 16 & 0xff),
                (byte) (plainTextBytes.length >> 8 & 0xff),
                (byte) (plainTextBytes.length & 0xff)
        };

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try {
            byteStream.write(randomBytes);
            byteStream.write(plainTextLenBytes);
            byteStream.write(plainTextBytes);
            byteStream.write(suiteIdBytes);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

        int padCount = 32 - (32 % byteStream.size());
        byte padChar = (byte) (padCount & 0xff);
        for (int i = 0; i < padCount; i++) {
            byteStream.write(padChar);
        }
        byte[] allBytes = byteStream.toByteArray();

        SecretKeySpec keySpec = new SecretKeySpec(secretKeyBytes, "AES");
        IvParameterSpec paramSpec = new IvParameterSpec(secretKeyBytes, 0, 16);

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);

        byte[] encrypted = cipher.doFinal(allBytes);
        return Base64.encodeBase64String(encrypted);
    }

    /**
     * 此方法用于对密文进行解密，解密方案参见企业微信文档，这是个静态方法，在其他类中可以直接调用
     *
     * @param config 企业微信配置不可为空
     * @param encrypted 密文不可为空
     * @return 返回该密文对应的解密明文
     * @throws GeneralSecurityException 解密通用异常
     */
    public static String decrypt(QyWeixinConfig config, String encrypted) throws GeneralSecurityException {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(encrypted, "encrypted cannot be null");
        byte[] aesKeyBytes = Base64.decodeBase64(config.getAesKey() + "=");

        SecretKeySpec keySpec = new SecretKeySpec(aesKeyBytes, "AES");
        IvParameterSpec paramSpec = new IvParameterSpec(aesKeyBytes, 0, 16);

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);

        byte[] decrypted = cipher.doFinal(Base64.decodeBase64(encrypted));

        int padChar = decrypted[decrypted.length - 1];
        byte[] allBytes = Arrays.copyOfRange(decrypted, 0, decrypted.length - padChar);

        byte[] plainTextBytesLen = Arrays.copyOfRange(allBytes, 16, 20);

        int plainTextLen = 0;
        for (int i = 0; i < 4; ++i) {
            plainTextLen <<= 8;
            plainTextLen |= plainTextBytesLen[i] & 0xff;
        }
        return new String(Arrays.copyOfRange(allBytes, 20, 20 + plainTextLen));
    }
}
