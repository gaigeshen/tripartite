package work.gaigeshen.tripartite.core.client;

import work.gaigeshen.tripartite.core.client.parameters.ClientParameters;
import work.gaigeshen.tripartite.core.client.response.ClientResponse;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author gaigeshen
 */
public interface ServerHosts extends Iterable<ServerHost> {

    static Collection<ServerHost> create(String... serverHosts) {
        if (Objects.isNull(serverHosts)) {
            return Collections.emptySet();
        }
        return Arrays.stream(serverHosts).map(ServerHost::create).collect(Collectors.toSet());
    }

    @Override
    default Iterator<ServerHost> iterator() {
        return getServerHosts().iterator();
    }

    default ServerHost getServerHost(ClientParameters parameters,
                                     Class<? extends ClientResponse> responseClass) throws ServerHostException {
        Collection<ServerHost> serverHosts = getServerHosts();
        if (serverHosts.isEmpty()) {
            throw new ServerHostMissingException("server hosts is empty");
        }
        return serverHosts.iterator().next();
    }

    Collection<ServerHost> getServerHosts() throws ServerHostException;
}
