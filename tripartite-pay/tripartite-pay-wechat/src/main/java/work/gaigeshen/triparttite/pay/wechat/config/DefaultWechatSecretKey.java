package work.gaigeshen.triparttite.pay.wechat.config;

import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Base64;
import java.util.Objects;

/**
 * 默认的微信商户密钥
 *
 * @author gaigeshen
 */
public class DefaultWechatSecretKey implements WechatSecretKey {

  private final byte[] secretKey;

  public DefaultWechatSecretKey(byte[] secretKey) throws WechatSecretKeyException {
    if (Objects.isNull(secretKey)) {
      throw new IllegalArgumentException("secret key cannot be null");
    }
    if (secretKey.length != 32) {
      throw new WechatSecretKeyException("secret key length != 32");
    }
    this.secretKey = secretKey;
  }

  public static DefaultWechatSecretKey load(String secretKeyContent) throws WechatSecretKeyException {
    if (Objects.isNull(secretKeyContent)) {
      throw new IllegalArgumentException("secret key content cannot be null");
    }
    return new DefaultWechatSecretKey(secretKeyContent.getBytes(StandardCharsets.UTF_8));
  }

  public static DefaultWechatSecretKey loadClasspath(String classpath) throws WechatSecretKeyException {
    if (Objects.isNull(classpath)) {
      throw new IllegalArgumentException("secret key classpath cannot be null");
    }
    try (InputStream in = DefaultWechatSecretKey.class.getClassLoader().getResourceAsStream(classpath)) {
      if (Objects.isNull(in)) {
        throw new WechatSecretKeyException("could not read resource: " + classpath);
      }
      return load(IOUtils.toString(in, StandardCharsets.UTF_8));
    } catch (IOException e) {
      throw new WechatSecretKeyException("could not load from classpath: " + classpath, e);
    }
  }

  public static DefaultWechatSecretKey loadFile(String filename) throws WechatSecretKeyException {
    if (Objects.isNull(filename)) {
      throw new IllegalArgumentException("filename cannot be null");
    }
    Path path = Paths.get(filename);
    if (!Files.isReadable(path)) {
      throw new IllegalArgumentException("file not readable: " + filename);
    }
    try {
      String secretKeyContent = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
      return load(secretKeyContent);
    } catch (IOException e) {
      throw new WechatSecretKeyException("could not load from filename: " + filename, e);
    }
  }

  @Override
  public byte[] decrypt(String cipherText, byte[] nonce, byte[] aad) throws WechatSecretKeyException {
    if (Objects.isNull(cipherText)) {
      throw new IllegalArgumentException("cipher text cannot be null");
    }
    if (Objects.isNull(nonce)) {
      throw new IllegalArgumentException("nonce cannot be null");
    }
    if (Objects.isNull(aad)) {
      throw new IllegalArgumentException("aad cannot be null");
    }
    SecretKeySpec keySpec = new SecretKeySpec(secretKey, "AES");
    GCMParameterSpec parameterSpec = new GCMParameterSpec(128, nonce);
    try {
      Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
      cipher.init(Cipher.DECRYPT_MODE, keySpec, parameterSpec);
      cipher.updateAAD(aad);
      return cipher.doFinal(Base64.getDecoder().decode(cipherText));
    }
    catch (InvalidKeyException e) {
      throw new WechatSecretKeyException("secret key is invalid", e);
    }
    catch (GeneralSecurityException e) {
      throw new WechatSecretKeyDecryptionException("could not decrypt", e);
    }
  }
}
