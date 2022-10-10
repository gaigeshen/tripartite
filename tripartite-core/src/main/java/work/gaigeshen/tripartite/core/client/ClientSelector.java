package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.config.Config;

/**
 *
 * @author gaigeshen
 */
public interface ClientSelector<C extends Config> {

    Client<C> select(C config);
}
