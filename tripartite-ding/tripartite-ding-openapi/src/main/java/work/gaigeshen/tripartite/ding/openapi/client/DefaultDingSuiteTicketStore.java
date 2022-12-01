package work.gaigeshen.tripartite.ding.openapi.client;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 钉钉套件票据存储器的默认实现
 *
 * @author gaigeshen
 */
public class DefaultDingSuiteTicketStore implements DingSuiteTicketStore {

    private final Map<String, String> suiteTickets = new ConcurrentHashMap<>();

    @Override
    public void setTicket(String suiteId, String ticket) {
        if (Objects.isNull(suiteId) || Objects.isNull(ticket)) {
            return;
        }
        suiteTickets.put(suiteId, ticket);
    }

    @Override
    public String getTicket(String suiteId) {
        if (Objects.isNull(suiteId)) {
            return null;
        }
        return suiteTickets.get(suiteId);
    }
}
