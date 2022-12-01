package work.gaigeshen.tripartite.ding.openapi.client;

public interface DingSuiteTicketStore {

    void setSuiteTicket(String suiteKey, String suiteTicket);

    void removeTicket(String suiteKey);

    String getSuiteTicket(String suiteKey);
}
