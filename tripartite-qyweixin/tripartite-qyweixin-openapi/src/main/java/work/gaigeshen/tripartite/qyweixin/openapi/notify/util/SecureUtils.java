package work.gaigeshen.tripartite.qyweixin.openapi.notify.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/**
 * 加解密工具类
 *
 * @author gaigeshen
 */
public abstract class SecureUtils {

    private SecureUtils() { }

    public static String encrypt(String aesKey, String corpId, String plainText) throws GeneralSecurityException {

        byte[] secretKeyBytes = Base64.decodeBase64(aesKey + "=");

        byte[] randomBytes = RandomStringUtils.randomAscii(16).getBytes();
        byte[] plainTextBytes = plainText.getBytes();
        byte[] corpIdBytes = corpId.getBytes();

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
            byteStream.write(corpIdBytes);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

        int padCount = 32 - (byteStream.size() % 32);
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

    public static String decrypt(String aesKey, String encrypted) throws GeneralSecurityException {

        byte[] aesKeyBytes = Base64.decodeBase64(aesKey + "=");

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
