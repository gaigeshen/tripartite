package work.gaigeshen.tripartite.ding.openapi.client;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultDingSuiteTicketStore implements DingSuiteTicketStore {

    private final Map<String, String> suiteTickets = new ConcurrentHashMap<>();

    @Override
    public void setSuiteTicket(String suiteKey, String suiteTicket) {
        if (Objects.isNull(suiteKey) || Objects.isNull(suiteTicket)) {
            return;
        }
        suiteTickets.put(suiteKey, suiteTicket);
    }

    @Override
    public void removeTicket(String suiteKey) {
        if (Objects.isNull(suiteKey)) {
            return;
        }
        suiteTickets.remove(suiteKey);
    }

    @Override
    public String getSuiteTicket(String suiteKey) {
        if (Objects.isNull(suiteKey)) {
            return null;
        }
        return suiteTickets.get(suiteKey);
    }
}
