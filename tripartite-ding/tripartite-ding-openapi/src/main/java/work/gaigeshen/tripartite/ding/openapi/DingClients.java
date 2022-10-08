package work.gaigeshen.tripartite.ding.openapi;

import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.function.Predicate;

/**
 *
 * @author gaigeshen
 */
public interface DingClients {

    default DingClient getClient() throws DingClientNotFoundException {
        return getClient(cfg -> true);
    }

    default DingConfig getConfig(Predicate<DingConfig> predicate) throws DingClientNotFoundException {
        return getClient(predicate).getDingConfig();
    }

    DingClient getClient(Predicate<DingConfig> predicate) throws DingClientNotFoundException;

    DingClient getClientOrCreate(DingConfig config) throws DingClientNotFoundException;
}
