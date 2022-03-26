package work.gaigeshen.tripartite.pay.wechat.config;

/**
 * 微信商户密钥可以用于回调通知和平台证书解密
 *
 * @author gaigeshen
 */
public interface WechatSecretKey {

  byte[] decrypt(String cipherText, byte[] nonce, byte[] aad) throws WechatSecretKeyException;
}
