package work.gaigeshen.tripartite.retrofit.core.server;

import java.util.*;

/**
 *
 * @author gaigeshen
 */
public class DefaultServerHosts implements ServerHosts {

    private final Map<String, ServerHost> serverHosts = new HashMap<>();

    private DefaultServerHosts(List<ServerHost> serverHosts) {
        for (ServerHost serverHost : serverHosts) {
            this.serverHosts.put(serverHost.getServerId(), serverHost);
        }
    }

    public static DefaultServerHosts create(List<ServerHost> serverHosts) {
        return new DefaultServerHosts(serverHosts);
    }

    @Override
    public ServerHost getServerHost(String serverId) {
        return serverHosts.get(serverId);
    }

    @Override
    public Collection<ServerHost> getServerHosts() {
        return new ArrayList<>(serverHosts.values());
    }

    @Override
    public String toString() {
        return getServerHosts().toString();
    }
}
