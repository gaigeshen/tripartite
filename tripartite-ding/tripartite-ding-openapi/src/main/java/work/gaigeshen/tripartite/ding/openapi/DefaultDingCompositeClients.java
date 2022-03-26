package work.gaigeshen.tripartite.ding.openapi;

import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 *
 * @author gaigeshen
 */
public class DefaultDingCompositeClients implements DingCompositeClients {

  private final Map<DingConfig, DingCompositeClient> clients = new ConcurrentHashMap<>();

  public DefaultDingCompositeClients() { }

  public DefaultDingCompositeClients(Collection<DingCompositeClient> clients) {
    if (Objects.isNull(clients)) {
      throw new IllegalArgumentException("clients cannot be null");
    }
    for (DingCompositeClient client : clients) {
      this.clients.put(client.getConfig(), client);
    }
  }

  @Override
  public DingCompositeClient getCompositeClient(Predicate<DingConfig> predicate) throws DingClientNotFoundException {
    if (Objects.isNull(predicate)) {
      throw new IllegalArgumentException("predicate cannot be null");
    }
    for (Map.Entry<DingConfig, DingCompositeClient> entry : clients.entrySet()) {
      if (predicate.test(entry.getKey())) {
        return entry.getValue();
      }
    }
    throw new DingClientNotFoundException("available clients: " + clients.keySet());
  }

}
