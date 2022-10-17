package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.config.Config;
import work.gaigeshen.tripartite.core.client.config.ConfigException;
import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.client.response.ClientResponse;

/**
 * 客户端
 *
 * @author gaigeshen
 */
public interface Client<C extends Config> {

    C getConfig() throws ConfigException;

    <R extends ClientResponse, P extends ClientParameters> R execute(
            P parameters, Class<R> responseClass, String uri, Object... uriVariables
    ) throws ClientException;
}
