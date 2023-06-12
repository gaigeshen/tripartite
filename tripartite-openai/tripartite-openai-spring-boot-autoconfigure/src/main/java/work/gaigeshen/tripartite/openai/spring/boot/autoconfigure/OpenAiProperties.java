package work.gaigeshen.tripartite.openai.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaigeshen
 */
@ConfigurationProperties("openai")
public class OpenAiProperties {

    private List<Client> clients = new ArrayList<>();

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    /**
     * 配置信息
     *
     * @author gaigeshen
     */
    public static class Client {

        /**
         * 服务器地址
         */
        private String serverHost;

        /**
         * 接口密钥
         */
        private String apiKey;

        /**
         * 组织标识符
         */
        private String organization;

        public String getServerHost() {
            return serverHost;
        }

        public void setServerHost(String serverHost) {
            this.serverHost = serverHost;
        }

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getOrganization() {
            return organization;
        }

        public void setOrganization(String organization) {
            this.organization = organization;
        }
    }
}
