package work.gaigeshen.tripartite.ding.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 钉钉配置信息
 *
 * @author gaigeshen
 */
@ConfigurationProperties("ding")
@Data
public class DingProperties {

    private List<Client> clients = new ArrayList<>();

    /**
     * 钉钉配置信息
     *
     * @author gaigeshen
     */
    @Data
    public static class Client {

        /**
         * 新版服务器的地址
         */
        private String apiServerHost;

        /**
         * 旧版服务器的地址
         */
        private String oapiServerHost;

        /**
         * 授权企业编号（用于定制应用）
         */
        private String authCorpId;

        /**
         * 应用相关属性（调用接口的时候可能会用到）
         */
        private String agentId;

        /**
         * 应用编号（或者定制应用编号）
         */
        private String appKey;

        /**
         * 应用密钥（或者定制应用密钥）
         */
        private String appSecret;

        /**
         * 异步通知或者回调通知的密钥（定制应用不可用）
         */
        private String secretKey;

        /**
         * 异步通知或者回调通知的签名令牌（定制应用不可用）
         */
        private String token;
    }
}
