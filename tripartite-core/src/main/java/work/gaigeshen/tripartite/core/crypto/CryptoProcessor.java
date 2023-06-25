package work.gaigeshen.tripartite.core.crypto;

import java.security.GeneralSecurityException;

/**
 * 加解密处理器
 *
 * @author gaigeshen
 */
public interface CryptoProcessor {

    /**
     * 创建加解密处理器的默认实现
     *
     * @param secret 加解密使用的密钥
     * @return 默认的加解密处理器
     */
    static CryptoProcessor createDefault(String secret) {
        return new DefaultCryptoProcessor(secret);
    }

    /**
     * 执行加密操作
     *
     * @param plainData 明文字符串
     * @return 加密后的内容
     * @throws GeneralSecurityException 无法执行加密操作
     */
    String doEncrypt(String plainData) throws GeneralSecurityException;

    /**
     * 执行解密操作
     *
     * @param encrytedData 密文字符串
     * @return 解密后的内容
     * @throws GeneralSecurityException 无法执行解密操作
     */
    String doDecrypt(String encrytedData) throws GeneralSecurityException;
}
