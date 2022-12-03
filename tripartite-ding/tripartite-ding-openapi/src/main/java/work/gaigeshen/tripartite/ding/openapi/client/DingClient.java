package work.gaigeshen.tripartite.ding.openapi.client;

import java.util.Objects;

/**
 * 钉钉接口客户端
 *
 * @author gaigeshen
 */
public interface DingClient extends DingApiClient, DingOapiClient {

    /**
     * 返回是否有套件票据存储器，只有第三方企业应用开发才需要
     *
     * @return 是否有套件票据存储器
     */
    default boolean hasSuiteTicketStore() {
        return Objects.nonNull(getSuiteTicketStore());
    }

    /**
     * 返回钉钉套件票据存储器
     *
     * @return 钉钉套件票据存储器，默认返回空对象，表示没有套件票据存储器
     */
    default DingSuiteTicketStore getSuiteTicketStore() {
        return null;
    }
}
