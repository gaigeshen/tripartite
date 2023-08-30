package work.gaigeshen.tripartite.retrofit.core.server;

import java.util.Objects;

/**
 * 维护服务器地址
 *
 * @author gaigeshen
 */
public class ServerHost {

    private final String serverId;

    private final String serverHost;

    private ServerHost(String serverId, String serverHost) {
        if (Objects.isNull(serverId) || Objects.isNull(serverHost)) {
            throw new IllegalArgumentException("server id and host cannot be null");
        }
        this.serverId = serverId;
        this.serverHost = serverHost;
    }

    public static ServerHost create(String serverId, String serverHost) {
        return new ServerHost(serverId, serverHost);
    }

    public String getServerId() {
        return serverId;
    }

    public String getServerHost() {
        return serverHost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServerHost that = (ServerHost) o;
        return Objects.equals(serverId, that.serverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverId);
    }

    @Override
    public String toString() {
        return serverHost + "(" + serverId + ")";
    }
}
