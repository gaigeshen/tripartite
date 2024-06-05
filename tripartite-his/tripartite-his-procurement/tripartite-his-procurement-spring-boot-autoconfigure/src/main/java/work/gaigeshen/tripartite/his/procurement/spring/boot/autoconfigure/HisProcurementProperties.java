package work.gaigeshen.tripartite.his.procurement.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaigeshen
 */
@ConfigurationProperties("his.procurement")
@Data
public class HisProcurementProperties {

    private List<Client> clients = new ArrayList<>();

    /**
     * @author gaigeshen
     */
    @Data
    public static class Client {

        private Integer connectTimeout;

        private Integer readTimeout;

        private String serverHost;

        private String accessTokenUri;

        private String serviceUri;

        private String account;

        private String type;

        private String appCode;

        private String authCode;

        private String secret;
    }
}
