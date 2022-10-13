package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.config.Config;

import java.util.function.Predicate;

/**
 *
 * @author gaigeshen
 */
public interface Clients<C extends Config> {

    default Client<C> getClient() throws ClientNotFoundException {
        return getClient(cfg -> true);
    }

    default C getConfig(Predicate<C> predicate) throws ClientNotFoundException {
        return getClient(predicate).getConfig();
    }

    Client<C> getClient(Predicate<C> predicate) throws ClientNotFoundException;

    Client<C> getClientOrCreate(C config) throws ClientCreationException;
}
