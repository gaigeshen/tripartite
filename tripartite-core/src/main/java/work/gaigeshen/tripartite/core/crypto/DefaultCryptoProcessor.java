package work.gaigeshen.tripartite.core.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;

/**
 * @author gaigeshen
 */
public class DefaultCryptoProcessor implements CryptoProcessor {

    private final byte[] secret;

    public DefaultCryptoProcessor(String secret) {
        if (Objects.isNull(secret)) {
            throw new IllegalArgumentException("secret cannot be null");
        }
        this.secret = secret.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String doEncrypt(String plainData) throws GeneralSecurityException {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        byte[] seedBytes = new SecureRandom().generateSeed(16);

        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(secret, "AES"), new IvParameterSpec(seedBytes));

        byte[] encryptedDataBytes = cipher.doFinal(plainData.getBytes(StandardCharsets.UTF_8));

        byte[] encryptedDataWithSeedBytes = new byte[encryptedDataBytes.length + 16];

        System.arraycopy(encryptedDataBytes, 0, encryptedDataWithSeedBytes, 0, encryptedDataBytes.length);

        System.arraycopy(seedBytes, 0, encryptedDataWithSeedBytes, encryptedDataBytes.length, 16);

        return Base64.getEncoder().encodeToString(encryptedDataWithSeedBytes);
    }

    @Override
    public String doDecrypt(String encrytedData) throws GeneralSecurityException {

        byte[] encryptedDataWithSeedBytes = Base64.getDecoder().decode(encrytedData.getBytes(StandardCharsets.UTF_8));

        byte[] seedBytes = new byte[16];

        byte[] encryptedDataBytes = new byte[encryptedDataWithSeedBytes.length - 16];

        System.arraycopy(encryptedDataWithSeedBytes, encryptedDataWithSeedBytes.length - 16, seedBytes, 0, seedBytes.length);

        System.arraycopy(encryptedDataWithSeedBytes, 0, encryptedDataBytes, 0, encryptedDataBytes.length);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(secret, "AES"), new IvParameterSpec(seedBytes));

        byte[] decryptedDataBytes = cipher.doFinal(encryptedDataBytes);

        return new String(decryptedDataBytes, StandardCharsets.UTF_8);
    }
}
