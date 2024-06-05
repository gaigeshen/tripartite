package work.gaigeshen.tripartite.wangdian.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 旺店通客户端配置
 *
 * @author gaigeshen
 */
@ConfigurationProperties("wangdian")
@Data
public class WangdianProperties {

    /**
     * 旺店通服务器主机地址（必填）
     */
    private String serverHost;

    /**
     * 旺店通卖家编号（必填）
     */
    private String sellerId;

    /**
     * 特定场景下的默认店铺编号（必填）
     */
    private String shopNo;

    /**
     * 旺店通接口账号（必填）
     */
    private String appKey;

    /**
     * 旺店通接口密钥（必填）
     */
    private String appSecret;
}
