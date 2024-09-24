package work.gaigeshen.tripartite.qyweixin.openapi.notify.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

/**
 * 签名工具类
 *
 * @author gaigeshen
 */
public abstract class SignatureUtils {

    private SignatureUtils() { }

    public static String genSignature(String token, String timestamp, String nonce, String encrypted) {
        String[] message = new String[]{token, timestamp, nonce, encrypted};
        Arrays.sort(message);
        return DigestUtils.sha1Hex(String.join("", message));
    }
}
