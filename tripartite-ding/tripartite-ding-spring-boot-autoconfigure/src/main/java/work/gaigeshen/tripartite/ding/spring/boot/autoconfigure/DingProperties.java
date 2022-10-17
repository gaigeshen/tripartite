package work.gaigeshen.tripartite.ding.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 钉钉配置信息属性
 *
 * @author gaigeshen
 */
@ConfigurationProperties("ding")
public class DingProperties {

    private List<Client> clients = new ArrayList<>();

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    /**
     * 钉钉配置信息
     *
     * @author gaigeshen
     */
    public static class Client {

        /**
         * 服务器的地址
         */
        private String serverHost;

        /**
         * 获取访问令牌的接口调用地址
         */
        private String accessTokenUri;

        /**
         * 应用编号
         */
        private String appKey;

        /**
         * 应用密钥
         */
        private String appSecret;

        /**
         * 异步通知或者回调通知的密钥
         */
        private String secretKey;

        /**
         * 异步通知或者回调通知的签名令牌
         */
        private String token;

        public String getServerHost() {
            return serverHost;
        }

        public void setServerHost(String serverHost) {
            this.serverHost = serverHost;
        }

        public String getAccessTokenUri() {
            return accessTokenUri;
        }

        public void setAccessTokenUri(String accessTokenUri) {
            this.accessTokenUri = accessTokenUri;
        }

        public String getAppKey() {
            return appKey;
        }

        public void setAppKey(String appKey) {
            this.appKey = appKey;
        }

        public String getAppSecret() {
            return appSecret;
        }

        public void setAppSecret(String appSecret) {
            this.appSecret = appSecret;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
