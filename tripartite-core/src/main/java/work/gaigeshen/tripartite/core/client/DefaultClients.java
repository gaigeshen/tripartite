package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.config.Config;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 *
 * @author gaigeshen
 */
public class DefaultClients<C extends Config> implements Clients<C> {

    private final Map<C, Client<C>> clients = new ConcurrentHashMap<>();

    private final ClientCreator<C> clientCreator;

    public DefaultClients(Collection<Client<C>> clients, ClientCreator<C> clientCreator) {
        if (Objects.isNull(clients)) {
            throw new IllegalArgumentException("clients cannot be null");
        }
        if (Objects.isNull(clientCreator)) {
            throw new IllegalArgumentException("client creator cannot be null");
        }
        for (Client<C> client : clients) {
            this.clients.put(client.getConfig(), client);
        }
        this.clientCreator = clientCreator;
    }

    @Override
    public Client<C> getClient(Predicate<C> predicate) throws ClientNotFoundException {
        if (Objects.isNull(predicate)) {
            throw new IllegalArgumentException("predicate cannot be null");
        }
        Client<C> client = findClient(predicate);
        if (Objects.isNull(client)) {
            throw new ClientNotFoundException("could not find client");
        }
        return client;
    }

    @Override
    public Client<C> getClientOrCreate(C config) throws ClientCreationException {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("config cannot be null");
        }
        return clients.computeIfAbsent(config, clientCreator::create);
    }

    private Client<C> findClient(Predicate<C> predicate) {
        if (Objects.isNull(predicate)) {
            throw new IllegalArgumentException("predicate cannot be null");
        }
        for (Map.Entry<C, Client<C>> entry : clients.entrySet()) {
            if (predicate.test(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
