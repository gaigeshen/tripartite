package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.config.Config;

/**
 *
 * @author gaigeshen
 */
public interface ClientCreator<C extends Config> {

    Client<C> create(C config) throws ClientCreationException;

}
