package work.gaigeshen.tripartite.retrofit.core.server;

import java.util.Collection;
import java.util.Iterator;

/**
 * 维护多个服务器地址
 *
 * @author gaigeshen
 */
public interface ServerHosts extends Iterable<ServerHost> {

    @Override
    default Iterator<ServerHost> iterator() {
        return getServerHosts().iterator();
    }

    ServerHost getServerHost(String serverId);

    Collection<ServerHost> getServerHosts();

    /**
     * 此方法返回默认的服务器地址
     *
     * @return 默认的服务器地址可能为空
     */
    default ServerHost getServerHost() {
        Collection<ServerHost> serverHosts = getServerHosts();
        if (!serverHosts.isEmpty()) {
            return serverHosts.iterator().next();
        }
        return null;
    }
}
