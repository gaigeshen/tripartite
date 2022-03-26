package work.gaigeshen.tripartite.pay.wechat.config;

/**
 * 微信商戶私钥可以用于计算签名和敏感数据解密
 *
 * @author gaigeshen
 */
public interface WechatPrivateKey {

  String sign(byte[] content) throws WechatPrivateKeyException;

  byte[] decrypt(String cipherText) throws WechatPrivateKeyException;

  String getCertSerialNumber();

}
