package work.gaigeshen.tripartite.qyweixin.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业微信配置信息
 *
 * @author gaigeshen
 */
@ConfigurationProperties("spring.qyweixin")
@Data
public class QyWeixinProperties {

    private List<Client> clients = new ArrayList<>();

    /**
     * 企业微信配置信息
     *
     * @author gaigeshen
     */
    @Data
    public static class Client {

        /**
         * 服务器的地址
         */
        private String serverHost;

        /**
         * 企业编号
         */
        private String corpId;

        /**
         * 应用的凭证密钥
         */
        private String corpSecret;
    }
}
