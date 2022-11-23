package work.gaigeshen.tripartite.ding.openapi.notify;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import work.gaigeshen.tripartite.core.client.Clients;
import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentReceiver;
import work.gaigeshen.tripartite.core.notify.DefaultNotifyContent;
import work.gaigeshen.tripartite.core.notify.NotifyContentIncorrectException;
import work.gaigeshen.tripartite.core.util.json.JsonCodec;
import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * 钉钉异步通知数据接收器，首先会对接收到的异步通知数据校验签名是否合法，然后进行解密操作，将解密后的内容原封不动加入到原始的异步通知数据里面
 *
 * @author gaigeshen
 */
public class DingNotifyContentReceiver extends AbstractNotifyContentReceiver<DefaultNotifyContent> {

    private final Clients<DingConfig> dingClients;

    public DingNotifyContentReceiver(Clients<DingConfig> clients) {
        if (Objects.isNull(clients)) {
            throw new IllegalArgumentException("clients cannot be null");
        }
        this.dingClients = clients;
    }

    @Override
    protected DefaultNotifyContent validate(DefaultNotifyContent content) throws NotifyContentIncorrectException {
        String appKey = (String) content.getValue("app_key");
        if (Objects.isNull(appKey)) {
            throw new NotifyContentIncorrectException("could not find [app_key] parameter: " + content);
        }
        DingConfig dingConfig = dingClients.getConfig(cfg -> Objects.equals(cfg.getAppKey(), appKey));
        content.put("ding_config", dingConfig);

        String signature = (String) content.getValue("msg_signature");
        if (Objects.isNull(signature)) {
            throw new NotifyContentIncorrectException("could not find [msg_signature] parameter: " + content);
        }
        String timestamp = (String) content.getValue("timestamp");
        if (Objects.isNull(timestamp)) {
            throw new NotifyContentIncorrectException("could not find [timestamp] parameter: " + content);
        }
        String nonce = (String) content.getValue("nonce");
        if (Objects.isNull(nonce)) {
            throw new NotifyContentIncorrectException("could not find [nonce] parameter: " + content);
        }
        String bodyString = content.getBodyAsString();
        if (Objects.isNull(bodyString)) {
            throw new NotifyContentIncorrectException("could not find request body: " + content);
        }
        Map<String, Object> bodyDecoded = JsonCodec.instance().decodeObject(bodyString);
        String encrypted = (String) bodyDecoded.get("encrypt");
        if (Objects.isNull(encrypted)) {
            throw new NotifyContentIncorrectException("could not find [encrypt] field of request body: " + content);
        }
        if (!Objects.equals(genSignature(dingConfig, timestamp, nonce, encrypted), signature)) {
            throw new NotifyContentIncorrectException("invalid signature: " + content);
        }
        try {
            content.put("decrypted", decrypt(dingConfig, encrypted));
        } catch (GeneralSecurityException e) {
            throw new NotifyContentIncorrectException("could not decrypt: " + content, e);
        }
        return content;
    }

    /**
     * 此方法用于计算密文的签名，这是个静态方法，在其他类中可以直接调用
     *
     * @param config 钉钉配置不可为空
     * @param timestamp 时间戳不可为空
     * @param nonce 随机值不可为空
     * @param encrypted 密文不可为空
     * @return 返回该密文的签名
     */
    public static String genSignature(DingConfig config, String timestamp, String nonce, String encrypted) {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        if (Objects.isNull(timestamp) || Objects.isNull(nonce) || Objects.isNull(encrypted)) {
            throw new IllegalArgumentException("timestamp and nonce and encrypted cannot be null");
        }
        String[] message = new String[]{config.getToken(), timestamp, nonce, encrypted};
        Arrays.sort(message);
        return DigestUtils.sha1Hex(String.join("", message));
    }

    /**
     * 此方法用于对明文进行加密，加密方案参见钉钉文档，这是个静态方法，在其他类中可以直接调用
     *
     * @param config 钉钉配置不可为空
     * @param plainText 明文不可为空
     * @return 返回该明文加密后的密文
     * @throws GeneralSecurityException 加密通用异常
     */
    public static String encrypt(DingConfig config, String plainText) throws GeneralSecurityException {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        if (Objects.isNull(plainText)) {
            throw new IllegalArgumentException("plain text cannot be null");
        }
        byte[] secretKeyBytes = Base64.decodeBase64(config.getSecretKey() + "=");

        byte[] randomBytes = RandomStringUtils.randomAscii(16).getBytes();
        byte[] plainTextBytes = plainText.getBytes();
        byte[] appKeyBytes = config.getAppKey().getBytes();

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
            byteStream.write(appKeyBytes);
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
     * 此方法用于对密文进行解密，解密方案参见钉钉文档，这是个静态方法，在其他类中可以直接调用
     *
     * @param config 钉钉配置不可为空
     * @param encrypted 密文不可为空
     * @return 返回该密文对应的解密明文
     * @throws GeneralSecurityException 解密通用异常
     */
    public static String decrypt(DingConfig config, String encrypted) throws GeneralSecurityException {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        if (Objects.isNull(encrypted)) {
            throw new IllegalArgumentException("encrypted text cannot be null");
        }
        byte[] secretKeyBytes = Base64.decodeBase64(config.getSecretKey() + "=");

        SecretKeySpec keySpec = new SecretKeySpec(secretKeyBytes, "AES");
        IvParameterSpec paramSpec = new IvParameterSpec(secretKeyBytes, 0, 16);

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
